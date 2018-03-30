package pl.noip.lolstats.lol.stats.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.noip.lolstats.lol.stats.utils.InvalidTokenException;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Component
public class JwtCheckerImpl implements JwtChecker{

    @Value("${jwt.secret}")
    private String secret;


    public void checkToken(String bearerToken) {
        System.out.println(bearerToken);
        String token = bearerToken.split(" ")[1];
        System.out.println(token);



            try {
                if (token.contains(".")){}
                else{
                throw new InvalidTokenException();}
            }
            catch (InvalidTokenException invalidTokenExpresion) {
                System.out.println("no chyba git");
            }




            //todo check that has "bearer " -> Badreq


            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            byte[] apiKeySecretBytes = secret.getBytes();
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            Jwts.parser().setSigningKey(signingKey).parse(token);


        }

}
