package net.sxbang.watchapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class WatchappApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatchappApplication.class, args);
    }

}
