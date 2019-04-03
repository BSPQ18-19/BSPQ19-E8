from django.db import models


class User(models.Model):
    username = models.CharField(max_length=32, unique=True)
    password = models.CharField(max_length=64)

    name = models.CharField(max_length=32)
    surname = models.CharField(max_length=32)
    personal_id = models.CharField(max_length=32, unique=True)
    email = models.CharField(max_length=64, unique=True)

    isOrganizer = models.BooleanField(default=False)
    isRunner = models.BooleanField(default=False)
    isVolunteer = models.BooleanField(default=False)

    def __str__(self):
        return self.user_text

    def create_race(self):
        return self.race
    def update_race_information(self):
        return self.info
    def register_as_user(self):
        return self.user
    def delete_from_race(self):
        return self.race
    def sign_up_for_a_task(self):
        return self.task
    def check_race_details(self):
        return self.race
    def set_up_for_a_race(self):
        return self.race



class Race(models.Model):
    edition= models.CharField(max_length=32, unique=True)
    sponsor = models.CharField(max_length=32)
    place = models.CharField(max_length=32)
    time = models.CharField(max_length=32)
    prize = models.CharField(max_length=32)
    price = models.CharField(max_length=32)

    def __str__(self):
        return self.race_text

