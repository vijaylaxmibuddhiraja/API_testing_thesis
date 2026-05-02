package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class JsonPlaceholderTests {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    // TC-01 GET single resource
    @Test
    @DisplayName("GET /posts/1 - should return post with id 1 and status 200")
    void shouldReturnPostWhenValidProvided() {
        // Using RestAssured - given-when-then pattern
        given()
                .log().all() // log request
                .when()
                .get("/posts/1")  // Send GET request to /posts
                .then()
                .log().all()  // log response
                .statusCode(200)         // verify status code
                .body("id", equalTo(1))
                .body("userId", equalTo(1));  // verify we get posts
    }

    // TC-02 POST - create resources
    @Test
    @DisplayName("POST /posts - should create new post and return status 201")
    void shouldCreateNewPostWhenValidDataSent() {
        String requestBody = """
            {
                "title": "test post",
                "body": "this is a test",
                "userId": 101
            }
             """;
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .log().all()
                .statusCode(201)
                .body("title", equalTo("test post"))
                .body("id", equalTo(101));

    }

    @Test
    @DisplayName("TC-03: PUT /posts/1 - should update post and return status 200")
    void shouldUpdatePostWhenValidDataProvided() {
        String requestBody = """
            {
                "id": 1,
                "title": "updated title",
                "body": "updated body",
                "userId": 1
            }
            """;

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/posts/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("title", equalTo("updated title"));
    }

    @Test
    @DisplayName("TC-04: GET /posts/9999 - should return status 404 when post does not exist")
    void shouldReturn404WhenPostDoesNotExist() {
        given()
                .log().all()
                .when()
                .get("/posts/9999")
                .then()
                .log().all()
                .statusCode(404);
    }

    @Test
    @DisplayName("TC-05: POST /api/login - should return token when credentials are valid")
    void shouldReturnTokenWhenCredentialsAreValid() {
        String requestBody = """
            {
                "email": "charles.morris@reqres.in",
                "password": "pistol"
            }
            """;

        given()
                .baseUri("https://reqres.in")
                .header("x-api-key", "free_user_3D1YdePONrqGNlt9oyWWTu1frlo")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().all()
                .when()
                .post("/api/login")
                .then()
                .log().all()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test
    @DisplayName("TC-06: POST /api/login - should return 400 when credentials are invalid")
    void shouldReturn400WhenCredentialsAreInvalid() {
        String requestBody = """
            {
                "email": "wrong@email.com",
                "password": "wrongpassword"
            }
            """;

        given()
                .baseUri("https://reqres.in")
                .header("x-api-key", "free_user_3D1YdePONrqGNlt9oyWWTu1frlo")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().all()
                .when()
                .post("/api/login")
                .then()
                .log().all()
                .statusCode(400);
    }

}


