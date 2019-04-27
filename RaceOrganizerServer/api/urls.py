from django.urls import path

from . import views

urlpatterns = [
    path('', views.index, name='index'),

    path('users/', views.get_userlist, name='get_userlist'),
    path('users/<int:user_id>/', views.get_user, name='get_user'),
    path('profile/', views.get_profile, name='get_profile'),

]
