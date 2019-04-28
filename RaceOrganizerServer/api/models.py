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

    def get_simple_json(self):
        json = {"user_id": self.pk,
                "username": self.user.username,
                "first_name": self.user.first_name,
                "last_name": self.user.last_name,
                "email": self.user.email,
                }

        return json

    def get_json(self):
        json = {"user_id": self.pk, "username": self.user.username, "first_name": self.user.first_name,
                "last_name": self.user.last_name, "email": self.user.email, "birth_date": self.birth_date,
                "runner_races": [], "organizer_races": [], "helper_races": []}

        for runner_race in self.runner_set.all():
            json["runner_races"].append(runner_race.get_simple_race_json())

        for organizer_race in self.organizer.all():
            json["organizer_races"].append(organizer_race.get_simple_json())

        for helper_race in self.helper.all():
            json["helper_races"].append(helper_race.get_simple_json())

        return json

    def get_profile_json(self):
        json = self.get_json()
        json["personal_id"] = self.personal_id

        return json


class Race(models.Model):

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

    def get_simple_json(self):
        json = {"race_id": self.pk,
                "edition": self.edition,
                }

        return json

    def get_json(self):
        json = {
            "race_id": self.pk,
            "edition": self.edition,
            "sponsor": self.sponsor,
            "place": self.place,
            "time": self.time,
            "price": self.price,
            "prize": self.prize,
            "organizer": self.organizer.get_simple_json(),
            "runners": [],
            "helpers": []
        }

        for runner in self.runner_set.all():
            json["runners"].append(runner.get_simple_person_json())

        for helpers in self.helpers.all():
            json["helpers"].append(helpers.get_simple_json())

        return json


class Runner(models.Model):
    person = models.ForeignKey(Person, on_delete=models.CASCADE)
    race = models.ForeignKey(Race, on_delete=models.CASCADE)
    number = models.IntegerField()

    def get_simple_person_json(self):
        json = self.person.get_simple_json()
        json["number"] = self.number

        return json

    def get_simple_race_json(self):
        json = self.race.get_simple_json()
        json["number"] = self.number

        return json
