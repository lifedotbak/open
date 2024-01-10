package com.spyker.framework.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.spyker.framework.util.date.ExDateUtils;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Slf4j
public final class JwtUtils {

    private static final String SECRET = "app-spyker-jwt";

    private static final String ISSUER = "spyker";

    public static void main(String[] args) {
        Map<String, String> par = new HashMap<>();
        par.put("name", "xxxx");
        par.put("value", "xxxx");

        String token = genToken(par);

        System.out.println(token);

        Map<String, String> re = new HashMap<>();

        re = verifyToken(token);

        log.info("re--->{}", re);
    }

    public static String genToken(Map<String, String> claims) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTCreator.Builder builder =
                    JWT.create()
                            .withIssuer(ISSUER)
                            .withExpiresAt(ExDateUtils.addDays(new Date(), 365));
            claims.forEach((k, v) -> builder.withClaim(k, v));
            return builder.sign(algorithm);
        } catch (Exception e) {

            log.error("genToken--->{}", e);

            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> verifyToken(String token) {
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(SECRET);
        } catch (Exception e) {

            log.error("verifyToken--->{}", e);

            throw new RuntimeException(e);
        }
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> map = jwt.getClaims();
        Map<String, String> resultMap = new HashMap<String, String>();
        map.forEach((k, v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }
}