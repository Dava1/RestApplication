package com.train.rest.controller;

import com.train.rest.dto.CreateUserRequestV1;
import com.train.rest.dto.CreateUserResponseV1;
import com.train.rest.dto.UpdateUserRequestV1;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.train.rest.service.UserService;
import com.train.rest.model.User;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
 
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
	    return ResponseEntity.ok(userService.getUser(id));
    }
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getUsersList() {
		return ResponseEntity.ok(userService.getUsersList());
	}
	
	@PostMapping("/")
	public ResponseEntity<CreateUserResponseV1> createUser(@RequestBody CreateUserRequestV1 requestV1) {
		return ResponseEntity.ok(userService.createUserV1(requestV1));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequestV1 requestV1){
		userService.updateUser(id, requestV1);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
