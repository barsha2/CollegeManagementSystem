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

import com.cms.entity.Course;

//@DataJpaTest Using this annotation will disable full auto-configuration and instead apply 
//only configuration relevant to JPA tests. 
@DataJpaTest

//@AutoConfigureTestDatabase Annotation that can be applied to a test class to configure a test database to use 
//instead of the application-defined 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseRepositoryTest {

	// autowired by Spring's dependency injection facilities
	@Autowired
	CourseRepository courseRepository;

	// this method is to test save method
	@Test
	@Rollback(value = false)
	void saveCourseTest() {
		Course course = Course.builder().title("Java full stack").price(2000d).csId(1).build();
		courseRepository.save(course);
		assertThat(courseRepository.findById(1).get().getCsId()).isEqualTo(1);
	}

	// this method is to test getAllCourse method
	@Test
	@Rollback(value = false)
	void getAllCourseTest() {
		List<Course> list = courseRepository.findAll();
		assertThat(list.size()).isGreaterThan(0);
	}

	// this method is to test updateCourse method
	@Test
	@Rollback(value = false)
	void updateCourseTest() {
		Course updateCrs = courseRepository.findById(1).get();
		updateCrs.setTitle("Python");
		Course crs = courseRepository.save(updateCrs);
		assertThat(crs.getTitle()).isEqualTo("Python");
	}
}