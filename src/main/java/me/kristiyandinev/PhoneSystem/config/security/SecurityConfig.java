package me.kristiyandinev.PhoneSystem.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                            auth.requestMatchers("/login").permitAll();
                            auth.requestMatchers("/register").permitAll();
                            auth.anyRequest().authenticated();
                        }
                )
                .formLogin(Customizer.withDefaults())

                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }*/
}
