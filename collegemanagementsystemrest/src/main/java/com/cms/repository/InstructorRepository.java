package com.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cms.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

	// method for get instructor my it's first name by Query annotation
	@Query("select i from Instructor i where i.fname=?1 ")
	List<Instructor> getInstructorByFirstName(String firstName);

	// method for get instructor my it's email by Query annotation
	@Query("select i from Instructor i where i.email=:email")
	Instructor getInstructorByEmail(@Param("email") String email);

}
