package com.cms.service;

import com.cms.entity.User;

//It's for user logging in the system to use the api's.
public interface UserService {

	User login(String userName, String password);
}
