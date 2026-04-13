package main;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
        "main",
        "service",
        "repository",
        "entity",
        "system",
        "scheduler",
        "Optimization"
})
@EnableJpaRepositories(basePackages = "repository") 
@EntityScan(basePackages = "entity")
@EnableScheduling
public class SmartSystemOptimizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartSystemOptimizerApplication.class, args);
    }
}