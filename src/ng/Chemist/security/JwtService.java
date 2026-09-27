package ng.Chemist.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import ng.Chemist.Data.model.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtService {

    private final SecretKey secretKey;

    public JwtService(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createJwt(User user) {
        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("userName", user.getUserName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(secretKey)
                .compact();
    }

    public Claims verifyToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return verifyToken(token).get("userName", String.class);
    }

    public boolean isTokenValid(String token, String expectedUsername) {
        try {
            Claims claims = verifyToken(token);
            String username = claims.get("userName", String.class);
            Date expiration = claims.getExpiration();
            return username.equals(expectedUsername) && expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
