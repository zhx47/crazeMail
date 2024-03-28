package xyz.zhx47.crazemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class CrazeMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrazeMailApplication.class, args);
    }

}

