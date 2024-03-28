package com.cms.entity;

/*
 * This is an Entity class to be persistted in MYSQL as per setting in
 * application.properties file with custom table name courses
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Generates getters for all fields, a useful toString method, 
//and hashCode and equals implementations that checkall non-transient fields.
//Will also generate setters for all non-final fields, as well as a constructor. 
@Data

//it produce complex builder APIs for the annotaded POJO class
@Builder

//it allows us to generates a private all arguments constructor
@AllArgsConstructor

//it allows us to generates no arguments constructor
@NoArgsConstructor

//it specify the data of the table
@Entity
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int csId;

	@Column(name = "course_title")
	private String title;

	@Column(name = "course_price")
	private double price;

	@ManyToOne
	// It's used foreign key name generation to avoid auto naming strategy
	@JoinColumn(name = "ins_id")
	// It is used for ignoring course attributes at time of creating any Instructor
	// to allow NULL
	// @JsonIgnoreProperties("course")
	private Instructor instructor;
}
