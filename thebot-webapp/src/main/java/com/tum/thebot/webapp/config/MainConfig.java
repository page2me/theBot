package com.tum.thebot.webapp.config;
import com.tum.thebot.webapp.slack.SlackClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MainConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
