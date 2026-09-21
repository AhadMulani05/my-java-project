package com.example.SpringBootCrudProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class})
public class SpringBootCrudProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudProjectApplication.class, args);
	}

}
