package kg.tili.kgserver.config;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Markitanov Vadim
 * @since 11.08.2023
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Value("${client.address}")
    private String clientAddress;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns(clientAddress)
                        .allowedOrigins("OPTIONS", "GET", "POST", "PUT", "DELETE")
                        .allowCredentials(false);
            }
        };
    }
}
