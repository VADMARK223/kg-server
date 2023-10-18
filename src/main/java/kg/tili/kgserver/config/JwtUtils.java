package kg.tili.kgserver.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author Markitanov Vadim
 * @since 11.04.2023
 */
@Component
public class JwtUtils {
    private final SecretKeySpec secretKeySpec;

    public JwtUtils(@Value("${jwt.auth.secret.key}") String secretKey) {
        final byte[] apiKeySecretBytes = Base64.getDecoder().decode(secretKey);
        secretKeySpec = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public String validate(String token) {
        Claims claims = Jwts
                .parser()
                .verifyWith(secretKeySpec)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.get("login", String.class);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .claim("login", username)
                .signWith(secretKeySpec)
                .compact();
    }
}
