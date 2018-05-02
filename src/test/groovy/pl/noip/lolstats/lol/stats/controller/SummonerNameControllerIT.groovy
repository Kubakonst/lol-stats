package pl.noip.lolstats.lol.stats.controller

import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import pl.noip.lolstats.lol.stats.jwt.JwtGeneratorImpl
import pl.noip.lolstats.lol.stats.repository.AccountRepository
import pl.noip.lolstats.lol.stats.time.TimeService
import spock.lang.Specification

import static io.restassured.RestAssured.given

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class SummonerNameControllerIT extends Specification {

    JwtGeneratorImpl jwtGenerator

    TimeService timeService = Mock()

    @LocalServerPort
    private int webPort

    @Autowired
    private AccountRepository repository

    private static final String PATH = "/api/summoner/name"
    private static final String AUTHORIZATION = "Authorization"

    def setup() {
        repository.deleteAll()
        jwtGenerator = new JwtGeneratorImpl(timeService)
        jwtGenerator.setSecret("dh1asg2fhksdf4jkla9edhgfk8jadsh7flas3dsdf4gbhjkfews5rrtweherhedrtf6gwetygedrgwergsed2rgwergwrfgwefwe")

    }

    def "Create token with correct data"() {
        given:"jwt is generated as second 1 and checked at second 2"
        timeService.millisSinceEpoch >>> [1000, 2000]
        def mail = "example@mail.com"
        def sumName = "ExampleName"
        def bearerToken = "bearer " + jwtGenerator.generate(mail)
        given()
                .port(webPort)
                .when()
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, bearerToken)
                .body([sumName: sumName])
                .post(PATH)
                .then()
                .statusCode(401)
                .body("accessToken", Matchers.containsString("."))
                .body("bearer", Matchers.containsString("bearer "))
    }

}
