package com.op.be.usercard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OnepieceUserCardApplication extends SpringBootServletInitializer {

	private static Class<OnepieceUserCardApplication> applicationClass = OnepieceUserCardApplication.class;

	public static void main(String[] args) {
		SpringApplication.run(OnepieceUserCardApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

}
