package pl.noip.lolstats.lol.stats.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noip.lolstats.lol.stats.jwt.Authorization;
import pl.noip.lolstats.lol.stats.jwt.JwtGenerator;
import pl.noip.lolstats.lol.stats.dto.ErrorResponse;

@RestController
@RequestMapping("/api/auth/login")
public class LoginController {

    @Autowired
    JwtGenerator jwtGenerator;

    public LoginController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody Authorization authorization) {
        if (jwtGenerator=authorization.getBearer());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("invalid email or password"), HttpStatus.UNAUTHORIZED);
    }
}
