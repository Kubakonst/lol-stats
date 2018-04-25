package pl.noip.lolstats.lol.stats.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException;
import pl.noip.lolstats.lol.stats.jwt.JwtChecker;
import pl.noip.lolstats.lol.stats.jwt.JwtParser;
import pl.noip.lolstats.lol.stats.jwt.TokenSplit;

@RestController
@RequestMapping("/api/auth/checkToken")
public class CheckTokenController {

    private JwtChecker jwtChecker;
    private TokenSplit tokenSplit;
    private JwtParser jwtParser;

    public CheckTokenController(JwtChecker jwtChecker, TokenSplit tokenSplit, JwtParser jwtParser) {
        this.jwtChecker = jwtChecker;
        this.tokenSplit = tokenSplit;
        this.jwtParser = jwtParser;
    }

    @PostMapping
    public void checkToken(@RequestHeader(required = false, value = "Authorization") String bearer) {

        if (bearer == null) {
            throw new BearerNotPresentException();
        }

        String token = tokenSplit.splitToken(bearer);

        jwtChecker.checkToken(token);

        jwtParser.getmail(token);
    }

}
