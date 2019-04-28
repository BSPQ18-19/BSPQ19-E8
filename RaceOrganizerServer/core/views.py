from django.contrib.auth import login, authenticate, logout
from django.contrib.auth.models import User
from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt

from api.models import Person


@csrf_exempt
def signup_view(request):
    """
    Registration process as a HTML form
    """

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
                return HttpResponse("Signup Successful", status=201)
            else:
                return HttpResponse("Signup Failed", status=400)


@csrf_exempt
def login_view(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')

        user = authenticate(request, username=username, password=password)

        if user is not None:
            login(request, user)
            return HttpResponse("Login Successful", status=200)
        else:
            return HttpResponse("Login Failed", status=401)


@csrf_exempt
def logout_view(request):
    logout(request)


def index(request):
    return HttpResponse("Nothing to see here")
