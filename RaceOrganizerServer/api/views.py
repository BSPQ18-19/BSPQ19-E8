from django.contrib.auth.decorators import login_required
from django.contrib.auth.models import User
from django.http import HttpResponse, JsonResponse


def index(request):
    return HttpResponse("I think you are lost")


def get_user(request):
    username = request.POST.get('username')

    user = User.objects.filter(username=username).values("username", "first_name", "last_name", 'email')

    return JsonResponse(list(user), safe=False, status=200)


@login_required
def get_profile(request):
    username = request.user

    user = User.objects.filter(username=username).values("username", "first_name", "last_name", 'email',
                                                         "person__birth_date", "person__personal_id")

    return JsonResponse(list(user), safe=False, status=200)
