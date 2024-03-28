package com.cms.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/*
 * This class is DTO class holding the proxy representation of the entity class
 * a also containing the necessary validation
 */
@Data
public class CourseDTO {

	private int csId;

	@NotNull
	@Size(min = 2, message = "First name should be atleast 2 characters")
	private String title;

	@NotNull
	private double price;

}
