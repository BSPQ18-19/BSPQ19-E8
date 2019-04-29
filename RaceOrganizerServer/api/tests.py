import json

from django.contrib.auth.models import User
from django.test import TestCase

from api.models import Person, Race


class APITest(TestCase):
    def setUp(self):
        self.credentials1 = {
            'username': "laurence",
            'password': "1234",
            'first_name': "Laurence",
            'last_name': "Richard",
            'email': "laurence.richard@gmail.com"

        }

        self.user1 = User.objects.create_user(**self.credentials1)
        self.person1 = Person.objects.create(user=self.user1,
                                             personal_id="11111111T", birth_date="2019-04-07")

        self.credentials2 = {
            'username': "john",
            'password': "1234",
            'first_name': "John",
            'last_name': "Doe",
            'email': "john.doe@gmail.com"
        }

        self.user2 = User.objects.create_user(**self.credentials2)
        self.person2 = Person.objects.create(user=self.user2,
                                             personal_id="22222222T", birth_date="1989-07-07")

        self.race1 = {
            "edition": "Marathon",
            "sponsor": "Company",
            "place": "City",
            "time": "2019-04-27T16:58:10.926Z",
            "price": 0,
            "prize": 0
        }

        self.race1 = Race.objects.create(organizer=self.person1, **self.race1)

        self.race2 = {
            "edition": "Charity Race",
            "sponsor": "Company",
            "place": "City",
            "time": "2019-04-27T16:58:10.926Z",
            "price": 0,
            "prize": 0
        }

        self.race2 = Race.objects.create(organizer=self.person2, **self.race2)

    # User API tests

    def test_userlist(self):
        response = self.client.get('/api/users/')

        # Check that the response is 200
        self.assertEqual(response.status_code, 200)

        # Check if userlist has correct data
        userlist = json.loads(str(response.content, encoding='utf8'))

        self.assertEqual(userlist[0]["username"], self.person1.user.username)
        self.assertEqual(userlist[0]["first_name"], self.person1.user.first_name)
        self.assertEqual(userlist[0]["last_name"], self.person1.user.last_name)
        self.assertEqual(userlist[0]["email"], self.person1.user.email)

        self.assertEqual(userlist[1]["username"], self.person2.user.username)
        self.assertEqual(userlist[1]["first_name"], self.person2.user.first_name)
        self.assertEqual(userlist[1]["last_name"], self.person2.user.last_name)
        self.assertEqual(userlist[1]["email"], self.person2.user.email)

    def test_get_user(self):
        # Check if method returns correct information
        response = self.client.get('/api/users/%i/' % self.person2.pk)

        # Check that the response is 200
        self.assertEqual(response.status_code, 200)

        # Check if user_data has correct data
        user_data = json.loads(str(response.content, encoding='utf8'))
        self.assertEqual(user_data["username"], self.person2.user.username)
        self.assertEqual(user_data["first_name"], self.person2.user.first_name)
        self.assertEqual(user_data["last_name"], self.person2.user.last_name)
        self.assertEqual(user_data["email"], self.person2.user.email)
        self.assertEqual(user_data["birth_date"], self.person2.birth_date)

        # Check method for nonexistent user
        response = self.client.get('/api/users/56/')
        # Check that the response is 404
        self.assertEqual(response.status_code, 404)

    def test_get_profile(self):
        # Check if when not logged in response return 401
        response = self.client.get('/api/profile/')
        self.assertEqual(response.status_code, 401)

        # Check when method logged in
        self.client.login(username="laurence", password="1234")
        response = self.client.get('/api/profile/')

        # Check that the response is 200
        self.assertEqual(response.status_code, 200)

        # Check if profile_data has correct data
        profile_data = json.loads(str(response.content, encoding='utf8'))
        self.assertEqual(profile_data["username"], self.person1.user.username)
        self.assertEqual(profile_data["first_name"], self.person1.user.first_name)
        self.assertEqual(profile_data["last_name"], self.person1.user.last_name)
        self.assertEqual(profile_data["email"], self.person1.user.email)
        self.assertEqual(profile_data["birth_date"], self.person1.birth_date)
        self.assertEqual(profile_data["personal_id"], self.person1.personal_id)

    # Race API tests

    def test_racelist(self):
        response = self.client.get('/api/races/')

        # Check that the response is 200
        self.assertEqual(response.status_code, 200)

        # Check if racelist has correct data
        racelist = json.loads(str(response.content, encoding='utf8'))

        self.assertEqual(racelist[0]["race_id"], self.race1.pk)
        self.assertEqual(racelist[0]["edition"], self.race1.edition)

        self.assertEqual(racelist[1]["race_id"], self.race2.pk)
        self.assertEqual(racelist[1]["edition"], self.race2.edition)

    def test_create_race(self):
        race = {
            "edition": "Half Marathon Bilbao",
            "sponsor": "Deusto",
            "place": "Bilbao",
            "time": "2019-04-27T16:58:10.926Z",
            "price": 0,
            "prize": 0
        }

        # Check failure when not logged in
        response = self.client.post('/api/races/', race)
        # Check that the response is 401
        self.assertEqual(response.status_code, 401)

        # Check successful method call
        self.client.login(username="laurence", password="1234")
        response = self.client.post('/api/races/', race)
        self.assertEqual(response.status_code, 201)

        # Check if Race has been created
        self.assertTrue(Race.objects.filter(edition="Half Marathon Bilbao").exists())

        # Check failure to duplicate Race
        response = self.client.post('/api/races/', race)
        self.assertEqual(response.status_code, 400)

    def test_get_race(self):
        # Check if method returns correct information
        response = self.client.get('/api/races/%i/' % self.race2.pk)

        # Check that the response is 200
        self.assertEqual(response.status_code, 200)

        # Check if user_data has correct data
        race_data = json.loads(str(response.content, encoding='utf8'))
        self.assertEqual(race_data["race_id"], self.race2.pk)
        self.assertEqual(race_data["edition"], self.race2.edition)
        self.assertEqual(race_data["sponsor"], self.race2.sponsor)
        self.assertEqual(race_data["place"], self.race2.place)
        self.assertEqual(race_data["organizer"]["user_id"], self.race2.organizer.pk)
        self.assertEqual(race_data["organizer"]["username"], self.race2.organizer.user.username)

        # Check method for nonexistent race
        response = self.client.get('/api/races/56/')
        # Check that the response is 404
        self.assertEqual(response.status_code, 404)

    def test_add_people_to_races(self):
        runner = {
            "role": "runner",
            "username": self.person1.user.username
        }

        helper = {
            "role": "helper",
            "username": self.person2.user.username
        }

        duplicate_helper = {
            "role": "helper",
            "username": self.person1.user.username
        }

        nonexistent_runner = {
            "role": "runner",
            "username": "I do not exists"
        }

        invalid_role = {
            "role": "icecream man",
            "username": self.person1.user.username
        }

        # Check failure when unauthorised
        response = self.client.post('/api/races/%i/' % self.race1.pk, runner)
        self.assertEqual(response.status_code, 401)

        self.client.login(username="laurence", password="1234")

        # Check failure when race or user do not exist
        response = self.client.post('/api/races/56/', runner)
        self.assertEqual(response.status_code, 404)

        response = self.client.post('/api/races/%i/' % self.race2.pk, nonexistent_runner)
        self.assertEqual(response.status_code, 404)

        # Check failure when user does not have the permissions to add another user
        response = self.client.post('/api/races/%i/' % self.race2.pk, helper)
        self.assertEqual(response.status_code, 403)

        # Check invalid role failure
        response = self.client.post('/api/races/%i/' % self.race1.pk, invalid_role)
        self.assertEqual(response.status_code, 400)

        # Check successful method calls
        response = self.client.post('/api/races/%i/' % self.race1.pk, runner)
        self.assertEqual(response.status_code, 201)
        self.assertEqual(self.race1.runner_set.count(), 1)

        response = self.client.post('/api/races/%i/' % self.race1.pk, helper)
        self.assertEqual(response.status_code, 201)
        self.assertEqual(self.race1.helpers.count(), 1)

        # Check failure to add duplicate users to race
        response = self.client.post('/api/races/%i/' % self.race1.pk, runner)
        self.assertEqual(response.status_code, 400)

        response = self.client.post('/api/races/%i/' % self.race1.pk, duplicate_helper)
        self.assertEqual(response.status_code, 400)
