////package com.bti.configs;
////
////import com.bti.services.CustomUserDetailsService;
////import org.springframework.login.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.authentication.AuthenticationProvider;
////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.session.HttpSessionEventPublisher;
////
////@Configuration
////@EnableWebSecurity
////public class WebSecurityConfig {
////
////    @Autowired
////    private CustomUserDetailsService customUserDetailsService;
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers(
////                                "/",
//////                                "/main.xhtml",
////                                "/sec/**",
////                                "/admin-tools/**",
////                                "/jakarta.faces.resource/**",
////                                "/register",
////                                "/login").permitAll()
////                        .anyRequest().authenticated())
////                .csrf(csrf -> csrf.disable())
////                .cors(cors -> cors.disable()
//////                        cors.configurationSource(request -> {
//////                    var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
//////                    corsConfiguration.setAllowedOrigins(List.of("http://localhost:8080"));
//////                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//////                    corsConfiguration.setAllowedHeaders(List.of("*"));
//////                    corsConfiguration.setAllowCredentials(true);
//////                    return corsConfiguration;
//////                })
////                )
////                .formLogin(form -> form
//////                        .loginPage("/sec/login.xhtml")
////                        .defaultSuccessUrl("/", true)
////                        .permitAll())
////                .logout(logout -> logout
//////                        .logoutSuccessUrl("/sec/login.xhtml")
////                        .logoutSuccessUrl("/")
////                        .permitAll())
////                .exceptionHandling(exception -> exception
////                        .accessDeniedPage("/access-denied"));
////
////        return http.build();
////    }
////
////    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(customUserDetailsService);
////        auth.authenticationProvider(authenticationProvider());
////    }
////
////    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////
////        auth
////                .userDetailsService(customUserDetailsService)
////                .passwordEncoder(passwordEncoder());
////    }
////
////    @Bean
////    public AuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
////        authenticationProvider.setUserDetailsService(customUserDetailsService);
////        authenticationProvider.setPasswordEncoder(passwordEncoder());
////        return authenticationProvider;
////    }
////
//////    @Bean
//////    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//////        return authenticationConfiguration.getAuthenticationManager();
//////    }
////
////    @Bean
////    public HttpSessionEventPublisher httpSessionEventPublisher() {
////        return new HttpSessionEventPublisher();
////    }
////}
//
//package com.bti.configs;
//
//import org.springframework.login.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.sql.DataSource;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig_old {
//
//    @Autowired
//    DataSource dataSource;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/sec/register.xhtml", "/jakarta.faces.resource/**").permitAll()
//                        .anyRequest().authenticated())
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(request -> {
//                    var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
//                    corsConfiguration.setAllowedOrigins(List.of("http://localhost:8080"));
//                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//                    corsConfiguration.setAllowedHeaders(List.of("*"));
//                    corsConfiguration.setAllowCredentials(true);
//                    return corsConfiguration;
//                }))
////                .formLogin(withDefaults())
////                .formLogin(form -> form
//////                        .loginPage("/sec/login.xhtml")
////                        .defaultSuccessUrl("/main.xhtml", true)
////                        .permitAll())
////                .logout(logout -> logout
////                        .logoutSuccessUrl("/sec/login.xhtml?logout")
////                        .permitAll())
////                .exceptionHandling(exception -> exception
////                        .accessDeniedPage("/access-denied")
//                .formLogin(form -> form
//                        .loginPage("/login.xhtml")
//                        .permitAll()
//                        .defaultSuccessUrl("/main.xhtml")
//                        .successForwardUrl("/main.xhtml")
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login.xhtml")
//                        .deleteCookies("JSESSIONID")
//                        .invalidateHttpSession(true)
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                )
//                .sessionManagement(session -> session
//                        .invalidSessionUrl("/error/invalid-session.xhtml")
//                );
//
//
//        return http.build();
//    }
//
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled from praetorius_user where username=?")
//                .authoritiesByUsernameQuery("select praetorius_user_username, role_set from user_role where username=?");
//    }
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
//
