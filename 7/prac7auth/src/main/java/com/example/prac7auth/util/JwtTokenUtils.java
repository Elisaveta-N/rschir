package com.example.prac7auth.util;

import com.example.prac7auth.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;


    public String generateToken(User user) {
        String accessToken = Jwts.builder()
                .setSubject("test")
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = user.getRoles().stream().map (elem -> elem.getName().toString())
                .collect(Collectors.toList());
        claims.put("roles", rolesList);

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());
        try {
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(user.getName())
                    .setIssuedAt(issuedDate)
                    .setExpiration(expiredDate)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
                    return token;
        }
        catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return "";
    }

/*    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }*/
}
