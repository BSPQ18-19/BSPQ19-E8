# Create your tests here.

from django.contrib.auth.models import User
from django.test import TestCase


class AuthenticationTest(TestCase):
    def setUp(self):
        self.credentials = {
            'username': 'testuser',
            'password': 'secret'}
        User.objects.create_user(**self.credentials)

    def test_signup_post_view(self):
        user = {
            'username': 'test',
            'password': 'test'}

        response = self.client.post('/signup/post/', user)

        # Check that the response is 201
        self.assertEqual(response.status_code, 201)
        # Check if user has been created
        self.assertTrue(User.objects.filter(username="test").exists())

    def test_login_post_view(self):
        # login
        response = self.client.post('/login/post/', **self.credentials)
        # Check that the response is 200 OK.
        self.assertEqual(response.status_code, 200)
