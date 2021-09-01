package dev.patika.homework04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"dev.patika.homework04.mappers"})
@EnableSwagger2
public class Homework04Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework04Application.class, args);
    }

}