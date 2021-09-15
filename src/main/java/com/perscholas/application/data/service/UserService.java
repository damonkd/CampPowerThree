package com.perscholas.application.data.service;

import com.perscholas.application.data.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.Collections;

import com.perscholas.application.data.Role;

@Service
public class UserService extends CrudService<User, Integer> {

    private PasswordEncoder passwordEncoder;

    private UserRepository repository;

    public UserService(PasswordEncoder passwordEncoder, @Autowired UserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @Override
    protected UserRepository getRepository() {
        return repository;
    }

    public void adduser(User user) {


        user.setHashedPassword(passwordEncoder.encode(user.getHashedPassword()));

        user.setRoles(Collections.singleton(Role.USER));
        repository.save(user);
    }

}
