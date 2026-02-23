package com.train.rest.controller;

import com.train.rest.dto.CreateUserRequestV1;
import com.train.rest.dto.CreateUserResponseV1;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws NotFoundException {
       return ResponseEntity.ok(userService.getUser(id));
    }
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getUsersList() {
		return ResponseEntity.ok(userService.getUsersList());
	}
	
	@PostMapping
	@RequestMapping("/")
	public ResponseEntity<CreateUserResponseV1> createUser(@RequestBody CreateUserRequestV1 requestV1) {
		return ResponseEntity.ok(userService.createUserV1(requestV1));
	}
}
