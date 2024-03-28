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

import com.cms.entity.Instructor;
import com.cms.model.InstructorDTO;
import com.cms.service.InstructorService;
import com.cms.util.Converter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@ApiOperation(value = "View a full list of Instructor with Course", response = List.class)

//This annotation describes a possible response of an operation. 
//This can be used to describe possible success and error codes from your REST API call.
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
public class InstructorController {

	// This annotation marks a Constructor, Setter method, Properties and Config()
	// method as to be autowired that is ‘injecting beans'(Objects) at runtime
	// by Spring Dependency Injection mechanism
	@Autowired
	InstructorService instructorService;

	@Autowired
	private Converter converter;

	// This annotation describes an operation or typically a HTTP method against a
	// specific path.
	@ApiOperation(value = "This method is used to create Instructor.")

	// This Annotation for mapping HTTP POST requests onto specific handler methods.
	@PostMapping("/createInstructor")
	public String createInstructor(
			@ApiParam(value = "Instructor object store in database table", example = "1") @Valid @RequestBody InstructorDTO instructorDTO) {
		final Instructor instructor = converter.convertToInstructorEntity(instructorDTO);

		return instructorService.createInstructor(instructor);
	}

	// This annotation describes an operation or typically a HTTP method against a
	// specific path.
	@ApiOperation(value = "This method is used to delete Instructor.")

	// This annotation maps HTTP DELETE requests onto specific handler methods.
	@DeleteMapping("/deleteInstructor/{id}")
	public String deleteInstructor(
			@ApiParam(value = "Instructor id from which Instructor object will be retrieved", required = true, example = "1") @PathVariable("id") int insId) {

		return instructorService.deleteInstructor(insId);
	}

	// This annotation describes an operation or typically a HTTP method against a
	// specific path.
	@ApiOperation(value = "This method is used to update Instructor..")

	// This annotation is used for mapping HTTP PUT requests onto specific handler
	// methods.
	@PutMapping("/updateInstructor/{id}")
	public ResponseEntity<InstructorDTO> updateInstructor(@Valid @PathVariable int id,
			@ApiParam(value = "Update employee object", example = "1") @RequestBody InstructorDTO instructorDTO) {
		final Instructor instructor = converter.convertToInstructorEntity(instructorDTO);

		return new ResponseEntity<InstructorDTO>(instructorService.updateInstructor(id, instructorDTO), HttpStatus.OK);
	}

	// This annotation describes an operation or typically a HTTP method against a
	// specific path.
	@ApiOperation(value = "This method is used to fetch Instructor.")

	// This annotation maps HTTP GET requests onto specific handler methods.
	@GetMapping("/getInsById/{id}")
	public InstructorDTO getInstructorById(
			@ApiParam(value = "Instructor id from which Instructor object will be retrieved", required = true, example = "1") @PathVariable("id") int id) {
		return instructorService.getInstructorById(id);
	}

	// This annotation describes an operation or typically a HTTP method against a
	// specific path.
	@ApiOperation(value = "This method is used to fetch all Instructors.")

	// This annotation maps HTTP GET requests onto specific handler methods.
	@GetMapping("/getAllIns")
	public List<InstructorDTO> getAllInstructor() {
		return instructorService.getAllInstructors();
	}

	// This annotation describes an operation or typically a HTTP method against a
	// specific path.
	@ApiOperation(value = "This method is used to fetch Instructor by Name")

	// This annotation maps HTTP GET requests onto specific handler methods.
	@GetMapping("/getInsByFirstName/{fname}")
	public List<InstructorDTO> getInstructorByFirstName(
			@ApiParam(value = "Instructor id from which Instructor object will be retrieved", required = true, example = "1") @PathVariable("fname") String firstName) {
		return instructorService.getInstructorByFirstName(firstName);
	}

	@ApiOperation(value = "This method is used to fetch Instructor by email.")

	// This annotation maps HTTP GET requests onto specific handler methods.
	@GetMapping("/getInsByEmail/{email}")
	public InstructorDTO getInstructorByEmail(@PathVariable("email") String email) {
		return instructorService.getInstructorByEmail(email);
	}
}
