package com.darkwiki.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/void-api/**").permitAll()
                            .requestMatchers("/order-post.xhtml").permitAll()
                            .requestMatchers("/main/**").permitAll()
                            .anyRequest().authenticated())
                    .csrf(csrf -> csrf.disable())
                    .formLogin(form -> form
//                            .loginPage("/login")
//                            .defaultSuccessUrl("/", true)
                            .permitAll())
                    .logout(logout -> logout
//                            .logoutSuccessUrl("/login?logout")
                            .permitAll());

            return http.build();
        }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exceptio//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests(
//                        authorize -> authorize
//                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .defaultSuccessUrl("/landing-page/index.html", true)
//                )
//                .logout(logout -> logout
////                        .logoutSuccessUrl("/login?logout")
//                        .deleteCookies("JSESSIONID")
//                )
//                .sessionManagement(session -> session
////                        .invalidSessionUrl("/login?invalid")
//                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                        .sessionFixation().migrateSession()
//                        .maximumSessions(1)
////                                .expiredUrl("/login?expired")
////                )
//                .build();
//    }n {
//        http
////                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers(new AntPathRequestMatcher("/**")).authenticated()
//                                .anyRequest().permitAll())
//                .formLogin(formLogin ->
//                        formLogin
////                        .loginPage("/login")
////                                .permitAll()
//                                .defaultSuccessUrl("/", true))  // Configure redirection after login
//                .logout(logout ->
//                        logout
////                                .logoutUrl("/logout")
////                                .logoutSuccessUrl("/login?logout")  // Configure redirection after logout
//                                .deleteCookies("JSESSIONID")  // Ensure JSESSIONID cookie is deleted on logout
//                                .invalidateHttpSession(true))
//                .sessionManagement(sessionManagement ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Session policy
//                                .sessionFixation().migrateSession()
//                                .maximumSessions(1)
////                                .expiredUrl("/login?expired")
//                );  // Handling for expired sessions
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
                .username("darkwiki")
                .password(encoder.encode("pTzR7"))
                .roles("USER")
                .build();
        UserDetails user2 = User.builder()
                .username("cro")
                .password(encoder.encode("mo"))
                .roles("USER")
                .build();
        UserDetails user3 = User.builder()
                .username("sam")
                .password(encoder.encode("van"))
                .roles("USER")
                .build();
        UserDetails user4 = User.builder()
                .username("xan")
                .password(encoder.encode("stan"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, user3, user4);
    }
}



