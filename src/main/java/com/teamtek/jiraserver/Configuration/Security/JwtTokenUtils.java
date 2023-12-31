package com.teamtek.jiraserver.Configuration.Security;

import com.teamtek.jiraserver.Model.Users;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtTokenUtils {
    private static final long EXPIRE_DURATION=24*60*60*1000;
    private String SECRET_KEY="aman16969pratyush7183ashish";

    public String generateAccessToken(Users users){
        return Jwts.builder()
                .setSubject(String.format("%s %s",users.getId(),users.getEmail()))
                .setIssuer("Team-tek")
                .claim("role","USER")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(JwtTokenUtils.class);

    public boolean validateAccessToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }
        catch (ExpiredJwtException ex){
            LOGGER.error("JWT expired", ex.getMessage());
        }
        catch (IllegalArgumentException ex){
            LOGGER.error("Token is null, empty of only whitespace", ex.getMessage());
        }
        catch (MalformedJwtException ex){
            LOGGER.error("JWT is invalid", ex);
        }
        catch (UnsupportedJwtException ex){
            LOGGER.error("JWT is not supported", ex);
        }
        catch (SignatureException ex){
            LOGGER.error("Signature validation failed");
        }
        return false;
    }

    public String getSubject(String token){
        return parseClaims(token).getSubject();
    }

    Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}