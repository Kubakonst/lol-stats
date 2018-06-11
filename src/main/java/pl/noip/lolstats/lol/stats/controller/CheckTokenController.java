package pl.noip.lolstats.lol.stats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException;
import pl.noip.lolstats.lol.stats.jwt.JwtChecker;
import pl.noip.lolstats.lol.stats.jwt.TokenSplit;

@RestController
@RequestMapping("/api/auth/checkToken")
@Slf4j
public class CheckTokenController {

    private JwtChecker jwtChecker;
    private TokenSplit tokenSplit;


    public CheckTokenController(JwtChecker jwtChecker, TokenSplit tokenSplit) {
        this.jwtChecker = jwtChecker;
        this.tokenSplit = tokenSplit;

    }

    @PostMapping
    public void checkToken(@RequestHeader(required = false, value = "Authorization") String bearer) {

        if (bearer == null) {
            log.error("there is no bearer");
            throw new BearerNotPresentException();
        }

        String token = tokenSplit.splitToken(bearer);

        jwtChecker.checkToken(token);


    }

}
