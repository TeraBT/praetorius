package com.darkwiki.configs;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomServletContextInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        sc.setInitParameter("jakarta.faces.PROJECT_STAGE", "Development");
        sc.setInitParameter("jakarta.faces.STATE_SAVING_METHOD", "server");
        sc.setInitParameter("jakarta.faces.FACELETS_SKIP_COMMENTS", "true");
        sc.setInitParameter("jakarta.faces.DEFAULT_SUFFIX", ".xhtml");
    }
}