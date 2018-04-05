package pl.noip.lolstats.lol.stats.jwt;

import org.springframework.stereotype.Component;
import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException;

@Component
public class TokenSplitImpl implements TokenSplit {

    @Override
    public String splitToken(String bearerToken) {

        if(bearerToken.isEmpty()){
            throw new BearerNotPresentException();
        }

        String[] splited = bearerToken.split(" ");


        if (splited.length<2){
            throw new BearerNotPresentException();
        }

        if (!bearerToken.toLowerCase().startsWith("bearer ")){
            throw new BearerNotPresentException();}


       return bearerToken.split(" ")[1];
    }
}