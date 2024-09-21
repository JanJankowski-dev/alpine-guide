package com.example.alpineguide;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlpineGuideApplication {
	private static final Logger logger = LogManager.getLogger(AlpineGuideApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AlpineGuideApplication.class, args);
		logger.info("To jest komunikat informacyjny.");
		logger.error("To jest komunikat błędu.");
		logger.debug("To jest komunikat debug.");
	}

}
