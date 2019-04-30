import random
import string

from locust import HttpLocust, TaskSet, task

REGISTERED = False


class UserBehaviour(TaskSet):
    username = ''.join(random.choice(
        string.ascii_uppercase + string.digits
    ) for _ in range(6))
    password = ''.join(random.choice(
        string.ascii_uppercase + string.digits
    ) for _ in range(6))

    token = None

    def on_start(self):
        # global REGISTERED
        #
        # if not REGISTERED:
        #     REGISTERED = True
        #     self.signup()

        self.login()

    def on_stop(self):
        self.logout()

    def signup(self):
        self.client.post("/signup/", {
            "username": self.username,
            "password": self.password
        })

    def login(self):
        self.client.post("/login/", {
            "username": "test",
            "password": "test"
        })

    def logout(self):
        self.logout()

    @task(1)
    def get_userlist(self):
        self.client.get("/api/users/")

    @task(1)
    def get_racelist(self):
        self.client.get("/api/races")


class WebsiteUser(HttpLocust):
    task_set = UserBehaviour
    min_wait = 5000
    max_wait = 20000
