package pl.noip.lolstats.lol.stats.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.noip.lolstats.lol.stats.time.TimeService;
import pl.noip.lolstats.lol.stats.utils.NoBearerException;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtCheckerImpl implements JwtChecker{

    @Value("${jwt.secret}")
    private String secret;

    public void setSecret(String secret) {
        this.secret = secret;
    }

    private TimeService timeService;

    public JwtCheckerImpl(TimeService timeService) {
        this.timeService = timeService;
    }

    public void checkToken(String bearerToken) {

        if (!bearerToken.startsWith("Bearer ")){
            throw new NoBearerException();}

        String token = bearerToken.split(" ")[1];

            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            byte[] apiKeySecretBytes = secret.getBytes();
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            Jwts.parser()
                    .setClock( () -> new Date(timeService.getMillisSinceEpoch()) )
                    .setSigningKey(signingKey).parse(token);

        }
}
