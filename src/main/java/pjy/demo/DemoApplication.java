package pjy.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	//api 명세서 확인
	//http://localhost:8080/swagger-ui/index.html

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
