from django.http import HttpResponse, JsonResponse

from api.models import Person


def index(request):
    return HttpResponse("I think you are lost")


def get_user(request):
    username = request.POST.get('username')

    user = Person.objects.filter(username=username).values('username', 'name', 'surname', 'email')

    return JsonResponse(list(user), safe=False, status=200)


# TODO doesn't work
def get_profile(request):
    if request.user.is_authenticated:
        user = Person.objects.filter(username=request.user.username).values('username', 'personal_id', 'name',
                                                                             'surname'
                                                                             'email', 'isOrganizer')
        return JsonResponse(list(user), safe=False, status=200)

    else:
        return HttpResponse("User not logged in", status=401)
