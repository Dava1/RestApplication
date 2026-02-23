package com.train.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "user_name", nullable = false)
	@JsonProperty("user_name")
	private String username;
	
	@Column(name = "user_surname", nullable = false)
	@JsonProperty("user_surname")
	private String surname;
	
	@Column(name = "email", nullable = false)
	@Email
	@NotBlank
	@JsonProperty("email")
	private String email;
	
	@Column(name = "created_at", nullable = false)
	@JsonProperty("created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "modifiedAt")
	@JsonProperty("modifiedAt")
	private LocalDateTime modifiedAt;
	
    public User() {
    }

    public User(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}
	
	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
