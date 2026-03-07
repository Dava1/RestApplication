package com.train.rest.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;

public record UpdateUserRequestV1(
		@Nullable
		String userName,
		@Nullable
		String userSurname,
		@Nullable
		@Email(message = "Invalid email format")
		String email
) {}
