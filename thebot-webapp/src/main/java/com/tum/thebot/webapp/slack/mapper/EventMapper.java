package com.tum.thebot.webapp.slack.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tum.thebot.webapp.slack.data.SlackEvent;

import java.io.IOException;
import java.util.Map;

public class EventMapper {

    private Map<String, String> slackUserMapper;

    private Map<String, String> slackChannelMapper;

    public void setSlackUserMapper(Map<String, String> slackUserMapper) {
        this.slackUserMapper = slackUserMapper;
    }

    public void setSlackChannelMapper(Map<String, String> slackChannelMapper) {
        this.slackChannelMapper = slackChannelMapper;
    }

    public synchronized SlackEvent transform(String response) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode jsonNode = mapper.readTree(response);
            return process(jsonNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private SlackEvent process(JsonNode jsonNode) {
        SlackEvent slackEvent = new SlackEvent();
        jsonNode.fields().forEachRemaining( eachEntry -> {
            switch (eachEntry.getKey()) {
                case "type":
                    slackEvent.setType(eachEntry.getValue().asText());
                    break;
                case "text":
                    slackEvent.setMessage(eachEntry.getValue().asText());
                    break;
                case "channel":
                    slackEvent.setChannelId(eachEntry.getValue().asText());
                    slackEvent.setChannelName(slackChannelMapper.get(slackEvent.getChannelId()));
                    break;
                case "user":
                    slackEvent.setUserId(eachEntry.getValue().asText());
                    slackEvent.setUserName(slackUserMapper.get(slackEvent.getUserId()));
                    break;
                default:
                    break;
            }
        });
        return slackEvent;
    }
}
