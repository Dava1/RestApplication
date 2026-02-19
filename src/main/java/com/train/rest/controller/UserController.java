package com.train.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.train.rest.service.UserService;
import com.train.rest.model.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
       return ResponseEntity.ok(userService.getUser(id));
    }
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getUsersList() {
		return ResponseEntity.ok(userService.getUsersList());
	}
}
