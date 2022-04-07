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
    private static final String keySecretAccessToken = "Luc@s!#M1103";
    private static final String keySecretRefreshToken = "Luc@s!#M1104";
    private static final Algorithm algorithmAccessToken = Algorithm.HMAC256(keySecretAccessToken.getBytes());
    private static final Algorithm algorithmRefreshToken = Algorithm.HMAC256(keySecretRefreshToken.getBytes());

    private JWTTokenBuilder(String username, String rolesName, List<?> claims){
        jwtAccessToken= JWT.create()
                .withSubject(username)
                .withExpiresAt(expireAccessToken)
                .withClaim(rolesName, claims)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .sign(algorithmAccessToken);
    }

    private JWTTokenBuilder(String username){
        jwtRefreshToken = JWT.create()
                .withSubject(username)
                .withExpiresAt(expireRefreshToken)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .sign(algorithmRefreshToken);
    }

    public static String getAccessToken(String username, String rolesName, List<?> claims){
        JWTTokenBuilder accessToken = new JWTTokenBuilder(username, rolesName, claims);
        return accessToken.jwtAccessToken;
    }

    public static String getRefreshToken(String username){
        JWTTokenBuilder refreshToken = new JWTTokenBuilder(username);
        return refreshToken.jwtRefreshToken;
    }

    public static Map<String, List<String>> getDecodedAccessToken(String token){
        JWTVerifier verifier = JWT.require(algorithmAccessToken).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        Map<String, List<String>> usernameWithRoles = new HashMap<>();
        usernameWithRoles.put(username, roles);
        return usernameWithRoles;
    }

    public static String getDecodedRefreshToken(String token){
        JWTVerifier verifier = JWT.require(algorithmRefreshToken).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        return username;
    }
}
