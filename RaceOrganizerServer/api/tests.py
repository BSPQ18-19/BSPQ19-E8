from django.contrib.auth.models import User
from django.test import TestCase

from api.models import Person as UserAPI


class AuthenticationTest(TestCase):
    def setUp(self):
        username = "test"
        password = "test"

        self.credentials = {
            'username': username,
            'password': password}

        User.objects.create_user(**self.credentials)
        UserAPI.objects.create(username=username, password=password, name="Test", surname="McTesty",
                               personal_id="11111111T", email="test@test.com", isOrganizer=False)

    def test_get_user(self):
        user = {'username': 'test'}

        response = self.client.post('/api/user/', user)

        # Check that the response is 200
        self.assertEqual(response.status_code, 200)
        # Check if user has been created
        self.assertJSONEqual(
            str(response.content, encoding='utf8'),
            [{"username": "test", "name": "Test", "surname": "McTesty", "email": "test@test.com"}]
        )

    def test_get_profile(self):
        # Check if when not logged in response return 403
        response = self.client.post('/api/profile/')
        self.assertEqual(response.status_code, 401)
