package com.nexos.test_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication()
@EntityScan()
@EnableJpaRepositories()
@ComponentScan()

public class TestJavaApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TestJavaApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TestJavaApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

}
