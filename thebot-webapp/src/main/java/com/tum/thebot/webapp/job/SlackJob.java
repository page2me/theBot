package com.tum.thebot.webapp.job;

import com.tum.thebot.webapp.slack.data.SlackResponse;
import com.tum.thebot.webapp.slack.SlackClient;
import com.tum.thebot.webapp.wss.MyWebSocketBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class SlackJob {

    @Autowired
    private WebSocketClient webSocketClient;

    @Autowired
    private SlackClient slackClient;

    private WebSocketConnectionManager manager;

    @PostConstruct
    public void init() {
        SlackResponse slackResponse = slackClient.startRequest();
        WebSocketHandler webSocketHandler = new MyWebSocketBuilder()
                .withSlackClient(slackClient)
                .withSlackResponse(slackResponse)
                .build();
        manager = new WebSocketConnectionManager(webSocketClient, webSocketHandler, slackResponse.getUrl());
        manager.start();
    }

    @PreDestroy
    public void teardown() {
        if (manager != null) {
            manager.stop();
        }
    }
}
