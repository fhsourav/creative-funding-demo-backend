package com.acescribbles.creative_funding_demo.controllers;

import com.acescribbles.creative_funding_demo.domain.records.AuthResponse;
import com.acescribbles.creative_funding_demo.domain.records.SignupRequest;
import com.acescribbles.creative_funding_demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register/subscriber")
	public ResponseEntity<AuthResponse> createSubscriber(@Valid @RequestBody SignupRequest signupRequest) {

		AuthResponse response = userService.registerNewSubscriber(signupRequest);

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

}
