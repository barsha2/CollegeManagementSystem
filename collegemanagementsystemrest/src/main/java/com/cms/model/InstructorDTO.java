package com.cms.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/*
 * This class is DTO class holding the proxy representation of the entity class
 * a also containing the necessary validation
 */

@Data
public class InstructorDTO extends UserDTO {

	@NotNull(message = "fname can not be null")
	@Size(min = 2, message = "fname size can not be less than 2")
	private String fname;

	@NotNull(message = "{lname can not be null}")
	@Size(min = 2, message = "lname size can not be less than 2")
	private String lname;

	@NotNull(message = "{email can not be null}")
	@Email
	private String email;

}
