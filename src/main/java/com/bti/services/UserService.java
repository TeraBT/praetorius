package com.bti.services;

import com.bti.model.User;
import com.bti.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;


//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

//    public Optional<Role> findRoleByName(String name) {
//        return roleRepository.findByName(name);
//    }

    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }


    @PreAuthorize("hasAuthority('ADMIN') or principal.username eq #username")
    public User loadUser(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }


//    @PreAuthorize("hasAuthority('ADMIN')")
//    public User saveUser(User user) {
//        if (user.isNew()) {
//            user.setCreateDate(LocalDateTime.now());
//            user.setCreateUserUsername(getAuthenticatedUser().getUsername());
//        } else {
//            user.setUpdateDate(LocalDateTime.now());
//            user.setUpdateUserUsername(getAuthenticatedUser().getUsername());
//        }
//        return userRepository.save(user);
//    }


//    @PreAuthorize("hasAuthority('ADMIN')")
//    @Transactional
//    public boolean deleteUser(String username) {
//
//        Optional<User> user = userRepository.findById(username);
//
//        if (user.isPresent()) {
//
//            userRepository.delete(user.get());
//
//            return true;
//        }
//
//        return false;
//    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @Transactional
//    public boolean deleteUser(User user) {
//        return deleteUser(user.getUsername());
//    }



    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName()).orElse(null);
    }


}