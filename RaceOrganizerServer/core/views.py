from django.contrib.auth import login, authenticate, logout
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth.models import User
from django.http import HttpResponse
from django.shortcuts import render, redirect

from api.models import Person


def signup_view(request):
    """
    Registration process as a HTML form
    """

    if request.method == 'POST':
        form = UserCreationForm(request.POST)
        if form.is_valid():
            form.save()
            username = form.cleaned_data.get('username')
            raw_password = form.cleaned_data.get('password')
            user = authenticate(username=username, password=raw_password)
            login(request, user)
            return redirect('home')
    else:
        form = UserCreationForm()
    return render(request, 'core/register.html', {'form': form})


def signup_post_view(request):
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
            return HttpResponse("Signup Failed")


def login_post_view(request):
    username = request.POST.get('username')
    password = request.POST.get('password')

    user = authenticate(request, username=username, password=password)
    if user is not None:
        login(request, user)
        return HttpResponse("Login Successful", status=200)
    else:
        return HttpResponse("Login Failed")


def logout_view(request):
    logout(request)


def index(request):
    return HttpResponse("Nothing to see here")
