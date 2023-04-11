package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class RetoInicialConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetoInicialConfigApplication.class, args);
	}

}
