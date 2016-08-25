package com.tum.thebot.webapp.config;


import com.tum.thebot.webapp.slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class RTSConfig {

    @Autowired
    private SlackClient slackClient;

//    @Bean
//    public WebSocketHandler webSocketHandler() {
//        return new MyWebSocketHandler();
//    }

    @Bean
    public WebSocketClient webSocketClient() {
        WebSocketClient client = new StandardWebSocketClient();
        return client;
    }

}
