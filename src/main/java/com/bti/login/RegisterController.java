package com.bti.login;

import com.bti.model.Role;
import com.bti.model.User;
import com.bti.services.UserService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;

@Component
@Scope("request")
public class RegisterController implements Serializable {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String username;

    private String password;

    private String confirmPassword;


    public String register() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!password.equals(confirmPassword)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password Error", "Passwords do not match"));
            return null;
        }

        if (userService.getByUsername(username).isPresent()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username Error", "Username already exists"));
            return null;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.getRoleSet().add(Role.USER);
        userService.saveUser(user);

        return "/login?faces-redirect=true";
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}