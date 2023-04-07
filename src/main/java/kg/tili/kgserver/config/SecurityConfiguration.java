package kg.tili.kgserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author Markitanov Vadim
 * @since 07.04.2023
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("FILTER CHAIN");
        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests((authz) -> authz
//                        .requestMatchers("/get_user_info").fullyAuthenticated() // Показывает окно логина
                                .requestMatchers("/get_user_info", "/register_user").permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();
    }
}
