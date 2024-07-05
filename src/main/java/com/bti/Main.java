package com.bti;

import jakarta.faces.webapp.FacesServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = "com.bti", excludeFilters = @Filter(type = FilterType.REGEX, pattern = "com.bti.ui.websockets.*"))
public class Main extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean<FacesServlet> servletRegistrationBean = new ServletRegistrationBean<>(servlet, "*.xhtml");
        servletRegistrationBean.setName("Faces Servlet");
        servletRegistrationBean.setAsyncSupported(true);
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }
}