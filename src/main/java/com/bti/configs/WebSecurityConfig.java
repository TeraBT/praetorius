package com.bti.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain mainSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/void-api/**").permitAll()
                        .requestMatchers("/order-post.xhtml").permitAll()
                        .requestMatchers("/main/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of("http://localhost:8080"));
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))
                .formLogin(form -> form
//                        .loginPage("/login/login.xhtml")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/access-denied"));

        return http.build();
    }

//    @Bean
//    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .securityMatcher("/admin-tools/**")
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/admin-tools/**").authenticated())
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(request -> {
//                    var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
//                    corsConfiguration.setAllowedOrigins(List.of("http://localhost:8080"));
//                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//                    corsConfiguration.setAllowedHeaders(List.of("*"));
//                    corsConfiguration.setAllowCredentials(true);
//                    return corsConfiguration;
//                }))
//                .formLogin(form -> form
//                        .defaultSuccessUrl("/admin-tools", true)
//                        .permitAll())
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/admin/login?logout")
//                        .permitAll())
//                .exceptionHandling(exception -> exception
//                        .accessDeniedPage("/access-denied"));
//
//        return http.build();
//    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user1 = User.builder()
                .username("cbt")
                .password(encoder.encode("pTzR7"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1);
    }
}
