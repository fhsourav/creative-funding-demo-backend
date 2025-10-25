package com.acescribbles.creative_funding_demo.services.impl;

import com.acescribbles.creative_funding_demo.domain.entities.Role;
import com.acescribbles.creative_funding_demo.domain.entities.User;
import com.acescribbles.creative_funding_demo.domain.enums.RoleType;
import com.acescribbles.creative_funding_demo.domain.records.AuthResponse;
import com.acescribbles.creative_funding_demo.domain.records.SignupRequest;
import com.acescribbles.creative_funding_demo.repositories.RoleRepository;
import com.acescribbles.creative_funding_demo.repositories.UserRepository;
import com.acescribbles.creative_funding_demo.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public AuthResponse registerNewSubscriber(SignupRequest signupRequest) {
		if (userRepository.findByEmail(signupRequest.email()).isPresent()) {
			throw new IllegalArgumentException("Subscriber already exists with email: " + signupRequest.email());
		}

		String hashedPassword = passwordEncoder.encode(signupRequest.password());
		Role role = roleRepository.findByName(RoleType.SUBSCRIBER)
				.orElseThrow(() -> new IllegalStateException("Required role not found: " + RoleType.SUBSCRIBER.name()));

		User newUser = new User();
		newUser.setEmail(signupRequest.email());
		newUser.setPasswordHash(hashedPassword);
		newUser.setHandle(signupRequest.handle());
		newUser.setDisplayName(signupRequest.displayName());

		Set<Role> roles = new HashSet<>();
		roles.add(role);
		newUser.setRoles(roles);

		User savedUser = userRepository.save(newUser);

		return new AuthResponse(
				savedUser.getId(),
				savedUser.getEmail(),
				savedUser.getDisplayName(),
				savedUser.getHandle(),
				savedUser.getCreatedAt(),
				savedUser.getLastModifiedAt()
		);
	}
}
