package com.food;

import com.food.Entities.User;
import com.food.Repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoodOrderingAppApplication {



	public static void main(String[] args) {


		ConfigurableApplicationContext run = SpringApplication.run(FoodOrderingAppApplication.class, args);

	}



}
