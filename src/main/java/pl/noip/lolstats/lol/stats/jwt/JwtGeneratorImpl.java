package pl.noip.lolstats.lol.stats.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.noip.lolstats.lol.stats.time.TimeService;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtGeneratorImpl implements JwtGenerator {

    @Value("${jwt.secret}")
    private String secret;
    private TimeService timeService;

    public JwtGeneratorImpl(TimeService timeService) {
        this.timeService = timeService;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String generate(String email) {

        return generate(email, null);
    }

    @Override
    public String generate(String email, String sumName) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = timeService.getMillisSinceEpoch();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = secret.getBytes();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .claim("name", sumName)
                .claim("email",email)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey)//
                .setExpiration(new Date(now.getTime() + 60 * 60 * 1000));

        return builder.compact();
    }
}

