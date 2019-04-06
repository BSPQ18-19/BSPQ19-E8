from django.contrib.auth import login, authenticate, logout
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth.models import User
from django.http import HttpResponse
from django.shortcuts import render, redirect


def signup_view(request):
    """
    Registration process as a HTML form
    """

    if request.method == 'POST':
        form = UserCreationForm(request.POST)
        if form.is_valid():
            form.save()
            username = form.cleaned_data.get('username')
            raw_password = form.cleaned_data.get('password1')
            user = authenticate(username=username, password=raw_password)
            login(request, user)
            return redirect('home')
    else:
        form = UserCreationForm()
    return render(request, 'core/register.html', {'form': form})


def signup_post_view(request):
    username = request.POST.get('username')
    password = request.POST.get('password')

    if not (User.objects.filter(username=username).exists()):

        user = User.objects.create_user(username=username, password=password)

        if user is not None:
            login(request, user)
            return HttpResponse("Signup Successful", status=201)
        else:
            return HttpResponse("Signup Failed", )


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
