package com.githublookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.githublookup.client")
@EnableAsync
public class GitHubLookupApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitHubLookupApplication.class, args);
    }

}
