from django.http import HttpResponse, JsonResponse

from api.models import User


def index(request):
    return HttpResponse("I think you are lost")


def get_user(request, username):
    user = User.objects.filter(username=username).values('username', 'personal_id', 'name', 'surname', 'personal_id',
                                                         'email', 'isOrganizer')
    return JsonResponse(list(user), safe=False)
