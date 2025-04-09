package me.kristiyandinev.PhoneSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.logging.Logger;

@SpringBootApplication
public class PhoneSystemApplication implements CommandLineRunner {

	public static Logger logger =  Logger.getGlobal();

	public static void main(String[] args) {
		SpringApplication.run(PhoneSystemApplication.class, args);
	}


	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void run(String... args) throws Exception {
		//applicationContext.getBeanDefinitionNames()); beans
	}
}
