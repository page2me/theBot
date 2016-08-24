package com.tum.thebot.webapp.wss;

import com.tum.thebot.webapp.slack.data.SlackResponse;
import com.tum.thebot.webapp.slack.SlackClient;
import com.tum.thebot.webapp.slack.mapper.EventMapper;
import com.tum.thebot.webapp.slack.mapper.EventMapperBuilder;

public class MyWebSocketBuilder {

    private SlackClient slackClient;

    private SlackResponse slackResponse;

    private EventMapper eventMapper;

    public MyWebSocketBuilder withSlackClient(SlackClient slackClient) {
        this.slackClient = slackClient;
        return this;
    }

    public MyWebSocketBuilder withSlackResponse(SlackResponse slackResponse) {
        this.slackResponse = slackResponse;
        return this;
    }

    public MyWebSocketHandler build() {
        MyWebSocketHandler myWebSocketHandler = new MyWebSocketHandler();
        myWebSocketHandler.setSlackClient(slackClient);
        eventMapper = new EventMapperBuilder()
                .withSlackResponse(slackResponse)
                .build();
        myWebSocketHandler.setEventMapper(eventMapper);
        return myWebSocketHandler;
    }
}
