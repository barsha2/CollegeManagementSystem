package com.cms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.entity.Course;
import com.cms.model.CourseDTO;
import com.cms.service.CourseService;
import com.cms.util.Converter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//This annotation is applied to a class to mark it as a request handler. 
//Spring RestController annotation is used to create RESTful web services using Spring MVC.
@RestController

//This annotation is used to map HTTP requests to handler methods of MVC and REST controllers.
@RequestMapping("/api")

//This annotation is used to configure the whole API
@Api(tags = "college")

//This annotation describes an operation or typically a HTTP method against a specific path.
@ApiOperation(value = "View a full list of Course", response = List.class)

//This annotation describes a possible response of an operation. 
//This can be used to describe possible success and error codes from your REST API call.
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
public class CourseController {

	// This annotation marks a Constructor, Setter method, Properties and Config()
	// method as to be autowired that is ‘injecting beans'(Objects) at runtime
	// by Spring Dependency Injection mechanism
	@Autowired
	CourseService courseService;

	@Autowired
	private Converter converter;

	// This annotation describes an operation or typically a HTTP method against a
	// specific path.
	@ApiOperation(value = "This method is used to create Course.")

	// This Annotation for mapping HTTP POST requests onto specific handler methods.
	@PostMapping("/createCourse")
	public String createCourse(@Valid @RequestBody CourseDTO courseDTO) {
		final Course course = converter.convertToCourseEntity(courseDTO);

		return courseService.createCourse(course);
	}

	// This annotation describes an operation or typically a HTTP method against a
	// specific path.
	@ApiOperation(value = "This method is used to delete Course.")

	// This annotation maps HTTP DELETE requests onto specific handler methods.
	@DeleteMapping("/deleteCourse/{id}")
	public String deleteCourse(@PathVariable("id") int csId) {
		return courseService.deleteCourse(csId);
	}

	// This annotation describes an operation or typically a HTTP method against a
	// specific path.
	@ApiOperation(value = "This method is used to update Course.")

	// This annotation is used for mapping HTTP PUT requests onto specific handler
	// methods.
	@PutMapping("/updateCourse/{id}")
	public ResponseEntity<CourseDTO> updateCourse(@Valid @PathVariable int id, @RequestBody CourseDTO courseDTO) {
		final Course course = converter.convertToCourseEntity(courseDTO);

		return new ResponseEntity<CourseDTO>(courseService.updateCourse(id, courseDTO), HttpStatus.OK);
	}

	// This annotation describes an operation or typically a HTTP method against a
	// specific path.
	@ApiOperation(value = "This method is used to get an Course by id.")

	// This annotation maps HTTP GET requests onto specific handler methods.
	@GetMapping("/getCourseById/{id}")
	public CourseDTO getCourseBYId(@PathVariable("id") int id) {
		return courseService.getCourseById(id);
	}

	// This annotation describes an operation or typically a HTTP method against a
	// specific path.
	@ApiOperation(value = "This method is used to get all Course.")

	// This annotation maps HTTP GET requests onto specific handler methods.
	@GetMapping("/getAllCs")
	public List<CourseDTO> getAllCourse() {
		return courseService.getAllCourse();
	}

	// This annotation describes an operation or typically a HTTP method against a
	// specific path.
	@ApiOperation(value = "This method is used to get Course By title.")

	// This annotation maps HTTP GET requests onto specific handler methods.
	@GetMapping("/getCouseByTitle/{title}")
	public List<CourseDTO> getCourseByTitle(@PathVariable("title") String title) {
		return courseService.getCourseByTitle(title);
	}

	// This annotation describes an operation or typically a HTTP method against a
	// specific path.
	@ApiOperation(value = "This method is used to assign Instructor to Course.")

	// This Annotation for mapping HTTP POST requests onto specific handler methods.
	@PostMapping("/assignInstructor/{insId}/{cId}")
	public ResponseEntity<Course> assignInstructor(@PathVariable("insId") int insId, @PathVariable("cId") int cId) {
		return new ResponseEntity<Course>(courseService.assignInstructor(insId, cId), HttpStatus.CREATED);
	}
}
