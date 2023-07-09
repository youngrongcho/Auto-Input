package auto.input;

import auto.input.service.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class InputApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(InputApplication.class, args);

		ServiceImpl service = new ServiceImpl();
		service.input();
	}
}