package com.javacoding.springredditclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.javacoding.springredditclone.config.SwaggerConfiguration;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class SpringredditcloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringredditcloneApplication.class, args);
	}

}
