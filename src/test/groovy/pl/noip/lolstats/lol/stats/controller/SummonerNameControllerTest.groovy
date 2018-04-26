package pl.noip.lolstats.lol.stats.controller

import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import pl.noip.lolstats.lol.stats.model.Account
import pl.noip.lolstats.lol.stats.repository.AccountRepository
import spock.lang.Specification

import static io.restassured.RestAssured.given

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class SummonerNameControllerTest extends Specification {
    @LocalServerPort
    private int webPort

    @Autowired
    private AccountRepository repository

    private static final String PATH = "/api/summoner/name"
    private static final String AUTHORIZATION = "Authorization"

    def setup() {
        repository.deleteAll()

    }

    def "Login with correct credentials"() {
        given:
        def bearerToken = "bearer eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImV4YW1wbGVAbWFpbC5jb20iLCJpYXQiOjE1MjQ3NDMyMzgsImV4cCI6MTUyNDc0NjgzOH0.NCnjXfyiYztn5Jba8pm4bBl5SLMS3xP6PbxaapX4fbk"
        def mail = "dashjukda@dha.pl"
        def sumName = "ExampleName"
        repository.save(new Account(mail, sumName))
        given()
                .port(webPort)
                .when()
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, bearerToken)
                .body([sumName: sumName])
                .post(PATH)
                .then()
                .statusCode(200)
                .body("accessToken", Matchers.containsString("."))
                .body("bearer", Matchers.containsString("bearer "))
    }

}
