package com.tum.thebot.webapp.websocket.reply;

import com.tum.thebot.webapp.slack.SlackClient;
import com.tum.thebot.webapp.slack.data.SlackEvent;
import com.tum.thebot.webapp.websocket.annotations.SlackReply;

public class HelloReply {

    private SlackClient slackClient;

    public void setSlackClient(SlackClient slackClient) {
        this.slackClient = slackClient;
    }

    @SlackReply(pattern = "สวัสดี")
    public void hello(SlackEvent message) {

    }
}
