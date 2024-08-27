package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.Controllers")
@EnableJpaRepositories(basePackages = "com.example.Repositorys")
public class CharBuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharBuilderApplication.class, args);
	}

}
