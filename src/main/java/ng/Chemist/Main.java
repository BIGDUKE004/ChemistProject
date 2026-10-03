package ng.Chemist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        String mongoUri = System.getenv("MONGODB_URI");
        System.out.println("=== RAW MONGODB_URI: [" + mongoUri + "] ===");
        System.getenv().keySet().forEach(k -> {
            if (k.toUpperCase().contains("MONGO")) {
                System.out.println("=== Found env key containing MONGO: [" + k + "] ===");
            }
        });
        if (mongoUri != null && !mongoUri.isBlank()) {
            System.setProperty("spring.data.mongodb.uri", mongoUri);
        }
        SpringApplication.run(Main.class, args);
    }
}