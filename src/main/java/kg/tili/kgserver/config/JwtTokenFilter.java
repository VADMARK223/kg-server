package kg.tili.kgserver.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * @author Markitanov Vadim
 * @since 11.04.2023
 */
@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) {
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
        String username = jwtUtils.validate(token);
        if (username == null) {
            System.out.println("NOT VALID TOKEN!");
            filterChain.doFilter(request, response);
            return;
        }

        authenticationUser(username);

        filterChain.doFilter(request, response);
    }

    private void authenticationUser(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
