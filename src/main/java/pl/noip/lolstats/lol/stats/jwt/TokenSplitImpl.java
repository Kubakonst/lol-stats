package pl.noip.lolstats.lol.stats.jwt;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException;

@Component
@Log
public class TokenSplitImpl implements TokenSplit {

    @Override
    public String splitToken(String bearerToken) {


        if (bearerToken.split(" ").length < 2) {
            throw new BearerNotPresentException();
        }

        if (!bearerToken.toLowerCase().startsWith("bearer ")) {
            throw new BearerNotPresentException();
        }

        log.info("token splited from bearer");
        return bearerToken.split(" ")[1];
    }
}