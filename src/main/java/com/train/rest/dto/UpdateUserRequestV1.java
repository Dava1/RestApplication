package com.train.rest.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequestV1(
		@Nullable
		@NotBlank
		String userName,
		@Nullable
		@NotBlank
		String userSurname,
		@Nullable
		@Email(message = "Invalid email format")
		String email
) {}
