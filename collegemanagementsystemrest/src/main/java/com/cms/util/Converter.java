package com.cms.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.cms.entity.Course;
import com.cms.entity.Instructor;
import com.cms.model.CourseDTO;
import com.cms.model.InstructorDTO;

//Indicates that an annotated class is a "component".Such classes are considered as 
//candidates for auto-detectionwhen using annotation-based configuration and classpath 
//scanning. 
@Component
public class Converter {

	// convert from InstructorDTO to InstructorEntity

	public Instructor convertToInstructorEntity(InstructorDTO instructorDTO) {
		Instructor instructor = new Instructor();

		if (instructorDTO != null) {
			BeanUtils.copyProperties(instructorDTO, instructor);
		}
		return instructor;
	}

	// convert from InstructorEntity to InstructorDTO
	public InstructorDTO convertToInstructorDTO(Instructor instructor) {
		InstructorDTO instructorDTO = new InstructorDTO();

		if (instructor != null) {
			BeanUtils.copyProperties(instructor, instructorDTO);
		}
		return instructorDTO;
	}

	// convert from CourseDTO to CourseEntity
	public Course convertToCourseEntity(CourseDTO courseDTO) {
		Course course = new Course();

		if (courseDTO != null) {
			BeanUtils.copyProperties(courseDTO, course);
		}
		return course;
	}

	// convert from CourseEntity to CourseDTO
	public CourseDTO convertToCourseDTO(Course course) {
		CourseDTO courseDTO = new CourseDTO();

		if (course != null) {
			BeanUtils.copyProperties(course, courseDTO);
		}
		return courseDTO;
	}

}
