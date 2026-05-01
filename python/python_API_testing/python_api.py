import pytest
import requests

BASE_URL = "https://jsonplaceholder.typicode.com"
REQRES_URL = "https://reqres.in"
API_KEY = "free_user_3D1YdePONrqGNlt9oyWWTu1frlo"

@pytest.fixture
def base_url():
    return BASE_URL

@pytest.fixture
def reqres_url():
    return REQRES_URL

@pytest.fixture
def api_key():
    return API_KEY