package com.cms.service;

//It is used to expose the only required DAO facilty to customers 
import java.util.List;

import com.cms.entity.Instructor;
import com.cms.model.InstructorDTO;

public interface InstructorService {

	// for creating a instructor
	String createInstructor(Instructor instructor);

	// for deleting a instrucor by it's id
	String deleteInstructor(int id);

	// for update instructor details
	InstructorDTO updateInstructor(int id, InstructorDTO instructorDTO);

	// for get an instructor by it's id
	InstructorDTO getInstructorById(int id);

	// for get all instructors
	List<InstructorDTO> getAllInstructors();

	// Remainig 2 are for @Query annotation usage
	List<InstructorDTO> getInstructorByFirstName(String firstName);

	InstructorDTO getInstructorByEmail(String email);

}
