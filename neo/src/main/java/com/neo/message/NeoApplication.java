package com.neo.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.neo.controllers.ProducerController;

@SpringBootApplication
@ComponentScan(basePackageClasses = ProducerController.class)
public class NeoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeoApplication.class, args);
	}

}
