package com.library.bookstore;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class BookstoreApplication {

	/*
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	 */

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

}
