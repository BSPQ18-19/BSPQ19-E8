from django.db import models


class Person(models.Model):
    # TODO link with native User model
    username = models.CharField(max_length=32, unique=True)
    password = models.CharField(max_length=64)

    name = models.CharField(max_length=32)
    surname = models.CharField(max_length=32)
    personal_id = models.CharField(max_length=32, unique=True)
    birth_date = models.DateField()
    email = models.CharField(max_length=64, unique=True)

    def __str__(self):
        return '%s %s' % (self.name, self.surname)


class Race(models.Model):
    race_id = models.AutoField(primary_key=True)
    edition = models.CharField(max_length=32, unique=True)
    sponsor = models.CharField(max_length=32)
    place = models.CharField(max_length=32)
    time = models.DateTimeField()
    prize = models.IntegerField()
    price = models.IntegerField()

    organizer = models.ForeignKey(Person, on_delete=models.CASCADE, related_name="organizer")
    runners = models.ManyToManyField(Person, through='Runner')
    helpers = models.ManyToManyField(Person, related_name="helper")

    def __str__(self):
        return '%s - %s' % (self.edition, self.place)


class Runner(models.Model):
    person = models.ForeignKey(Person, on_delete=models.CASCADE)
    race = models.ForeignKey(Race, on_delete=models.CASCADE)
    number = models.IntegerField()
