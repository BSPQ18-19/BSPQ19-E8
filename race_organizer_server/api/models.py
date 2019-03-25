from django.db import models


class User(models.Model):
    username = models.CharField(max_length=32, unique=True)
    password = models.CharField(max_length=64)

    name = models.CharField(max_length=32)
    surname = models.CharField(max_length=32)
    personal_id = models.CharField(max_length=32, unique=True)
    email = models.CharField(max_length=64, unique=True)

    isOrganizer = models.BooleanField(default=False)
