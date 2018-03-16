package pl.noip.lolstats.lol.stats.controller

import io.restassured.http.ContentType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import pl.noip.lolstats.lol.stats.dto.Repository
import spock.lang.Specification
import static io.restassured.RestAssured.*
import static org.hamcrest.Matchers.equalTo

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegistrationControllerTest extends Specification {

    @LocalServerPort
    private int webPort;

    @Autowired
    private Repository repository;

    def setup() {
        repository.deleteAll()
    }

    def "Register account and return 201 status code"() {
        when:
            given()
            .port(webPort)
                    .when()
                    .contentType(ContentType.JSON)
                    .body([email : "example@mail.com", password: "secret"])
                    .post("/register")
            .then().statusCode(201)
        then:
        repository.count() == 1
        repository.findOne("example@mail.com")
    }

    def "400 response code for invalid email"() {
        given:
        given()
                .port(webPort)
                .when()
                .contentType(ContentType.JSON)
                .body([email : "example", password: "secret"])
                .post("/register")
                .then()
                .statusCode(400)
                .body("error", equalTo("invalid email"))
    }

    def "400 response code for 2 short password"() {
        given:
        given()
                .port(webPort)
                .when()
                .contentType(ContentType.JSON)
                .body([email : "example@mail.com", password: "sec"])
                .post("/register")
                .then().statusCode(400)
                .body("error", equalTo("too short password"))
    }

    def "Two times create with same email, first 201, then 400 response"() {
        given:
        given()
                .port(webPort)
                .when()
                .contentType(ContentType.JSON)
                .body([email : "example@mail.com", password: "secret"])
                .post("/register")
                .then().statusCode(201)

        given()
                .port(webPort)
                .when()
                .contentType(ContentType.JSON)
                .body([email : "example@mail.com", password: "secret"])
                .post("/register")
                .then().statusCode(400)
                .body("error", equalTo("email already exists"))
    }

}
