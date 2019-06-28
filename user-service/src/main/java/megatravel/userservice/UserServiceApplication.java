package megatravel.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties
@EntityScan(basePackages = {"megatravel.userservice.model", "megatravel.backend.model"})
@ComponentScan(basePackages = {"megatravel.userservice.controller", "megatravel.backend.controller", "megatravel.userservice.service", "megatravel.backend.service", "megatravel.backend"})
@EnableJpaRepositories({"megatravel.backend.repository"})
public class UserServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
