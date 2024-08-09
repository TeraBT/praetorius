package com.bti.configs;

import com.bti.model.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String USER = Role.USER.name();
    private static final String VENDOR = Role.VENDOR.name();
    private static final String ADMIN = Role.ADMIN.name();
    private static final String LOGIN = "/login.xhtml";
    private static final String ACCESSDENIED = "/error/access_denied.xhtml";

    @Autowired
    DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        try {

            http
                    .csrf(csrf -> csrf.disable()) // TODO: Enable csrf and cors.
                    .cors(cors -> cors.disable())
//                    .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/main*")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/jakarta.faces.resource/**")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/error/**")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/sec/**")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/register")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/vendor/**")).hasAnyAuthority(VENDOR, ADMIN)
                            .requestMatchers(new AntPathRequestMatcher("/admin-tools/**")).hasAnyAuthority(ADMIN)
                            .anyRequest().authenticated())

                    // :TODO: Login failure failureUrl(/login.xhtml?error)
                    .formLogin(form -> form
                                    .loginPage(LOGIN)
                                    .permitAll()
                                    .loginProcessingUrl("/login")
//                            .defaultSuccessUrl("/main", false)
                                    .successHandler(new RedirectBackAuthenticationSuccessHandler())
                    )
                    .logout(logout -> logout
                            .logoutSuccessUrl(LOGIN)
                            .deleteCookies("JSESSIONID")
                            .invalidateHttpSession(true)
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    )
                    .sessionManagement(session -> session
//                                    .invalidSessionUrl("/error/invalid-session.xhtml")
                                    .invalidSessionUrl("/main")
                    );

            return http.build();
        } catch (Exception ex) {
            throw new BeanCreationException("Wrong spring security configuration", ex);
        }
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from praetorius_user where username=?")
                .authoritiesByUsernameQuery("select praetorius_user_username, role from user_role where praetorius_user_username=?");
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static class RedirectBackAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            if (defaultSavedRequest != null) {
                if (defaultSavedRequest.getRequestURI().equals("/RES_NOT_FOUND")) {
                    getRedirectStrategy().sendRedirect(request, response, "/main");
                } else {
                    getRedirectStrategy().sendRedirect(request, response, defaultSavedRequest.getRedirectUrl());
                }
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        }
    }
}
