from django.contrib.auth import login, authenticate, logout
from django.contrib.auth.models import User
from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt
from silk.profiling.profiler import silk_profile

from api.models import Person


@csrf_exempt
@silk_profile(name='Signup')
def signup_view(request):

    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')
        first_name = request.POST.get('first_name')
        last_name = request.POST.get('last_name')
        email = request.POST.get('email')
        personal_id = request.POST.get('personal_id')
        birth_date = request.POST.get('birth_date')

        if not (User.objects.filter(username=username).exists()):

            user = User.objects.create_user(username=username, password=password, email=email,
                                            first_name=first_name, last_name=last_name)
            Person.objects.create(user=user, personal_id=personal_id, birth_date=birth_date)

            if user is not None:
                login(request, user)
                return HttpResponse("successful operation", status=201)

        return HttpResponse("invalid username or personal_id password supplied", status=400)

    else:
        return HttpResponse(status=405)


@csrf_exempt
@silk_profile(name='Login')
def login_view(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')

        user = authenticate(request, username=username, password=password)

        if user is not None:
            login(request, user)
            return HttpResponse("successful operation", status=200)
        else:
            return HttpResponse("invalid username/password supplied", status=401)

    else:
        return HttpResponse(status=405)


@csrf_exempt
@silk_profile(name='Logout')
def logout_view(request):
    if request.method == 'GET':
        if request.user.is_authenticated:
            logout(request)
            return HttpResponse("successful operation", status=200)
        else:
            return HttpResponse("user must be logged in", status=401)

    else:
        return HttpResponse(status=405)


def index(request):
    return HttpResponse("nothing to see here")
