package gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiGateway4Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiGateway4Application.class, args);
	}

}
