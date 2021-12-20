package com.glitch.servermanagement;

import com.glitch.servermanagement.enumeration.Status;
import com.glitch.servermanagement.models.Server;
import com.glitch.servermanagement.repos.ServerRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerManagementApplication.class, args);
	}
	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(null, "192.168.1.150", "Ubuntu Server", "8GB", "Gl1tch PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.151", "Ubuntu Web Server", "24GB", "Web server", "http://localhost:8080/server/image/server2.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.152", "Fedora Server", "16GB", "Work station", "http://localhost:8080/server/image/server3.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.153", "JsMailer", "2GB", "Mailing server", "http://localhost:8080/server/image/server4.png", Status.SERVER_UP));
		};
	}
}
