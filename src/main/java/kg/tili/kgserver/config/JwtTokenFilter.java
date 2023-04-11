package kg.tili.kgserver.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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

    @Autowired
    private JwtUtils jwtUtils;

    @SneakyThrows
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) {
//        Authentication authentication = authenticationConfiguration.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken("admin", "admin"));
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null) {
            System.out.println("Authorization header is null.");
            filterChain.doFilter(request, response);
            return;
        }

        if (!header.startsWith("Bearer ")) {
            System.out.println("Authorization header not start with Bearer.");
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        boolean result = jwtUtils.validate(token);
        System.out.println("result: " + result);
        if (!result) {
            System.out.println("NOT VALID TOKEN!");
            filterChain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authenticationConfiguration.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken("admin", "admin")));
        filterChain.doFilter(request, response);
    }
}
