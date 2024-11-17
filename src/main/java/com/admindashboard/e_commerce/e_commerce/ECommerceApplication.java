package com.admindashboard.e_commerce.e_commerce;

import com.admindashboard.e_commerce.e_commerce.updatescript.InitialUpdateScript;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceApplication {

	@Autowired
	InitialUpdateScript initialUpdateScript;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}


	@PostConstruct
	public void init() {
		// initially run this to add a role in the DB
		//initialUpdateScript.addRole();
		initialUpdateScript.addInitialAdminUser();
	}

}
