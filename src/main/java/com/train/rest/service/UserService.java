package com.train.rest.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.train.rest.dto.CreateUserRequestV1;
import com.train.rest.dto.CreateUserResponseV1;
import com.train.rest.dto.UpdateUserRequestV1;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.train.rest.model.User;
import com.train.rest.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService  {
    private final UserRepository repository;

    public UserService( UserRepository repository){
        this.repository = repository;
    }
    
	@Transactional
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
	
	@Transactional
    public User getUser(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    
	@Transactional
	public void updateUser(Long id, UpdateUserRequestV1 user){
		User modify = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		Optional.ofNullable(user.userName()).ifPresent(modify::setUsername);
		Optional.ofNullable(user.userSurname()).ifPresent(modify::setSurname);
		Optional.ofNullable(user.email()).ifPresent(modify::setEmail);
		modify.setModifiedAt(LocalDateTime.now());
		
		repository.save(modify);
	}
	
	@Transactional
	public void deleteUser(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional
    public List<User> getUsersList() {
        return repository.findAll();
    }
}
