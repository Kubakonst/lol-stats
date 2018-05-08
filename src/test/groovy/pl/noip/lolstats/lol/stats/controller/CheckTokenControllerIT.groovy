package pl.noip.lolstats.lol.stats.controller

import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import static io.restassured.RestAssured.given

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CheckTokenControllerIT extends Specification {
    @LocalServerPort
    private int webPort

    private static final String PATH = "/api/auth/checkToken"
    private static final String AUTHORIZATION = "Authorization"

    def "Token is expired"() {
        given:
        def bearerToken = "bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MjIyNDg2MjgsImV4cCI6MTUyMjI0ODYyOH0.NVmeysHPHEi-rjIwiju60vdmG4Yrr6kxpMcDY0etT1g"
        given()
                .port(webPort)
                .when()
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, bearerToken)
                .post(PATH)
                .then().statusCode(401)
                .body("error", Matchers.equalTo("token expired"))
    }

    def "Token has invelid format"() {
        given:
        def bearerToken = "bearer gg"
        given()
                .port(webPort)
                .when()
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, bearerToken)
                .post(PATH)
                .then().statusCode(401)
                .body("error", Matchers.equalTo("invalid token"))
    }

    def "Authorization header is empty"() {
        given:
        def bearerToken = " "
        given()
                .port(webPort)
                .when()
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, bearerToken)
                .post(PATH)
                .then().statusCode(401)
                .body("error", Matchers.equalTo("expected bearer authorization type"))
    }

    def "Authorization header is not present"() {
        given:
        given()
                .port(webPort)
                .when()
                .contentType(ContentType.JSON)
                .post(PATH)
                .then().statusCode(401)
                .body("error", Matchers.equalTo("expected bearer authorization type"))
    }

    def "Token has no bearer"() {
        given:
        def bearerToken = "eyJhbGciOiJIUzI1NiJ9eyJpYXQiOjEsImV4cCI6MzYwMX0.Q5HBk79pi0YzazlbpBT0kUXw8vwXcs76FcAw52DKaFI"
        given()
                .port(webPort)
                .when()
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, bearerToken)
                .post(PATH)
                .then().statusCode(401)
                .body("error", Matchers.equalTo("expected bearer authorization type"))
    }
}