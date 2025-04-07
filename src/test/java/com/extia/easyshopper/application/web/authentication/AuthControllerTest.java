package com.extia.easyshopper.application.web.authentication;

import com.extia.easyshopper.application.dto.request.LoginRequest;
import com.extia.easyshopper.application.dto.request.RegisterRequest;
import com.extia.easyshopper.stubs.IAuthStub;
import com.extia.easyshopper.utils.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Sql(scripts = "/sql/INSERT_USERS.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class AuthControllerTest extends BaseTest {

    @Test
    void shouldReturn400WhenLoginWithWrongPassword() {
        LoginRequest payload = IAuthStub.buildWrongPasswordLoginRequest();

        given()
            .body(payload)
        .when()
            .post("/auth/login")
        .then()
            .statusCode(400)
            .body("message", equalTo("The password given does not match."));
    }

    @Test
    void shouldReturn404WhenLoginAndUserNotFound() {
        LoginRequest payload = IAuthStub.buildInexistentLoginRequest();

        given()
            .body(payload)
        .when()
            .post("/auth/login")
        .then()
            .statusCode(404)
            .body("message", equalTo("The user was not found."));
    }

    @Test
    void shouldReturn200WhenLoginSuccessfully() {
        LoginRequest payload = IAuthStub.buildLoginRequest();

        given()
            .body(payload)
        .when()
            .post("/auth/login")
        .then()
            .statusCode(200)
            .body("name", equalTo("userone"))
            .body("token", notNullValue());
    }

    @Test
    void shouldReturn400WhenRegisterAndEmailAlreadyInUse() {
        RegisterRequest payload = IAuthStub.buildExistentUserRegisterRequest();

        given()
            .body(payload)
        .when()
            .post("/auth/register")
        .then()
            .statusCode(400)
            .body("message", equalTo("The email provided is already in use."));
    }

    @Test
    void shouldReturn201WhenRegisterSuccessfully() {
        RegisterRequest payload = IAuthStub.buildRegisterRequest();

        given()
            .body(payload)
        .when()
            .post("/auth/register")
        .then()
            .statusCode(201)
            .body("name", equalTo("usernew"))
            .body("token", notNullValue());
    }
}