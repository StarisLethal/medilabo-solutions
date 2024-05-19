package com.amenor.openclassrooms.msauthserv.service;

import com.amenor.openclassrooms.msauthserv.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final AuthService authService;
    private final String secretString = System.getenv("JWT_SECRET");
    private Key secretKey;

    public JwtTokenProvider(AuthService authService) {
        this.authService = authService;
    }

    @PostConstruct
    protected void init() {
        if (secretString == null) {throw new IllegalArgumentException("JWT secret is empty or null");}
            byte[] decodedKey = Base64.getDecoder().decode(secretString);
            secretKey = Keys.hmacShaKeyFor(decodedKey);

    }

    public String generateToken(String username, String roles) {
        Claims claims = Jwts.claims().subject(username).build();
        claims.put("roles", roles);

        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * 60 * 60);

        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
        Jws<Claims> claims = Jwts.parser()
                .verifyWith((SecretKey) secretKey)
                .build()
                .parseSignedClaims(token);
        return !claims.getPayload().get("roles").equals("admin");
    }catch (Exception e) {
        return false;
        }
    }

    public Authentication getAuthentication(String token) {
        User user = (User) authService.loadUserByUsername(getUsername(token));
        List<SimpleGrantedAuthority> authorities = getRoles(token).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(user, "", authorities);
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }

    public List<String> getRoles(String token) {
        Jws<Claims> claims = Jwts.parser()
                .verifyWith((SecretKey) secretKey)
                .build()
                .parseSignedClaims(token);
        return List.of(claims.getPayload().get("roles").toString().split(","));
    }
}
