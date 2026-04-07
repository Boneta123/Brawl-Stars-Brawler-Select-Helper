package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // scheduling
@SpringBootApplication // main method declared
@EnableAsync // allows async ( multi threading )
public class BrawlSelectorHelpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrawlSelectorHelpApplication.class, args);
    }

}
