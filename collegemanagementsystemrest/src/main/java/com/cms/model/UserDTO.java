package com.cms.model;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserDTO {

	private Integer userId;

	@NotNull
	private String userName;

	@NotNull
	private String password;

}
