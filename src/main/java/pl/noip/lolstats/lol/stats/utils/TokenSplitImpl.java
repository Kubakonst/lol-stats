package pl.noip.lolstats.lol.stats.utils;

import org.springframework.stereotype.Component;

@Component
public class TokenSplitImpl implements TokenSplit {

    @Override
    public String splitToken(String bearerToken) {

        if (!bearerToken.toLowerCase().startsWith("bearer ")){
            throw new NoBearerException();}

       return bearerToken.split(" ")[1];
    }
}