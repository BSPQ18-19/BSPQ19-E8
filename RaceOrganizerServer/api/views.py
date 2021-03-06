import json
import re

from django.contrib.auth.models import User
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from silk.profiling.profiler import silk_profile

from api.models import Person, Race, Runner, Task


def index(request):
    return HttpResponse("i think you are lost")


# Users API Methods
@silk_profile(name='User List')
def user_list(request):
    if request.method == "GET":
        users = Person.objects.all()
        users_json = []

        if request.GET.get('s'):
            users = users.filter(user__username__icontains=request.GET.get('s'))

        for user in users:
            users_json.append(user.get_simple_json())

        return JsonResponse(users_json, safe=False, status=200)

    else:
        return HttpResponse(status=405)


@silk_profile(name='User View')
def user_view(request, user_id):
    if request.method == "GET":

        if Person.objects.filter(pk=user_id).exists():
            user = Person.objects.get(pk=user_id)
            return JsonResponse(user.get_json(), safe=False, status=200)
        else:
            return HttpResponse("user not found", status=404)

    else:
        return HttpResponse(status=405)


@silk_profile(name='Profile View')
def profile_view(request):
    if request.method == "GET":

        if request.user.is_authenticated:
            person = request.user.person

            return JsonResponse(person.get_profile_json(), safe=False, status=200)
        else:
            return HttpResponse("user must be logged in", status=401)


# Races API Methods
@csrf_exempt
@silk_profile(name='Races List')
def races_list(request):
    if request.method == "GET":
        races = Race.objects.all()
        races_json = []

        if request.GET.get('s'):
            races = races.filter(edition__icontains=request.GET.get('s'))

        for race in races:
            races_json.append(race.get_simple_json())

        return JsonResponse(races_json, safe=False, status=200)

    elif request.method == "POST":
        if request.user.is_authenticated:

            edition = request.POST.get('edition')

            if not (Race.objects.filter(edition=edition).exists()):

                race = {
                    "edition": request.POST.get("edition"),
                    "sponsor": request.POST.get("sponsor"),
                    "place": request.POST.get("place"),
                    "time": request.POST.get("time"),
                    "price": request.POST.get("price"),
                    "prize": request.POST.get("prize")
                }

                Race.objects.create(organizer=request.user.person, **race)

                return HttpResponse("successful operation", status=201)

            else:
                return HttpResponse("invalid edition", status=400)

        else:
            return HttpResponse("user must be logged in", status=401)

    else:
        return HttpResponse(status=405)


@csrf_exempt
@silk_profile(name='Race View')
def race_view(request, race_id):
    if request.method == "GET":
        if Race.objects.filter(pk=race_id).exists():
            race = Race.objects.get(pk=race_id)

            return JsonResponse(race.get_json(), safe=False, status=200)
        else:
            return HttpResponse("race not found", status=404)
    else:
        return HttpResponse(status=405)


"""
This is a bit dirty but implementing a new package at this stage would take a lot of time
"""


@csrf_exempt
@silk_profile(name="Add Runner to Race")
def add_runner(request, race_id):
    if request.method == "POST":

        if request.user.is_authenticated:

            username = request.POST.get('username')

            if Race.objects.filter(pk=race_id).exists() and User.objects.filter(username=username).exists():

                race = Race.objects.get(pk=race_id)
                person = User.objects.get(username=username).person

                if race.organizer.user == request.user or request.user == person.user:

                    if person in race.runners.all() or person in race.helpers.all():
                        return HttpResponse("person already takes part in race", status=400)

                    else:

                        number = race.runner_set.count() + 1
                        runner = Runner.objects.create(race=race, person=person, number=number)
                        race.runner_set.add(runner)

                        return HttpResponse("successful operation", status=201)

                else:
                    return HttpResponse("user does not have permission to add this user to the race", status=403)

            else:
                return HttpResponse("user or race not found", status=404)
        else:
            return HttpResponse("user must be logged in", status=401)
    else:
        return HttpResponse(status=405)


