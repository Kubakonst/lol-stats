package pl.noip.lolstats.lol.stats.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noip.lolstats.lol.stats.jwt.JwtChecker;
import pl.noip.lolstats.lol.stats.utils.TokenSplit;

@RestController
@RequestMapping("/api/auth/checkToken")
public class CheckTokenController {

    private JwtChecker jwtChecker;
    private TokenSplit tokenSplit;

    public CheckTokenController(JwtChecker jwtChecker, TokenSplit tokenSplit) {
        this.jwtChecker = jwtChecker;
        this.tokenSplit = tokenSplit;
    }

    @PostMapping
    public void checkToken(@RequestHeader("Authorization") String bearer) {

        String token = tokenSplit.splitToken(bearer);

        jwtChecker.checkToken(token);
    }

}
