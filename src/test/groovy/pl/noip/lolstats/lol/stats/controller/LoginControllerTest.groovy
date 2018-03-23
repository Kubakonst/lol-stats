package pl.noip.lolstats.lol.stats.controller

import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import pl.noip.lolstats.lol.stats.dto.AccountRepository
import pl.noip.lolstats.lol.stats.model.Account
import spock.lang.Specification

import static io.restassured.RestAssured.given

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class LoginControllerTest extends Specification {
    @LocalServerPort
    private int webPort

    @Autowired
    private AccountRepository repository
    private static final String PATH = "/api/auth/login"

    def setup() {
        repository.deleteAll()
    }

    def "Login with correct credentials"() {
        given:
        def mail = "example@mail.com"
        def password = "secret"
        repository.save(new Account(mail, password))
        given()
                .port(webPort)
                .when()
                .contentType(ContentType.JSON)
                .body([email: mail, password: password])
                .post(PATH)
                .then().statusCode(200)
                .body("accessToken", Matchers.containsString("."))
                .body("bearer", Matchers.containsString("bearer "))
    }

    def "Login with incorrect credentials"() {
        given:
        def mail = "example@mail.com"
        def password = "secret"
        repository.save(new Account(mail, password))
        given()
                .port(webPort)
                .when()
                .contentType(ContentType.JSON)
                .body([email: mail, password: password])
                .post(PATH)
                .then().statusCode(401)
                .body("error", Matchers.equalTo("invalid email or password"))
    }
}