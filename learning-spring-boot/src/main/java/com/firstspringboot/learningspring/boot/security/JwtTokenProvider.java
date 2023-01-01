package com.firstspringboot.learningspring.boot.security;

import java.io.Serializable;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.firstspringboot.learningspring.boot.exception.BlogAPIException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider implements Serializable {

    @Value("${app.jwt-security}")  // fetching values form application.properties
    private String jwtSecurity;
    @Value("${app.jwt-expiration-days}")
    private String jwtExpirationDate;

    public String generateJwtToken(Authentication authentication){
        String username = authentication.getName();
        Date currDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currDate);
        c.add(Calendar.DATE, Integer.parseInt(jwtExpirationDate));
        Date expirDate = c.getTime();

        return Jwts.builder().setSubject(username).setIssuedAt(currDate).setExpiration(expirDate).signWith(key()).compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecurity));
    }

    public String getUsername(String toke){
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(toke).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        } catch (MalformedJwtException exception){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT Token");
        } catch (ExpiredJwtException exception){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT Token");
        } catch (UnsupportedJwtException exception){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT Token");
        } catch (IllegalArgumentException exception){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
        }
    }
}
