package pl.noip.lolstats.lol.stats.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noip.lolstats.lol.stats.jwt.JwtChecker;

@RestController
@RequestMapping("/api/auth/checkToken")
public class CheckTokenController {

    private JwtChecker jwtChecker;

    public CheckTokenController(JwtChecker jwtChecker) {
        this.jwtChecker = jwtChecker;
    }

    @PostMapping
    public void checkToken(@RequestHeader("Authorization") String token) {

        jwtChecker.checkToken(token);
    }
}
