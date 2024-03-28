package com.cms.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cms.entity.Course;
import com.cms.model.CourseDTO;
import com.cms.repository.CourseRepository;
import com.cms.util.Converter;

//@SpringBootTest Annotation that can be specified on a test class that runs Spring Boot based tests
@SpringBootTest
class CourseServiceTest {

	// autowired by Spring's dependency injection facilities
	@Autowired
	CourseService courseService;
	@Autowired
	Converter convert; // convert object is responsible to convert DTO entity and vice versa
	@MockBean
	private CourseRepository courseRepository;

	// this method is for testing createCourse method
	@Test
	@Order(1)
	void testCreateCourse() {
		Course course = Course.builder().title("Java full stack").price(2000d).csId(1).build();
		Mockito.when(courseRepository.save(course)).thenReturn(course);
		assertThat(courseService.createCourse(course)).isEqualTo("Course saved successfully");
	}

	// this method is for testing getAllCourse method
	@Test
	@Order(2)
	void testGetAllInstructors() {
		List<Course> list = new ArrayList<>();
		Course course = Course.builder().title("Java full stack").price(2000d).csId(1).build();
		Course course2 = Course.builder().title("Python").price(3000d).csId(2).build();
		list.add(course);
		list.add(course2);
		List<CourseDTO> dto = new ArrayList<>();
		list.forEach(crs -> {
			dto.add(convert.convertToCourseDTO(crs));
		});
		Mockito.when(courseRepository.findAll()).thenReturn(list);
		assertThat(courseService.getAllCourse()).isEqualTo(dto);
	}

	// this method is for testing getCourseById method
	@Test
	@Order(3)
	void testGetCourseById() {
		Course crs = Course.builder().title("Java full stack").price(2000d).csId(1).build();

		Optional<Course> opCrs = Optional.of(crs);
		Mockito.when(courseRepository.findById(crs.getCsId())).thenReturn(opCrs);

		CourseDTO dto = convert.convertToCourseDTO(crs);
		assertThat(courseService.getCourseById(crs.getCsId())).isEqualTo(dto);
	}

//this method is for testing deleteCourse method
	@Test
	@Order(4)
	void testDeleteCourse() {
		Course crs = Course.builder().title("Java full stack").price(2000d).csId(1).build();
		Optional<Course> opCrs = Optional.of(crs);
		Mockito.when(courseRepository.findById(crs.getCsId())).thenReturn(opCrs);
		assertThat(courseService.deleteCourse(crs.getCsId())).isEqualTo("Record Deleted Successfully");
	}

//this method is for testing updateCourse
	@Test
	@Order(5)
	void testUpdateCourse() {
		Course crs = Course.builder().title("Java full stack").price(2000d).csId(1).build();
		Course course2 = Course.builder().title("Python").price(3000d).csId(2).build();
		Optional<Course> opCrs = Optional.of(course2);
		Mockito.when(courseRepository.findById(crs.getCsId())).thenReturn(opCrs);
		assertThat(courseService.updateCourse(crs.getCsId(), convert.convertToCourseDTO(course2)))
				.isEqualTo(convert.convertToCourseDTO(course2));
	}

//this method is for testing assignInstructor method 
//	@Test
//	@DisplayName("Negative Test case")
//	@Order(6)
//	void testAssignInstructor() {
//		Course crs = Course.builder().title("Java full stack").price(2000d).csId(1).build();
//		Instructor inst = Instructor.builder().fname("Anurag").fname("Prasad").email("Anurag@gmail.com").userId(2)
//				.build();
//		Optional<Course> opCrs = Optional.of(crs);
//		Mockito.when(courseRepository.findById(crs.getCsId())).thenReturn(opCrs);
//		assertThat(courseService.assignInstructor(crs.getCsId(), inst.getUserId()))
//				.isEqualTo(convert.convertToCourseDTO(crs));
//	}
}