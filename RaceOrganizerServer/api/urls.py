from django.conf.urls import url
from django.urls import path

from . import views

urlpatterns = [
    path('', views.index, name='index'),

    url(r'^user/$', views.get_user, name='get_user'),
    url(r'^profile/$', views.get_profile, name='get_user'),

]
