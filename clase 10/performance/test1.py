from locust import HttpUser, between, task




class WebsiteUser(HttpUser):
    wait_time = between(5, 15)

    # def on_start(self):
    #     self.client.post("/cities")

    @task
    def index(self):
       self.client.post("/cities")
