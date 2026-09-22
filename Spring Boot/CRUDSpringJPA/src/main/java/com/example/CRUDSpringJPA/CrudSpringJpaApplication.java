package com.example.CRUDSpringJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class CrudSpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringJpaApplication.class, args);
	}

}
