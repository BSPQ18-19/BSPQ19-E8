import json

from django.contrib.auth.models import User
from django.test import TestCase

from api.models import Person, Race, Task


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

        self.credentials3 = {
            'username': "amy",
            'password': "1234",
            'first_name': "Amy",
            'last_name': "Santiago",
            'email': "a.santiago@gmail.com"
        }

        self.user3 = User.objects.create_user(**self.credentials3)
        self.person3 = Person.objects.create(user=self.user3,
                                             personal_id="33333333T", birth_date="1989-07-07")

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

        # self.race3 = {
        #     "edition": "Brooklyn's Charity Race",
        #     "sponsor": "99th Precinct",
        #     "place": "Brooklyn, NY",
        #     "time": "2019-04-27T16:58:10.926Z",
        #     "price": 150,
        #     "prize": 30
        # }
        #
        # self.race3 = Race.objects.create(organizer=self.person3, **self.race3)

        self.task1 = Task.objects.create(description="test", race=self.race2)
        self.task2 = Task.objects.create(description="test", race=self.race2, person=self.person1)
        self.task3 = Task.objects.create(description="test", race=self.race2, person=self.person3)

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

        # Test searching by username
        response = self.client.get('/api/users/?s=%s' % self.person1.user.username)
        self.assertEqual(response.status_code, 200)

        # Check if userlist now has 1 user instead of 2
        userlist = json.loads(str(response.content, encoding='utf8'))
        self.assertEqual(len(userlist), 1)

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

        # Test searching by edition
        response = self.client.get('/api/races/?s=%s' % self.race1.edition)
        self.assertEqual(response.status_code, 200)

        # Check if userlist now has 1 user instead of 2
        racelist = json.loads(str(response.content, encoding='utf8'))
        self.assertEqual(len(racelist), 1)

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
        nonexistent_runner = {
            "username": "I do not exists"
        }

        # Check failure when unauthorised
        response = self.client.post('/api/races/%i/add_runner' % self.race1.pk, self.person1.get_simple_json())
        self.assertEqual(response.status_code, 401)
        response = self.client.post('/api/races/%i/add_runner' % self.race1.pk, self.person1.get_simple_json())
        self.assertEqual(response.status_code, 401)

        self.client.login(username="laurence", password="1234")

        # Check failure when race or user do not exist
        response = self.client.post('/api/races/56/add_runner', self.person1.get_simple_json())
        self.assertEqual(response.status_code, 404)

        response = self.client.post('/api/races/%i/add_runner' % self.race2.pk, nonexistent_runner)
        self.assertEqual(response.status_code, 404)

        # Check failure when user does not have the permissions to add another user
        response = self.client.post('/api/races/%i/add_helper' % self.race2.pk, self.person2.get_simple_json())
        self.assertEqual(response.status_code, 403)

        # Check successful method calls
        response = self.client.post('/api/races/%i/add_runner' % self.race1.pk, self.person1.get_simple_json())
        self.assertEqual(response.status_code, 201)
        self.assertEqual(self.race1.runner_set.count(), 1)

        response = self.client.post('/api/races/%i/add_helper' % self.race1.pk, self.person2.get_simple_json())
        self.assertEqual(response.status_code, 201)
        self.assertEqual(self.race1.helpers.count(), 1)

        # Check failure to add duplicate users to race
        response = self.client.post('/api/races/%i/add_runner' % self.race1.pk, self.person1.get_simple_json())
        self.assertEqual(response.status_code, 400)

        response = self.client.post('/api/races/%i/add_helper' % self.race1.pk, self.person1.get_simple_json())
        self.assertEqual(response.status_code, 400)

    def test_add_tasks(self):
        new_task = {
            "description": "test_description"
        }

        empty_task = {
            "description": "       "
        }

        # Check failure when unauthorised
        response = self.client.post('/api/races/%i/new_task' % self.race2.pk, new_task)
        self.assertEqual(response.status_code, 401)

        self.client.login(username="laurence", password="1234")

        # Check failure when race or user do not exist
        response = self.client.post('/api/races/56/new_task', new_task)
        self.assertEqual(response.status_code, 404)

        # Check failure when user does not have the permissions to add another user
        response = self.client.post('/api/races/%i/new_task' % self.race2.pk, new_task)
        self.assertEqual(response.status_code, 403)

        # Check failure when empty description
        response = self.client.post('/api/races/%i/new_task' % self.race1.pk, empty_task)
        self.assertEqual(response.status_code, 400)

        # Check successful method calls
        race_count = self.race1.task_set.count()
        response = self.client.post('/api/races/%i/new_task' % self.race1.pk, new_task)
        self.assertEqual(response.status_code, 201)
        self.assertEqual(self.race1.task_set.count(), race_count + 1)

    def test_modify_task(self):
        new_description = {"description": "new description"}
        bad_description = {"description": "       "}
        assigned_task = {"username": self.user1.username}
        completed_task = {"completed": "true"}

        # Check failure when unauthorised
        response = self.client.put('/api/races/%i/%i/' % (self.race2.pk, self.task1.pk),
                                   {"username": self.user1.username})
        self.assertEqual(response.status_code, 401)

        # Check failure when user does not have permission
        self.client.login(username="laurence", password="1234")

        response = self.client.put('/api/races/%i/%i/' % (self.race2.pk, self.task1.pk), new_description)
        self.assertEqual(response.status_code, 403)

        response = self.client.put('/api/races/%i/%i/' % (self.race2.pk, self.task3.pk), completed_task)
        self.assertEqual(response.status_code, 403)

        response = self.client.put('/api/races/%i/%i/' % (self.race2.pk, self.task3.pk), assigned_task)
        self.assertEqual(response.status_code, 403)

        self.client.logout()
        self.client.login(username="john", password="1234")

        # Check failure when race or user do not exist
        response = self.client.put('/api/races/57/%i/' % self.task1.pk, {"username": self.user1.username})
        self.assertEqual(response.status_code, 404)
        response = self.client.put('/api/races/%i/57/' % self.race2.pk, {"username": self.user1.username})
        self.assertEqual(response.status_code, 404)

        # Check bad request
        response = self.client.put('/api/races/%i/%i/' % (self.race2.pk, self.task2.pk), bad_description)
        self.assertEqual(response.status_code, 400)

        response = self.client.put('/api/races/%i/%i/' % (self.race2.pk, self.task1.pk), completed_task)
        self.assertEqual(response.status_code, 400)

        # Check successful method calls
        # TODO these tests work manually but not in a test database, as such we will only test status_codes
        response = self.client.put('/api/races/%i/%i/' % (self.race2.pk, self.task1.pk), assigned_task)
        self.assertEqual(response.status_code, 200)

        response = self.client.put('/api/races/%i/%i/' % (self.race2.pk, self.task2.pk), new_description)
        self.assertEqual(response.status_code, 200)

        response = self.client.put('/api/races/%i/%i/' % (self.race2.pk, self.task2.pk), completed_task)
        self.assertEqual(response.status_code, 200)

    def test_delete_task(self):
        # Check failure when unauthorised
        response = self.client.put('/api/races/%i/%i/' % (self.race2.pk, self.task1.pk),
                                   {"username": self.user1.username})
        self.assertEqual(response.status_code, 401)

        # Check failure when user does not have permission
        self.client.login(username="laurence", password="1234")

        response = self.client.delete('/api/races/%i/%i/' % (self.race2.pk, self.task1.pk))
        self.assertEqual(response.status_code, 403)

        self.client.logout()
        self.client.login(username="john", password="1234")

        # Check failure when race or user do not exist
        response = self.client.delete('/api/races/57/%i/' % self.task1.pk, {"username": self.user1.username})
        self.assertEqual(response.status_code, 404)
        response = self.client.delete('/api/races/%i/57/' % self.race2.pk, {"username": self.user1.username})
        self.assertEqual(response.status_code, 404)

        # Check successful method calls
        race_count = self.race2.task_set.count()

        response = self.client.delete('/api/races/%i/%i/' % (self.race2.pk, self.task1.pk))
        self.assertEqual(response.status_code, 200)
        self.assertEqual(self.race2.task_set.count(), race_count - 1)
