package com.cms.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cms.entity.Instructor;

//@DataJpaTest Using this annotation will disable full auto-configuration and instead apply 
//only configuration relevant to JPA tests. 
@DataJpaTest

//@AutoConfigureTestDatabase Annotation that can be applied to a test class to configure a test database to use 
//instead of the application-defined 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InstructorRepositoryTest {

	// autowired by Spring's dependency injection facilities
	@Autowired
	private InstructorRepository instructorRepository;

	// this method is to test save method
	@Test
	@Rollback(value = false)
	void saveInstructorTest() {
		Instructor instructor = Instructor.builder().fname("barsha").fname("podder").email("barsha@gmail.com").userId(2)
				.build();
		instructorRepository.save(instructor);
		assertThat(instructor.getUserId()).isGreaterThan(0);
		// assertThat(instructorRepository.findById(1).get().getUserId()).isEqualTo(1);
	}

	// this method is to test getAllInstructor method
	@Test
	@Rollback(value = false)
	void getAllInstructorTest() {
		List<Instructor> list = instructorRepository.findAll();
		assertThat(list.size()).isGreaterThan(1);
	}

	// this method to test updateInstructor method
	@Test
	@Rollback(value = false)
	void updateInstructorTest() {
		Instructor updateIns = instructorRepository.findById(2).get();
		updateIns.setFname("anurag");
		Instructor ins = instructorRepository.save(updateIns);
		assertThat(ins.getFname()).isEqualTo("anurag");
	}

//	@Test
//	@DisplayName("Negative test case")
//	@Rollback(value = false)
//	void updateInstructorTestNegative() {
//		Instructor updateIns = instructorRepository.findById(2).get();
//		updateIns.setFname("anurag");
//		Instructor ins = instructorRepository.save(updateIns);
//		assertThat(ins.getFname()).isEqualTo("John");
//	}
}