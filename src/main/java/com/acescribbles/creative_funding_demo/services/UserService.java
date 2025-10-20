package com.acescribbles.creative_funding_demo.services;

import com.acescribbles.creative_funding_demo.domain.entities.User;
import com.acescribbles.creative_funding_demo.domain.records.UserRecord;

public interface UserService {

	public User registerNewSubscriber(UserRecord userRecord);

}
