from django.contrib.auth.decorators import login_required
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt

from api.models import Person, Race


def index(request):
    return HttpResponse("I think you are lost")


# Users API Methods

def user_list(request):
    if request.method == "GET":
        users = Person.objects.all()

        users_json = []

        for user in users:
            users_json.append(user.get_simple_json())

        return JsonResponse(users_json, safe=False, status=200)

    else:
        return HttpResponse(status=405)


def user_view(request, user_id):
    if request.method == "GET":
        user = Person.objects.get(pk=user_id)

        return JsonResponse(user.get_json(), safe=False, status=200)

    else:
        return HttpResponse(status=405)


@login_required
def profile_view(request):
    if request.method == "GET":
        person = request.user.person

        return JsonResponse(person.get_profile_json(), safe=False, status=200)

    else:
        return HttpResponse(status=405)


# Races API Methods
def races_list(request):
    if request.method == "GET":
        races = Race.objects.all()

        races_json = []

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

                return HttpResponse("successful operation", status=200)
            else:
                return HttpResponse("invalid edition", status=400)

        else:
            return HttpResponse("user must be logged in", status=403)

    else:
        return HttpResponse(status=405)


@csrf_exempt
def race_view(request, race_id):
    if request.method == "GET":
        race = Race.objects.get(pk=race_id)

        return JsonResponse(race.get_json(), safe=False, status=200)

    else:
        return HttpResponse(status=405)
