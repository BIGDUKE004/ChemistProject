package ng.Chemist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public static void main(String[] args) {
    System.out.println("MONGODB_URI EXISTS: " + (System.getenv("MONGODB_URI") != null));
    SpringApplication.run(Main.class, args);
}