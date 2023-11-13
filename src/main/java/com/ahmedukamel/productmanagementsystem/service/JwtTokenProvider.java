package com.ahmedukamel.productmanagementsystem.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

import static com.ahmedukamel.productmanagementsystem.config.Constant.SECRET_KEY;


@Service
public class JwtTokenProvider {
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateJwtToken(Authentication authentication) {
        return Jwts.builder()
                .setIssuer("AhmedUKamel")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", authentication.getName())
                .claim("password", authentication.getCredentials().toString())
                .signWith(key)
                .compact();
    }

    public String getEmailFromJwtToken(String jwtToken) {
        String tokenBegin = jwtToken.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(tokenBegin).getBody();
        return String.valueOf(claims.get("email"));
    }

    public String getPasswordFromJwtToken(String jwtToken) {
        String tokenBegin = jwtToken.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(tokenBegin).getBody();
        return String.valueOf(claims.get("password"));
    }
}
