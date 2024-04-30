package br.com.vs1.imobiliaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ImobiliariaWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(ImobiliariaWebApplication.class, args);
    }

}
