from datetime import datetime

from django.contrib.auth.models import User
from django.test import TestCase

from api.models import Person as UserAPI


class AuthenticationTest(TestCase):
    def setUp(self):
        self.credentials = {
            'username': "laurence",
            'password': "1234",
            'first_name': "Laurence",
            'last_name': "Richard",
            'email': "laurence.richard@gmail.com"

        }

        user = User.objects.create_user(**self.credentials)
        UserAPI.objects.create(user=user,
                               personal_id="11111111T", birth_date=datetime.today())

    def test_get_user(self):
        user = {'username': 'laurence'}

        response = self.client.post('/api/user/', user)

        # Check that the response is 200
        self.assertEqual(response.status_code, 200)
        # Check if user has been created
        self.assertJSONEqual(
            str(response.content, encoding='utf8'),
            [{"username": "laurence", "first_name": "Laurence",
              "last_name": "Richard", "email": "laurence.richard@gmail.com"}]
        )

    def test_get_profile(self):
        # Check if when not logged in response return 302
        response = self.client.post('/api/profile/')
        self.assertEqual(response.status_code, 302)

        # Check if logged in
        self.client.login(username="laurence", password="1234")
        response = self.client.post('/api/profile/')
        self.assertJSONEqual(
            str(response.content, encoding='utf8'),
            [{'email': 'laurence.richard@gmail.com',
              'first_name': 'Laurence',
              'last_name': 'Richard',
              'person__birth_date': '2019-04-07',
              'person__personal_id': '11111111T',
              'username': 'laurence'}]
        )
