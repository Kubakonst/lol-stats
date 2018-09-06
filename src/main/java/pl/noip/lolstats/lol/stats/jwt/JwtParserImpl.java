package pl.noip.lolstats.lol.stats.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.noip.lolstats.lol.stats.Exceptions.MissingTokenPropertyException;
import pl.noip.lolstats.lol.stats.model.JwtInfo;
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

    @Override
    public JwtInfo jwtInfo(String token) {
        Claims claims = getClaims(token);
        return JwtInfo.builder()
                .name(getValueOrEmpty(claims, "name"))
                .email(getValueOrEmpty(claims, "email"))
                .region(getValueOrEmpty(claims, "region"))
                .token(token)
                .build();
    }

    private String getData(String token, String claimName) {
        Claims claims = getClaims(token);
        if (!claims.containsKey(claimName)) {
            throw new MissingTokenPropertyException(claimName + " is missing in token");
        }
        return claims.get(claimName).toString();
    }

    private Claims getClaims(String token) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = secret.getBytes();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Jws<Claims> jwsClaims = Jwts.parser()
                .setClock(() -> new Date(timeService.getMillisSinceEpoch()))
                .setSigningKey(signingKey).parseClaimsJws(token);
        return jwsClaims.getBody();
    }

    private String getValueOrEmpty(Claims claims, String name) {
        if (!claims.containsKey(name)) {
            return "";
        }
        return claims.get(name, String.class);
    }
}