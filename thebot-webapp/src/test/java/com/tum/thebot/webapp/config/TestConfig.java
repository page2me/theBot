package com.tum.thebot.webapp.config;

import com.tum.thebot.webapp.properties.SlackProperties;
import com.tum.thebot.webapp.slack.SlackClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

@Configuration
public class TestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SlackClient slackClient() {
        return new SlackClient();
    }

    @Bean
    public SlackProperties slackProperties() {
        SlackProperties slackProperties = new SlackProperties();
        slackProperties.setToken("xxxxxxx");
        return slackProperties;
    }

}