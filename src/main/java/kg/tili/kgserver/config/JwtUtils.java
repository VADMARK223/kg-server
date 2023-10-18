package kg.tili.kgserver.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Markitanov Vadim
 * @since 11.04.2023
 */
@Component
public class JwtUtils {
    @Value("${jwt.auth.secret.key}")
    private String secretKey;

    public String validate(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getIssuer();
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            System.out.println("ERROR: " + exception);
        }

        return null;
    }

    public String generateToken(String username) {
        return JWT.create().withIssuer(username).sign(Algorithm.HMAC256(secretKey));
    }
}
