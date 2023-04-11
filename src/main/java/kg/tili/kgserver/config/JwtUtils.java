package kg.tili.kgserver.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

/**
 * @author Markitanov Vadim
 * @since 11.04.2023
 */
@Component
public class JwtUtils {
    public String validate(String token) {
        System.out.println("Try verify token: " + token);
        try {
            Algorithm algorithm = Algorithm.HMAC256("SECRET");
            JWTVerifier verifier = JWT.require(algorithm)
                    // specify an specific claim validations
//                    .withIssuer("admin")
                    // reusable verifier instance
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            String issuer = decodedJWT.getIssuer();

            return issuer;
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            System.out.println("ERROR: " + exception);
        }

        return null;
    }
}
