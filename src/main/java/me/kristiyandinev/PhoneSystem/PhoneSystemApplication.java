package me.kristiyandinev.PhoneSystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.logging.Logger;

@SpringBootApplication
public class PhoneSystemApplication implements CommandLineRunner {

	public static Logger logger =  Logger.getGlobal();

	public static void main(String[] args) {
		SpringApplication.run(PhoneSystemApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
	}
}
