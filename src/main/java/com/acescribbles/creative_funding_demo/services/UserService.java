package com.acescribbles.creative_funding_demo.services;

import com.acescribbles.creative_funding_demo.domain.records.AuthResponse;
import com.acescribbles.creative_funding_demo.domain.records.SignupRequest;

public interface UserService {

	public AuthResponse registerNewSubscriber(SignupRequest signupRequest);

}
