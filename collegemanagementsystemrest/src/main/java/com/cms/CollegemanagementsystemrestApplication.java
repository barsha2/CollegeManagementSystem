package com.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cms.config.JwtFilter;

//// same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication
//@EnableSwagger2
//use @EnableSwagger2 in config classes in case of production
public class CollegemanagementsystemrestApplication {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(CollegemanagementsystemrestApplication.class, args);

		System.out.println("Welcome to college management system");

	}

}
