from django.conf.urls import url
from django.urls import path

from . import views

urlpatterns = [
    path('', views.index, name='index'),
    url(r'^signup/post/$', views.signup_post_view, name='signup'),
    url(r'^signup/$', views.signup_view, name='register'),
    url(r'^login/post/$', views.login_post_view, name='login'),
    url(r'^logout/$', views.logout_view, name='logout'),
]
