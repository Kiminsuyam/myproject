package myproject.myprojectspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScans({
		@ComponentScan(basePackages = "myproject")
})
@SpringBootApplication(scanBasePackages = "myproject")
// @EnableScheduling
public class MyprojectSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyprojectSpringApplication.class, args);
	}

}
