package pl.noip.lolstats.lol.stats.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException;

@Component
@Slf4j
public class BearerTokenSeparatorImpl implements BearerTokenSeparator {

    @Override
    public String getToken(String bearerToken) {


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