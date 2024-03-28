package com.cms.entity;

/*
 * This is an Entity class to be persistted in MYSQL as per setting in
 * application.properties file with custom table name instructors
 */
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "instructors", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class Instructor extends User {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer insId;

	@Column(name = "firstname", length = 50)
	private String fname;

	@Column(name = "lastname", length = 50)
	private String lname;

	@Column(length = 150)
	private String email;

	@OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
	// It is used for ignoring Instructor attributes at time of creating any course
	// to allow NULL
	@JsonIgnoreProperties("instructor")
	// It is for more than 1 course assigned for one Instructor
	private List<Course> course;

	// At the time of testing constructor with all parameters are required

	@Builder
	public Instructor(Integer userId, String userName, String password, String fname, String lname, String email,
			List<Course> course) {
		super(userId, userName, password);
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.course = course;
	}

}
