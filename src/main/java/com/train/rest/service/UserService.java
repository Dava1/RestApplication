package com.train.rest.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.train.rest.dto.CreateUserRequestV1;
import com.train.rest.dto.CreateUserResponseV1;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import com.train.rest.model.User;
import com.train.rest.repository.UserRepository;

@Service
public class UserService  {
    private final UserRepository repository;

    public UserService( UserRepository repository){
        this.repository = repository;
    }

	public CreateUserResponseV1 createUserV1(CreateUserRequestV1 requestV1) {
		//Create
		User newest = new User();
		newest.setCreatedAt(LocalDateTime.now());
		newest.setUsername(requestV1.userName());
		newest.setSurname(requestV1.userSurname());
		newest.setEmail(requestV1.email());
		//call
		return new CreateUserResponseV1(repository.save(newest).getId() );
	}
	
    public User getUser(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }
    
    public List<User> getUsersList() {
        return repository.findAll();
    }
}
