package me.kristiyandinev.PhoneSystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PhoneSystemApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(PhoneSystemApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
	}
}
