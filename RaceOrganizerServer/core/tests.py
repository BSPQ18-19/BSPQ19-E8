# Create your tests here.

from django.contrib.auth.models import User
from django.test import TestCase


class AuthenticationTest(TestCase):
    def setUp(self):
        self.credentials = {
            'username': 'testuser',
            'password': 'secret'}
        User.objects.create_user(**self.credentials)

    def test_signup(self):
        user = {
            'username': 'test',
            'password': 'test'}

        response = self.client.post('/signup/', user)

        # Check that the response is 200 OK.
        self.assertEqual(response.status_code, 201)
        self.assertTrue(User.objects.filter(username="test").exists())

    def test_login(self):
        # login
        response = self.client.post('/login/', **self.credentials)
        # Check that the response is 200 OK.
        self.assertEqual(response.status_code, 200)
