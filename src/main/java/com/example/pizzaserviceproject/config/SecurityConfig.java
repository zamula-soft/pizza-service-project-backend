package com.example.pizzaserviceproject.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public static NoOpPasswordEncoder getEncoder()
    {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Bean // for endpoints
    public SecurityFilterChain getChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
           auth -> auth
                   .requestMatchers(HttpMethod.GET, "/log*").authenticated()
                   .requestMatchers(HttpMethod.GET).permitAll()
                   .requestMatchers(HttpMethod.PUT).hasAnyRole("ADMIN")
                   .requestMatchers(HttpMethod.POST).hasAnyRole("ADMIN")
                   .requestMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")
                   .anyRequest().authenticated()
        )
                .httpBasic(Customizer.withDefaults())
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        ;

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager getManager(){
        //add users
        UserDetails admin = User.withUsername("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("user")
                .password("user")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);

    }

}
