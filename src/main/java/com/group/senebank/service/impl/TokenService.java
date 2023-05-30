package com.group.senebank.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    @Value("${security.secret-key}")
    String secretKey;

    public String generateToken(String email, String role) {
        Claims claims = Jwts.claims();
        claims.put("email", email);
        claims.put("role", role);

        var expiryDate = new Date(System.currentTimeMillis() + 3600000);

        JwtBuilder builder = Jwts.builder();
        builder.setClaims(claims);
        builder.setExpiration(expiryDate);
        builder.signWith(SignatureAlgorithm.HS256, convertSecretKeyToBase64());
        return builder.compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(convertSecretKeyToBase64()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            // подпись токена недействительна
        } catch (MalformedJwtException ex) {
            // токен недействительный
        } catch (ExpiredJwtException ex) {
            // токен истек
        } catch (UnsupportedJwtException ex) {
            // тип токена не поддерживается
        } catch (IllegalArgumentException ex) {
            // токен пустой
        }
        return false;
    }

    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(convertSecretKeyToBase64()).parseClaimsJws(token).getBody();
        return claims.get("role", String.class);
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(convertSecretKeyToBase64()).parseClaimsJws(token).getBody();
        return claims.get("email", String.class);
    }

    private byte[] convertSecretKeyToBase64() {
        return Base64.encodeBase64(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
