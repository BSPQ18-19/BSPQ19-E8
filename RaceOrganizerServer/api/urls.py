from django.urls import path

from . import views

urlpatterns = [
    path('', views.index, name='index'),

    path('users/', views.user_list, name='user_list'),
    path('users/<int:user_id>/', views.user_view, name='user_view'),
    path('profile/', views.profile_view, name='profile_view'),

    path('races/', views.races_list, name='races_list'),
    path('races/<int:race_id>/', views.race_view, name='race_view'),
    path('races/<int:race_id>/add_runner', views.add_runner, name='add_runner'),
    path('races/<int:race_id>/add_helper', views.add_helper, name='add_helper'),
    # path('races/<int:race_id>/new_task', views.race_view, name='new_runner'),
]