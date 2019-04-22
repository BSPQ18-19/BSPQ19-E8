# Create your tests here.

from django.contrib.auth.models import User
from django.test import TestCase


class AuthenticationTest(TestCase):
    def setUp(self):
        self.credentials = {
            'username': 'testuser',
            'password': 'secret'
        }
        User.objects.create_user(**self.credentials)

    def test_signup_post_view(self):
        user = {
            'username': "laurence",
            'password': "1234",
            'first_name': "Laurence",
            'last_name': "Richard",
            'email': "laurence.richard@gmail.com",
            'personal_id': "11111111T",
            'birth_date': "2016-04-08"
        }

        response = self.client.post('/signup/', user)

        # Check that the response is 201
        self.assertEqual(response.status_code, 201)
        # Check if user has been created
        self.assertTrue(User.objects.filter(username="laurence").exists())

    def test_login_post_view(self):
        # login
        response = self.client.post('/login/', self.credentials)
        # Check that the response is 200 OK.
        self.assertEqual(response.status_code, 200)

        no_user = {
            'username': 'john',
            'password': 'doe'
        }
        # login
        response = self.client.post('/login/', no_user)
        # Check that the response is 200 OK.
        self.assertEqual(response.status_code, 401)
