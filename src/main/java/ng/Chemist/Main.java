package ng.Chemist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        String resolved = context.getEnvironment().getProperty("spring.data.mongodb.uri");
        System.out.println("=== Spring resolved mongodb.uri as: [" + resolved + "] ===");
    }
}