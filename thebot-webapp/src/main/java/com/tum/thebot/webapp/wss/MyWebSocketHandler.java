package com.tum.thebot.webapp.wss;

import com.tum.thebot.webapp.slack.SlackClient;
import com.tum.thebot.webapp.slack.data.SlackEvent;
import com.tum.thebot.webapp.slack.mapper.EventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Arrays;
import java.util.List;

public class MyWebSocketHandler implements WebSocketHandler {

    private static Logger log = LoggerFactory.getLogger(MyWebSocketHandler.class);

    private static List<String> answer = Arrays.asList("เก่ง","เทพ","สุดยอด");

    private static int index = 0;

    private SlackClient slackClient;

    private EventMapper eventMapper;

    public void setSlackClient(SlackClient slackClient) {
        this.slackClient = slackClient;
    }

    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("opened connection");
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        log.info("message: {}",webSocketMessage.getPayload());
        if(webSocketMessage instanceof TextMessage) {
            TextMessage txt = (TextMessage) webSocketMessage;
            SlackEvent message = eventMapper.transform(txt.getPayload());
            log.info("message: {}, channel: {}, user: {}",
                    message.getMessage(), message.getChannelName(), message.getUserName());
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.info("closed connection");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
