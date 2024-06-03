package com.example.khalipso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.Controllers","com.example.ServiceImplements","com.example.Services","com.example.WebModels"})
//@EnableJpaRepositories("com.example.Repositories")
//@EntityScan("com.example.Entities")
public class KhalipsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KhalipsoApplication.class, args);
	}

}