@csrf_exempt
@silk_profile(name="Add Helper to Race")
def add_helper(request, race_id):
    if request.method == "POST":
        if request.user.is_authenticated:

            username = request.POST.get('username')

            if Race.objects.filter(pk=race_id).exists() and User.objects.filter(username=username).exists():

                race = Race.objects.get(pk=race_id)
                person = User.objects.get(username=username).person

                if race.organizer.user == request.user or request.user == person.user:

                    if person in race.runners.all() or person in race.helpers.all():
                        return HttpResponse("person already takes part in race", status=400)

                    else:
                        race.helpers.add(person)

                        return HttpResponse("successful operation", status=201)

                else:
                    return HttpResponse("user does not have permission to add this user to the race", status=403)

            else:
                return HttpResponse("user or race not found", status=404)

        else:
            return HttpResponse("user must be logged in", status=401)
    else:
        return HttpResponse(status=405)


def body_to_dict(request):
    return json.loads(str(request.body, encoding='utf8').replace("'", '"'))


@csrf_exempt
@silk_profile(name="Add Task")
def new_task(request, race_id):
    if request.method == "POST":
        if request.user.is_authenticated:

            if Race.objects.filter(pk=race_id).exists():
                race = Race.objects.get(pk=race_id)

                if race.organizer.user == request.user:

                    description = request.POST.get('description')
                    pattern = re.compile("[a-zA-Z]")

                    if pattern.match(description):

                        Task.objects.create(description=description, race=race)

                        return HttpResponse("successful operation", status=201)

                    else:
                        return HttpResponse("empty description", status=400)
                else:
                    return HttpResponse("user does not have permission to create a task in this race", status=403)

            else:
                return HttpResponse("race not found", status=404)
        else:
            return HttpResponse("user must be logged in", status=401)
    else:
        return HttpResponse(status=405)


@csrf_exempt
@silk_profile(name="Add Task")
def task_view(request, race_id, task_id):
    if request.method == "PUT":
        put = body_to_dict(request)

        if request.user.is_authenticated:

            if Task.objects.filter(pk=task_id, race_id=race_id).exists():
                task = Task.objects.get(pk=task_id)

                if put.get("description"):
                    description = put.get("description")

                    if description != task.description:

                        if task.race.organizer.user == request.user:

                            pattern = re.compile("[a-zA-Z]")

                            if pattern.match(description):
                                task.description = description

                            else:
                                return HttpResponse("empty description", status=400)
                        else:
                            return HttpResponse("user does not have permission to modify this task", status=403)

                if put.get("username"):
                    username = put.get("username")

                    if Person.objects.filter(user__username=username).exists():
                        person = Person.objects.get(user__username=username)

                        if task.person is None:
                            task.person = person

                        else:
                            if person != task.person:
                                if task.race.organizer.user == request.user or task.person.user == request.user:
                                    task.person = person
                                else:
                                    return HttpResponse("user does not have permission to modify this task", status=403)

                    else:
                        return HttpResponse("user not found", status=404)

                if put.get("completed"):

                    if task.person is not None:
                        if put.get("completed").lower() == "true":
                            completed = True
                        else:
                            completed = False

                        if completed != task.completed:

                            if task.race.organizer.user == request.user or task.person.user == request.user:
                                task.completed = completed
                            else:
                                return HttpResponse("user does not have permission to modify this task", status=403)

                    else:
                        return HttpResponse("cannot set completed if task is not assigned", status=400)

                task.save()
                return HttpResponse("successful operation", status=200)

            else:
                return HttpResponse("race or task not found", status=404)
        else:
            return HttpResponse("user must be logged in", status=401)

    if request.method == "DELETE":
        if request.user.is_authenticated:
            if Task.objects.filter(pk=task_id, race_id=race_id).exists():
                task = Task.objects.get(pk=task_id)

                if task.race.organizer.user == request.user:
                    task.delete()
                    return HttpResponse("successful operation", status=200)
                else:
                    return HttpResponse("user does not have permission to delete this task", status=403)

            else:
                return HttpResponse("race or task not found", status=404)
        else:
            return HttpResponse("user must be logged in", status=401)
    else:
        return HttpResponse(status=405)
