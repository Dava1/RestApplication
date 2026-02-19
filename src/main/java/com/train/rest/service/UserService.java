package com.train.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.train.rest.model.User;
import com.train.rest.repository.UserRepository;

@Service
public class UserService  {
    private final UserRepository repository;

    public UserService( UserRepository repository){
        this.repository = repository;
    }

    public Optional<User> getUser(Long id){
        return repository.findById(id);
    }
    
    public List<User> getUsersList() {
        return repository.findAll();
    }
}
