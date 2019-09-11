package megatravel.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties
@EntityScan(basePackages = {"megatravel.backend.model", "megatravel.backend.soap"})
@ComponentScan(basePackages = {"megatravel.backend.controller", "megatravel.backend.service", "megatravel.backend", "megatravel.backend.soap"})
@ServletComponentScan
public class BackendApplication {
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}


}
