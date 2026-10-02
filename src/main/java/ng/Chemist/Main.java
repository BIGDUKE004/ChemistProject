package ng.Chemist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("=== MONGODB_URI is: [" + System.getenv("MONGODB_URI") + "] ===");
        System.out.println("=== JWT_SECRET is set: " + (System.getenv("JWT_SECRET") != null) + " ===");
        SpringApplication.run(Main.class, args);
    }
}