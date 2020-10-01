package org.xcasemanager.messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
@ComponentScan(basePackages = "org.xcasemanager.messenger")
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}