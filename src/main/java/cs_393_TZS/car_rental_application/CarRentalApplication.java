package cs_393_TZS.car_rental_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJpaRepositories("cs_393_TZS.car_rental_application.repository")
@ComponentScan(basePackages = "cs_393_TZS.car_rental_application")
public class CarRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}

}
