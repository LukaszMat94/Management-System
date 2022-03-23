package org.matusikl.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JWTTokenBuilder {

    private String jwtAccessToken;
    private String jwtRefreshToken;
    private static final Date expireAccessToken = new Date(System.currentTimeMillis() + 15 * 60 * 1000);
    private static final Date expireRefreshToken = new Date(System.currentTimeMillis() + 60 * 60 * 1000);
    private static final String keySecret = "Luc@s!#M1103";
    private static final Algorithm algorithm = Algorithm.HMAC256(keySecret.getBytes());

    private JWTTokenBuilder(String username, String issuer, String rolesName, List<?> claims){
        jwtAccessToken= JWT.create()
                .withSubject(username)
                .withExpiresAt(expireAccessToken)
                .withClaim(rolesName, claims)
                .withIssuer(issuer)
                .sign(algorithm);
    }

    private JWTTokenBuilder(String username, String issuer){
        jwtRefreshToken = JWT.create()
                .withSubject(username)
                .withExpiresAt(expireRefreshToken)
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public static String getAccessToken(String username, String issuer, String rolesName, List<?> claims){
        JWTTokenBuilder accessToken = new JWTTokenBuilder(username, issuer, rolesName, claims);
        return accessToken.jwtAccessToken;
    }

    public static String getRefreshToken(String username, String issuer){
        JWTTokenBuilder refreshToken = new JWTTokenBuilder(username, issuer);
        return refreshToken.jwtRefreshToken;
    }

    public static Map<String, List<String>> getDecodedToken(String token){
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        Map<String, List<String>> usernameWithRoles = new HashMap<>();
        usernameWithRoles.put(username, roles);
        return usernameWithRoles;
    }
}
