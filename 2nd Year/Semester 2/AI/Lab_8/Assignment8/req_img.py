import requests


for i in range(0, 50):
    response = requests.get("https://fakeface.rest/face/json?gender=male")
    response = response.json()
    print(response["image_url"])
    response = requests.get(response["image_url"])

    file = open("test/male/" + str(i + 1) + ".png", "wb")
    file.write(response.content)
    file.close()

for i in range(0, 50):
    response = requests.get("https://fakeface.rest/face/json?gender=female")
    response = response.json()
    print(response["image_url"])
    response = requests.get(response["image_url"])

    file = open("test/female/" + str(i + 1) + ".png", "wb")
    file.write(response.content)
    file.close()


for i in range(0, 50):
    response = requests.get("https://api.thecatapi.com/v1/images/search")
    response = response.json()
    print(response[0]["url"])
    response = requests.get(response[0]["url"])

    file = open("test/cats/" + str(i + 1) + ".png", "wb")
    file.write(response.content)
    file.close()
