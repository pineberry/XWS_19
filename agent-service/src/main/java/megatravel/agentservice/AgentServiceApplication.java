package megatravel.agentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import megatravel.backend.model.User;
import megatravel.backend.service.UserService;
import megatravel.backend.dto.UserListDTO;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties
@EntityScan(basePackages = {"megatravel.agentservice.model", "megatravel.backend.model"})
@ComponentScan(basePackages = {"megatravel.agentservice.controller", "megatravel.agentservice.service", "megatravel.backend.service", "megatravel.backend"})
@EnableJpaRepositories({"megatravel.backend.repository", "megatravel.agentservice.repository"})
public class AgentServiceApplication {

		
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserService userService;
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		//reads all and puts it in temp userlistdto
		UserListDTO temp = restTemplate.getForObject("http://backend/user/all", UserListDTO.class);
		//unpacks users from temp and for every 
		for (User user : temp.getUsers()) {
			userService.create(user);
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AgentServiceApplication.class, args);
	}

}
