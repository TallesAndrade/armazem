package com.talles.armazemdamargarete;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArmazemDaMargareteApplication {

    public static void main(String[] args) {
        Dotenv env = Dotenv.load();
        env.entries().forEach((entry) -> System.setProperty(entry.getKey(), entry.getValue()));
        SpringApplication.run(ArmazemDaMargareteApplication.class, args);
    }

}
