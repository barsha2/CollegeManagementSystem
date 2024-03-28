package com.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cms.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	// method for get course by it's title using Query annotation
	@Query("select c from Course c where c.title=?1")
	List<Course> getCourseByTitle(String title);

}
