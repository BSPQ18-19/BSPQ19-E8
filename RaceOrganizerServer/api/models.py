from django.contrib.auth.models import User
from django.db import models


class Person(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)

    personal_id = models.CharField(max_length=32, unique=True)
    birth_date = models.DateField()

    def __str__(self):
        return '%s %s' % (self.user.first_name, self.user.last_name)

    def get_username(self):
        return self.user.username

    def get_email(self):
        return self.user.email


class Race(models.Model):
    race_id = models.AutoField(primary_key=True)
    edition = models.CharField(max_length=32, unique=True)
    sponsor = models.CharField(max_length=32, blank=True)
    place = models.CharField(max_length=32)
    time = models.DateTimeField()
    prize = models.IntegerField()
    price = models.IntegerField()

    organizer = models.ForeignKey(Person, on_delete=models.CASCADE, related_name="organizer")
    runners = models.ManyToManyField(Person, through='Runner', blank=True)
    helpers = models.ManyToManyField(Person, related_name="helper", blank=True)

    def __str__(self):
        return '%s - %s' % (self.edition, self.place)


class Runner(models.Model):
    person = models.ForeignKey(Person, on_delete=models.CASCADE)
    race = models.ForeignKey(Race, on_delete=models.CASCADE)
    number = models.IntegerField()
