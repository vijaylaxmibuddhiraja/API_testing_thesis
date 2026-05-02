import requests
from conftest import BASE_URL, REQRES_URL, API_KEY


# TC-01: GET single post - should return 200 with correct data
def test_get_post_returns_200():
    response = requests.get(f"{BASE_URL}/posts/1")
    
    assert response.status_code == 200
    assert response.json()["id"] == 1
    assert response.json()["userId"] == 1

# TC-02: POST create post - should return 201 with correct data
def test_should_create_new_post_when_valid_data_sent():
    payload = {
        "title": "test post",
        "body": "this is a test",
        "userId": 1
    }

    response = requests.post(f"{BASE_URL}/posts", json=payload)

    assert response.status_code == 201
    assert response.json()["title"] == "test post"
    assert response.json()["userId"] == 1    


# TC-03: PUT update post - should return 200 with updated data
def test_should_update_post_when_valid_data_provided():
    payload = {
        "id": 1,
        "title": "updated title",
        "body": "updated body",
        "userId": 1
    }

    response = requests.put(f"{BASE_URL}/posts/1", json=payload)

    assert response.status_code == 200
    assert response.json()["title"] == "updated title"
    assert response.json()["body"] == "updated body"

# TC-04: GET non-existent post - should return 404
def test_should_return_404_when_post_does_not_exist():
    response = requests.get(f"{BASE_URL}/posts/9999")

    assert response.status_code == 404

# TC-05: POST valid login - should return 200 with token
def test_should_return_token_when_credentials_are_valid():
    payload = {
        "email": "eve.holt@reqres.in",
        "password": "cityslicka"
    }
    headers = {
        "x-api-key": API_KEY
    }

    response = requests.post(
        f"{REQRES_URL}/api/login",
        json=payload,
        headers=headers
    )

    assert response.status_code == 200
    assert response.json()["token"] is not None
    assert response.json()["token"] != ""


# TC-06: POST invalid login - should return 400
def test_should_return_400_when_credentials_are_invalid():
    payload = {
        "email": "wrong@email.com",
        "password": "wrongpassword"
    }
    headers = {
        "x-api-key": API_KEY
    }

    response = requests.post(
        f"{REQRES_URL}/api/login",
        json=payload,
        headers=headers
    )

    assert response.status_code == 400