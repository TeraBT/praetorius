package com.bti.beans;

import com.bti.model.Role;
import com.bti.model.User;
import com.bti.repositories.RoleRepository;
import com.bti.services.UserService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;

@Component
@ViewScoped
public class RegisterBean implements Serializable {

    private User user = new User();
    private String confirmPassword;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    public String register() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!user.getPassword().equals(confirmPassword)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password Error", "Passwords do not match"));
            return null;
        }

        if (userService.findByUsername(user.getUsername()).isPresent()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username Error", "Username already exists"));
            return null;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = userService.findRoleByName("USER").orElseGet(() -> {
            Role newRole = new Role();
            newRole.setName("USER");
            roleRepository.save(newRole);
            return newRole;
        });
        user.getRoleSet().add(userService.findRoleByName("USER").orElse(userRole));
        userService.saveUser(user);

        return "login?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}