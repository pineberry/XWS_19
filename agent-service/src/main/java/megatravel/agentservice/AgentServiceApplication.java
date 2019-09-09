package megatravel.agentservice;

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
@EntityScan(basePackages = {"megatravel.agentservice.model"})
@ComponentScan(basePackages = {"megatravel.agentservice.controller", "megatravel.agentservice.service", "megatravel.backend.service", "megatravel.backend"})
@EnableJpaRepositories({"megatravel.backend.repository", "megatravel.agentservice.repository"})
public class AgentServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AgentServiceApplication.class, args);
	}

}
