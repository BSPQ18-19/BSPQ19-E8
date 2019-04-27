from django.contrib.auth.models import User
from django.test import TestCase

from api.models import Person, Race


class APITest(TestCase):
    def setUp(self):
        credentials1 = {
            'username': "laurence",
            'password': "1234",
            'first_name': "Laurence",
            'last_name': "Richard",
            'email': "laurence.richard@gmail.com"

        }

        user1 = User.objects.create_user(**credentials1)
        self.person1 = Person.objects.create(user=user1,
                                             personal_id="11111111T", birth_date="2019-04-07")

        credentials2 = {
            'username': "john",
            'password': "1234",
            'first_name': "John",
            'last_name': "Doe",
            'email': "john.doe@gmail.com"
        }

        user2 = User.objects.create_user(**credentials2)
        self.person2 = Person.objects.create(user=user2,
                                             personal_id="22222222T", birth_date="1989-07-07")

        race = {
            "edition": "Test",
            "sponsor": "Test",
            "place": "Tes",
            "time": "2019-04-27T16:58:10.926Z",
            "price": 0,
            "prize": 0
        }

        self.race = Race.objects.create(organizer=self.person2, **race)

    def test_get_users(self):
        response = self.client.post('/api/users/')

        # Check that the response is 200
        self.assertEqual(response.status_code, 200)

        print(str(response.content, encoding='utf8'))

        # Check if user has been created
        # TODO sometimes pk do not match
        self.assertJSONEqual(
            str(response.content, encoding='utf8'),
            [{"pk": 1, "username": "laurence", "first_name": "Laurence", "last_name": "Richard",
              "email": "laurence.richard@gmail.com"},
             {"pk": 2, "username": "john", "first_name": "John", "last_name": "Doe", "email": "john.doe@gmail.com"}]
        )

    def test_get_profile(self):
        # Check if when not logged in response return 302
        response = self.client.post('/api/profile/')
        self.assertEqual(response.status_code, 302)

        # Check if logged in
        self.client.login(username="laurence", password="1234")
        response = self.client.post('/api/profile/')

        print(str(response.content, encoding='utf8'))

        self.assertJSONEqual(
            str(response.content, encoding='utf8'),
            [{'email': 'laurence.richard@gmail.com',
              'first_name': 'Laurence',
              'last_name': 'Richard',
              'person__birth_date': '2019-04-07',
              'person__personal_id': '11111111T',
              'username': 'laurence'}]
        )

    def test_create_race(self):
        race = {
            "edition": "Test Race",
            "sponsor": "Deusto",
            "place": "Bilbao",
            "time": "2019-04-27T16:58:10.926Z",
            "price": 0,
            "prize": 0
        }

        response = self.client.post('/api/races/', race)
        # Check that the response is 403
        self.assertEqual(response.status_code, 403)

        self.client.login(username="laurence", password="1234")

        response = self.client.post('/api/races/', race)
        self.assertEqual(response.status_code, 200)
        # Check if Race has been created
        self.assertTrue(Race.objects.filter(edition="Test Race").exists())

    def test_add_people_to_races(self):
        runner = {
            "role": "runner",
            "username": self.person1.user.username
        }

        response = self.client.post('/api/races/%i/' % self.race.pk, runner)
        self.assertEqual(response.status_code, 200)
        self.assertEqual(self.race.runner_set.count(), 1)

        helper = {
            "race_id": self.race.pk,
            "role": "helper",
            "username": self.person1.user.username
        }

        response = self.client.post('/api/races/%i/' % self.race.pk, helper)
        self.assertEqual(response.status_code, 200)
        self.assertEqual(self.race.helpers.count(), 1)
