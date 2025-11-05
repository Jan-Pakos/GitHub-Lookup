package com.githublookup.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig implements RequestInterceptor {

    @Value("${github.token}")
    private String githubToken;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Accept", "application/vnd.github+json");
        template.header("X-GitHub-Api-Version", "2022-11-28");
        template.header("Authorization", "Bearer " + githubToken);
    }
}
