package me.kristiyandinev.PhoneSystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.logging.Logger;

@SpringBootApplication
public class PhoneSystemApplication implements CommandLineRunner {

	public static Logger logger =  Logger.getGlobal();
	public JdbcTemplate template;

	public PhoneSystemApplication(JdbcTemplate _template) {
		template = _template;
	}

	public static void main(String[] args) {
		SpringApplication.run(PhoneSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		template.queryForObject("SELECT 1", Integer.class);
    }
}
