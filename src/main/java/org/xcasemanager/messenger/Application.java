package org.xcasemanager.messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication(scanBasePackages = "org.xcasemanager.messenger")
@EnableRetry
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}