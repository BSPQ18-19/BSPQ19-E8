import json

from locust import HttpLocust, TaskSet, task


class UserBehaviour(TaskSet):

    def on_start(self):

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
        self.client.get("/logout/")

    @task(1)
    def get_users(self):
        response = self.client.get("/api/users/")
        user_data = json.loads(str(response.content, encoding='utf8'))

        for i in range(1, len(user_data) + 1):
            self.client.get("/api/users/%i/" % i)

    @task(1)
    def get_races(self):
        response = self.client.get("/api/races")
        race_data = json.loads(str(response.content, encoding='utf8'))

        for i in range(1, len(race_data) + 1):
            self.client.get("/api/races/%i/" % i)


class WebsiteUser(HttpLocust):
    task_set = UserBehaviour
    min_wait = 5000
    max_wait = 20000
