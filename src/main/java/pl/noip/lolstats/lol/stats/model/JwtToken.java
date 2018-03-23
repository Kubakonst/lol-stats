package pl.noip.lolstats.lol.stats.model;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.impl.crypto.MacProvider;
//import java.security.Key;
//
//
//public class JwtToken {
//    Key key = MacProvider.generateKey();
//
//    String compactJws = Jwts.builder()
//            .setSubject("Joe")
//            .signWith(SignatureAlgorithm.HS512, key)
//            .compact();
//
//
//
//assert Jwts.parser().
//
//    setSigningKey(key).
//
//    parseClaimsJws(compactJws).
//
//    getBody().
//
//    getSubject().
//
//    equals("Joe");
//
//}

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class JwtToken {
    //Sample method to construct a JWT
    public String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = "anything".getBytes();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }
}
//    private void parseJWT(String jwt) {
//
//        //This line will throw an exception if it is not a signed JWS (as expected)
//        Claims claims = Jwts.parser()
//                .setSigningKey(DatatypeConverter.parseBase64Binary(apiKey.getSecret()))
//                .parseClaimsJws(jwt).getBody();
//        System.out.println("ID: " + claims.getId());
//        System.out.println("Subject: " + claims.getSubject());
//        System.out.println("Issuer: " + claims.getIssuer());
//        System.out.println("Expiration: " + claims.getExpiration());
//    }