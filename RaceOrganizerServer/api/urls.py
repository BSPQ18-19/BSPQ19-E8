from django.urls import path

from . import views

urlpatterns = [
    path('', views.index, name='index'),

    # TODO Placeholder add a token or some short of security measure
    path('<str:username>', views.get_user, name='get_user'),
]
