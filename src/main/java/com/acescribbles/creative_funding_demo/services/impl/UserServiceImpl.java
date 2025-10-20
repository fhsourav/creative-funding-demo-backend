package com.acescribbles.creative_funding_demo.services.impl;

import com.acescribbles.creative_funding_demo.domain.entities.Role;
import com.acescribbles.creative_funding_demo.domain.entities.User;
import com.acescribbles.creative_funding_demo.domain.enums.RoleType;
import com.acescribbles.creative_funding_demo.domain.records.UserRecord;
import com.acescribbles.creative_funding_demo.repositories.RoleRepository;
import com.acescribbles.creative_funding_demo.repositories.UserRepository;
import com.acescribbles.creative_funding_demo.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
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
	public User registerNewSubscriber(UserRecord userRecord) {
		if (userRepository.findByEmail(userRecord.email()).isPresent()) {
			throw new IllegalArgumentException("Subscriber already exists with email: " + userRecord.email());
		}

		String hashedPassword = passwordEncoder.encode(userRecord.password());
		String roleName = RoleType.SUBSCRIBER.name();
		Role role = roleRepository.findByName(roleName)
				.orElseThrow(() -> new IllegalStateException("Required role not found: " + roleName));

		User newUser = new User();
		newUser.setEmail(userRecord.email());
		newUser.setPasswordHash(hashedPassword);
		newUser.setHandle(userRecord.handle());
		newUser.setDisplayName(userRecord.displayName());

		Set<Role> roles = new HashSet<>();
		roles.add(role);
		newUser.setRoles(roles);

		return userRepository.save(newUser);
	}
}
