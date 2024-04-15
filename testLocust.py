from locust import HttpUser, task, between
import random
import json

class testLocust(HttpUser):
    wait_time = between(1, 2)

    def on_start(self):
        self.client.post("/users/sign-in", json={"userId": "test1",
                                                 "password": "123"})

    @task
    def add_post(self):
        self.client.post("/posts", json={
            "title": "테스트 게시글" + str(random.randint(1, 100000)),
            "contents": "테스트 컨텐츠" + str(random.randint(1, 100000)),
            "fileId": random.randint(1, 10),
        })