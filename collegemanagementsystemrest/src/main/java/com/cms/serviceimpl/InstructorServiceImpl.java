package com.cms.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.Instructor;
import com.cms.exception.ResourceNotFoundException;
import com.cms.model.InstructorDTO;
import com.cms.repository.InstructorRepository;
import com.cms.service.InstructorService;
import com.cms.util.Converter;

//Service Implementation Class to generate a call to DAO implementor
@Service
public class InstructorServiceImpl implements InstructorService {

	// This annotation marks a Constructor, Setter method, Properties and Config()
	// method as to be autowired that is ‘injecting be
	// by Spring Dependency Injection mechanism
	@Autowired
	private InstructorRepository instructorRepository;

	@Autowired
	private Converter converter;

	// Logger statically created
	private static final Logger l = LoggerFactory.getLogger(InstructorService.class);

	// Instructor created
	@Override
	public String createInstructor(Instructor instructor) {

		String message = null;
		instructorRepository.save(instructor);

		l.info("Inside Service Layer!!" + new java.util.Date());

		if (instructor != null) {
			message = "Instructor saved successfully";
		}

		return message;
	}

	// delete an existing Instructor by id
	@Override
	public String deleteInstructor(int id) {

		String message = null;

		Optional<Instructor> instructor = instructorRepository.findById(id);

		if (instructor.isPresent()) {
			instructorRepository.deleteById(id);
			l.info("delete instructor by id", id + " " + new java.util.Date());
			message = new String("Record deleted successfully");
		} else {
			throw new ResourceNotFoundException("Instructor", "Id", id);

		}
		return message;
	}

//update an existing Instructor
	@Override
	public InstructorDTO updateInstructor(int id, InstructorDTO instructorDTO) {

		Instructor existingIns = instructorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor", "Id", id));

		existingIns.setFname(instructorDTO.getFname());
		existingIns.setLname(instructorDTO.getLname());
		existingIns.setEmail(instructorDTO.getEmail());

		instructorRepository.save(existingIns);
		l.info("update instructor by id", id + " " + new java.util.Date());
		return converter.convertToInstructorDTO(existingIns);
	}

	// fetch an Instructor by id
	@Override
	public InstructorDTO getInstructorById(int id) {

		Optional<Instructor> instructor = instructorRepository.findById(id);

		Instructor ins = null;

		if (instructor.isPresent()) {
			ins = instructor.get();
			l.info("get instructor by id", id + " " + new java.util.Date());
		} else {
			throw new ResourceNotFoundException("Instructor", "id", id);
		}
		return converter.convertToInstructorDTO(ins);
	}

	// fetch all Instructors
	@Override
	public List<InstructorDTO> getAllInstructors() {

		List<Instructor> instructors = instructorRepository.findAll();

		List<InstructorDTO> insDTO = new ArrayList<>();

		for (Instructor instructor : instructors) {
			insDTO.add(converter.convertToInstructorDTO(instructor));
		}

		return insDTO;
	}

	// @Query Usage by title
	@Override
	public List<InstructorDTO> getInstructorByFirstName(String firstName) {

		List<Instructor> ins = instructorRepository.getInstructorByFirstName(firstName);

		List<InstructorDTO> DTO = new ArrayList<>();
		for (Instructor instructor : ins) {
			DTO.add(converter.convertToInstructorDTO(instructor));
		}
		return DTO;
	}

	// @Query Usage by email
	@Override
	public InstructorDTO getInstructorByEmail(String email) {

		Instructor instructor = instructorRepository.getInstructorByEmail(email);

		return converter.convertToInstructorDTO(instructor);
	}

}
