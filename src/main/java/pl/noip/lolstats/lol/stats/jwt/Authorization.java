package pl.noip.lolstats.lol.stats.jwt;

import pl.noip.lolstats.lol.stats.jwt.JwtGenerator
import org.springframework.beans.factory.annotation.Autowired

public class Authorization {


    @Autowired
    JwtGenerator jwtGenerator
    private String bearer = jwtGenerator.generate();


    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

}
