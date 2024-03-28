package com.cms.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cms.entity.Instructor;
import com.cms.model.InstructorDTO;
import com.cms.repository.InstructorRepository;
import com.cms.util.Converter;

//@SpringBootTest Annotation that can be specified on a test class that runs Spring Boot based tests
@SpringBootTest
class InstructorServiceTest {

//autowired by Spring's dependency injection facilities
	@Autowired
	private InstructorService instructorService;
	@Autowired
	Converter convert;
	@MockBean
	private InstructorRepository instructorRepository;

//this method is for testing createInstructor method
	@Test
	@Order(1)
	void testCreateInstructor() {
		Instructor ins = Instructor.builder().fname("JOY").lname("Verma").email("verma@gmail.com").userId(1).build();
		Mockito.when(instructorRepository.save(ins)).thenReturn(ins);
		assertThat(instructorService.createInstructor(ins)).isEqualTo("Instructor saved successfully");
	}

//this method is for testing getAllInstructor method
	@Test
	@Order(2)
	void testgetAllInstructors() {
		Instructor inst = Instructor.builder().fname("John").lname("willi").email("john@gmail.com").userId(1).build();
		Instructor inst2 = Instructor.builder().fname("Maya").lname("sen").email("sen@gmail.com").userId(1).build();
		List<Instructor> list = new ArrayList<>();
		list.add(inst);
		list.add(inst2);
		Mockito.when(instructorRepository.findAll()).thenReturn(list);
		List<InstructorDTO> dto = instructorService.getAllInstructors();
		List<Instructor> instructor = new ArrayList<>();
		dto.forEach(insDTO -> {
			instructor.add(convert.convertToInstructorEntity(insDTO));
		});
		assertThat(instructor).isEqualTo(list);

	}

//this method is for testing deleteInstructor method
	@Test
	@DisplayName("Positive Test case")
	@Order(3)
	void testDeleteInstructor() {
		Instructor inst = Instructor.builder().fname("Anurag").lname("Prasad").email("Anurag@gmail.com").userId(1)
				.build();

		Optional<Instructor> opIns = Optional.of(inst);
		Mockito.when(instructorRepository.findById(opIns.get().getUserId())).thenReturn(opIns);
		assertThat(instructorService.deleteInstructor(opIns.get().getUserId()))
				.isEqualTo("Record deleted successfully");

	}

//	@Test
//	@DisplayName("Negative Test case")
//	@Order(4)
//	void testDeleteInstructorNegative() {
//
//		Instructor inst = Instructor.builder().fname("Anurag").lname("Prasad").email("Anurag@gmail.com").userId(1)
//				.build();
//		Mockito.when(instructorRepository.existsById(inst.getUserId())).thenReturn(false);
//		assertThat(instructorService.deleteInstructor(inst.getUserId())).isEqualTo("Record deleted successfully");
//	}

//this method is for testing getInstructorById method
	@Test
	@Order(5)
	void testGetInsById() {
		Instructor inst = Instructor.builder().fname("Rohan").lname("Dey").email("Rohan@gmail.com").userId(1).build();

		Optional<Instructor> opIns = Optional.of(inst);
		Mockito.when(instructorRepository.findById(inst.getUserId())).thenReturn(opIns);

		InstructorDTO dto = convert.convertToInstructorDTO(inst);
		assertThat(instructorService.getInstructorById(inst.getUserId())).isEqualTo(dto);

	}
}