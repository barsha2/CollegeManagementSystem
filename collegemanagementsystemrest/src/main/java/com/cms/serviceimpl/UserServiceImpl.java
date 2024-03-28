package com.cms.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.User;
import com.cms.repository.UserRepository;
import com.cms.service.UserService;

//Service Implementation Class to generate a call to DAO implementor
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User login(String userName, String password) {

		User user = userRepository.findByUserNameAndPassword(userName, password);
		return user;
	}

}
