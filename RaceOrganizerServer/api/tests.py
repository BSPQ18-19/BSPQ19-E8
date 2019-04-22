from django.contrib.auth.models import User
from django.test import TestCase

from api.models import Person as UserAPI


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
        UserAPI.objects.create(user=user1,
                               personal_id="11111111T", birth_date="2019-04-07")

        credentials2 = {
            'username': "john",
            'password': "1234",
            'first_name': "John",
            'last_name': "Doe",
            'email': "john.doe@gmail.com"
        }

        user2 = User.objects.create_user(**credentials2)
        UserAPI.objects.create(user=user2,
                               personal_id="22222222T", birth_date="1989-07-07")

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
