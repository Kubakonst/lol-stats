package pl.noip.lolstats.lol.stats.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.noip.lolstats.lol.stats.time.TimeService;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;


@Component
@Slf4j
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

    private String getData(String token, String claimName) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = secret.getBytes();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Jws<Claims> jwsClaims = Jwts.parser()
                .setClock(() -> new Date(timeService.getMillisSinceEpoch()))
                .setSigningKey(signingKey).parseClaimsJws(token);

        return jwsClaims.getBody().get(claimName).toString();

    }

    public String getName(String token) {


        String infoN = getData(token, "name");
        log.info("there is a user name in token");
        return infoN;

    }

    public String getMail(String token) {

        String infoM = getData(token, "email");
        log.info("there is a user email in token");
        return infoM;
    }

    public String getRegion(String token) {

        String ingoR = getData(token, "region");
        log.info("there is a user region in token");
        return ingoR;
    }
}