package br.com.clientes.Clientes.Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ClientesDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientesDemoApplication.class, args);
	}

}
