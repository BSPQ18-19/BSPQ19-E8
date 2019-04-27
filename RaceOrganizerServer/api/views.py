from django.contrib.auth.decorators import login_required
from django.http import HttpResponse, JsonResponse

from api.models import Person


def index(request):
    return HttpResponse("I think you are lost")


def get_userlist(request):
    users = Person.objects.all()

    users_json = []

    for user in users:
        users_json.append(user.get_simple_json())

    return JsonResponse(users_json, safe=False, status=200)


def get_user(request, user_id):
    user = Person.objects.get(pk=user_id)

    return JsonResponse(user.get_json(), safe=False, status=200)


@login_required
def get_profile(request):
    person = request.user.person

    return JsonResponse(person.get_profile_json(), safe=False, status=200)
