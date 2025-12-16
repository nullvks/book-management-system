package com.nullvks.bookmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching; //for redis

@SpringBootApplication
@EnableCaching //for redis
public class BookmanagementsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmanagementsystemApplication.class, args);
		System.out.println("test1");
		System.out.println("17:22 min timestamp");
	}

}
