package kg.tili.kgserver.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Markitanov Vadim
 * @since 11.04.2023
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    public AuthenticationConfiguration authenticationConfiguration;

    @SneakyThrows
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) {
//        Authentication authentication = authenticationConfiguration.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken("admin", "admin"));

        System.out.println("===JWT_TOKEN_FILTER===");
        SecurityContextHolder.getContext().setAuthentication(authenticationConfiguration.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken("admin", "admin")));
        System.out.println("request: " + request);
        System.out.println("response: " + response);
        System.out.println("===JWT_TOKEN_FILTER===");
        filterChain.doFilter(request, response);
    }
}
