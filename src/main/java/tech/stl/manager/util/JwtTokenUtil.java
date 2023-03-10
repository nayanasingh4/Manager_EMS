package tech.stl.manager.util;

import java.security.SignatureException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import tech.stl.manager.entity.Manager;


@Component
public class JwtTokenUtil {
private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    
    private static final long EXPIRE_DURATION =  30 * 60 * 1000; // 30 minutes
    
    @Value("${app.jwt.secret}")
    private String SECRET_KEY;
    
    public String generateAccessToken(Manager user) {
        
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getManagerId(), user.getEmailId()))
                .setIssuer("CodeJava")
//                .claim("roles", user.getRole().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
    
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        }
        
        return false;
    }
    
    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }
    
    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
} 
