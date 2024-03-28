package com.cms.controller;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cms.entity.Instructor;
import com.cms.service.InstructorService;
import com.cms.util.Converter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@WebMvcTest(InstructorController.class)
class InstructorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	Instructor instructor;

	@MockBean
	InstructorService instructorService;
	@MockBean
	Converter converter;

	String jwtToken = "";

	@BeforeEach
	void setUp() {
		instructor = new Instructor();
		instructor.setFname("ram");
		instructor.setLname("sharma");
		instructor.setEmail("ram@gmail.com");
		instructor.setUserName("ram");
		instructor.setPassword("admin123");
	}

	public String tokenCreation() {
		String userName = "ram";
		jwtToken = Jwts.builder().setSubject(userName).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

		return jwtToken;
	}

	@Test
	void testSaveInstructor() throws Exception {
		String accessToken = tokenCreation();

		mockMvc.perform(MockMvcRequestBuilders.post("/api/createInstructor").contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n" + "  \"userId\": 101,\r\n" + "  \"firstName\": \"ram\",\r\n"
						+ "  \"lastName\": dutta,\r\n" + "  \"email\": \"ram@gmail.com\"\r\n"
						+ "  \"userName\": \"ram\",\r\n" + "  \"password\": \"ram123\",\r\n" + "}")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
