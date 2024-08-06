package com.bti.login;

import com.bti.services.UserService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

@Component
@Scope("request")
public class LoginController implements Serializable {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    private String username;
    private String password;

    public String login() {
        logger.info("Attempting to log in with username: " + username);
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.login(username, passwordEncoder.encode(password));
            logger.info("Login successful for username: " + username);
            return "/main?faces-redirect=true";
        } catch (Exception e) {
            logger.severe("Login failed for username: " + username + ". Error: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", "Invalid username or password"));
            return null;
        }
    }

    public void logout() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        context.getExternalContext().redirect(request.getContextPath() + "/login");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}