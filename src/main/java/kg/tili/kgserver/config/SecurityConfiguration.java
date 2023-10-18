package kg.tili.kgserver.config;

import kg.tili.kgserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author Markitanov Vadim
 * @since 07.04.2023
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        http.cors();
        http
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/login_user").permitAll()
                        .requestMatchers("/save_word", "/delete_word", "/get_dic", "/register_user").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin().loginPage("/login").and()
                .httpBasic(withDefaults());

        http.addFilterBefore(new JwtTokenFilter(jwtUtils, userService), BasicAuthenticationFilter.class);

        return http.build();
    }
}
