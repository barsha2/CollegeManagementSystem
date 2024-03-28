package com.cms.serviceimpl;

//Service Implementation Class to generate a call to DAO implementor
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.Course;
import com.cms.entity.Instructor;
import com.cms.exception.ResourceNotFoundException;
import com.cms.model.CourseDTO;
import com.cms.repository.CourseRepository;
import com.cms.repository.InstructorRepository;
import com.cms.service.CourseService;
import com.cms.util.Converter;

@Service
public class CourseServiceImpl implements CourseService {

	// This annotation marks a Constructor, Setter method, Properties and Config()
	// method as to be autowired that is ‘injecting be
	// by Spring Dependency Injection mechanism
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private Converter converter;

	@Autowired
	private InstructorRepository instructorRepository;

	private static final Logger l = LoggerFactory.getLogger(CourseService.class);

	// create a new Course
	@Override
	public String createCourse(Course course) {

		String message = null;
		courseRepository.save(course);

		l.info("Inside Service Layer!!" + new java.util.Date());
		if (course != null) {
			message = "Course saved successfully";
		}
		return message;
	}

	// delete an existing course
	@Override
	public String deleteCourse(int id) {

		String message = null;

		Optional<Course> course = courseRepository.findById(id);

		if (course.isPresent()) {
			courseRepository.deleteById(id);

			l.info("delete course by id", id + " " + new java.util.Date());
			message = new String("Record Deleted Successfully");

		} else {
			throw new ResourceNotFoundException("Course", "Id", id);
		}
		return message;
	}

	// update existing course
	@Override
	public CourseDTO updateCourse(int id, CourseDTO courseDTO) {

		Course existingCs = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Course", "Id", id));

		existingCs.setTitle(courseDTO.getTitle());
		existingCs.setPrice(courseDTO.getPrice());

		courseRepository.save(existingCs);
		l.info("update course by id", id + " " + new java.util.Date());

		return converter.convertToCourseDTO(existingCs);
	}

	// return a particular course by id
	@Override
	public CourseDTO getCourseById(int id) {

		Optional<Course> course = courseRepository.findById(id);

		Course cs = null;
		if (course.isPresent()) {
			cs = course.get();
			l.info("get course by id", id + " " + new java.util.Date());
		} else {
			throw new ResourceNotFoundException("Course", "ID", id);
		}
		return converter.convertToCourseDTO(cs);
	}

	// return all courses
	@Override
	public List<CourseDTO> getAllCourse() {
		List<Course> courses = courseRepository.findAll();

		List<CourseDTO> csDTO = new ArrayList<>();

		for (Course course : courses) {
			csDTO.add(converter.convertToCourseDTO(course));
		}

		return csDTO;
	}

	// @Query usage by title
	@Override
	public List<CourseDTO> getCourseByTitle(String title) {

		List<Course> cs = courseRepository.getCourseByTitle(title);

		List<CourseDTO> DTO = new ArrayList<>();
		for (Course course : cs) {
			DTO.add(converter.convertToCourseDTO(course));
		}
		return DTO;
	}
	// assign a course to an instructor

	@Override
	public Course assignInstructor(int insId, int cId) {

		Instructor ins1 = instructorRepository.findById(insId).get();
		Course c1 = courseRepository.findById(cId).get();

		c1.setInstructor(ins1);
		return courseRepository.save(c1);
	}

}
