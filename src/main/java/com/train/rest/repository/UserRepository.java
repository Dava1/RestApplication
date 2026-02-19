package com.train.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.train.rest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
