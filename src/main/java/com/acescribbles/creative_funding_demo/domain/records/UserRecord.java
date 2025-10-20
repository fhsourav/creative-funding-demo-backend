package com.acescribbles.creative_funding_demo.domain.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record UserRecord(

		long id,

		@NotBlank(message = "Email is required.")
		@Email(message = "Must be a valid email address.")
		String email,

		@NotBlank(message = "Password is required.")
		@Size(min = 8, message = "Password must be at least 8 characters long.")
		String password,

		String displayName,

		String handle,

		Instant createdAt,

		Instant lastModifiedAt

) { }
