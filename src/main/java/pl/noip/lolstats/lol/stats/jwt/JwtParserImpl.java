package pl.noip.lolstats.lol.stats.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.noip.lolstats.lol.stats.time.TimeService;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;


@Component
public class JwtParserImpl implements JwtParser {

    @Value("${jwt.secret}")
    private String secret;
    private TimeService timeService;

    public JwtParserImpl(TimeService timeService) {
        this.timeService = timeService;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }


    public Claims getData(String token) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = secret.getBytes();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Jws<Claims> jwsClaims = Jwts.parser()
                .setClock(() -> new Date(timeService.getMillisSinceEpoch()))
                .setSigningKey(signingKey).parseClaimsJws(token);

        return jwsClaims.getBody();

    }

    @Override
    public String getName(Claims getData) {

        return getData.get("name").toString();

    }

    @Override
    public String getMail(Claims getData) {

        return getData.get("email").toString();

    }

}