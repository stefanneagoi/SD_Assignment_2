package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.example")
@EnableJpaRepositories("com.example.demo.repository")
@EntityScan("com.example.demo")
public class DemoApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	};
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
