package com.cms.service;

//It is used to expose the only required DAO facilty to customers 
import java.util.List;

import com.cms.entity.Course;
import com.cms.model.CourseDTO;

public interface CourseService {

	// for creating a course
	String createCourse(Course course);

	// for deleting the course by it's id
	String deleteCourse(int id);

	// for update course
	CourseDTO updateCourse(int id, CourseDTO courseDTO);

	// for get course by it's id
	CourseDTO getCourseById(int id);

	// for get all course
	List<CourseDTO> getAllCourse();

	// for @Query usage
	List<CourseDTO> getCourseByTitle(String title);

	// for assigning instructor to a course
	Course assignInstructor(int insId, int cId);
}
