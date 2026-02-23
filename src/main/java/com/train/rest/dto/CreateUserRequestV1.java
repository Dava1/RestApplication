package com.train.rest.dto;

import jakarta.validation.constraints.Email;

public record CreateUserRequestV1(
		Long id,
		String userName,
		String userSurname,
		@Email
		String email
) {}
