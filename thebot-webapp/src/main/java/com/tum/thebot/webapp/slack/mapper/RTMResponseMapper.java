package com.tum.thebot.webapp.slack.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.tum.thebot.webapp.slack.data.SlackChannel;
import com.tum.thebot.webapp.slack.data.SlackGroup;
import com.tum.thebot.webapp.slack.data.SlackResponse;
import com.tum.thebot.webapp.slack.data.SlackUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RTMResponseMapper {

    private static Logger log = LoggerFactory.getLogger(RTMResponseMapper.class);

    private SlackResponse slackResponse;

    public synchronized SlackResponse transform(String response) {
        ObjectMapper mapper = new ObjectMapper();
        slackResponse = new SlackResponse();
        try {
            JsonNode jsonNode = mapper.readTree(response);

            jsonNode.fields().forEachRemaining( this::process );

        } catch (IOException e) {
            e.printStackTrace();
        }
        return slackResponse;
    }

    private void process(Map.Entry<String,JsonNode> content) {
        log.debug("name: {}", content.getKey());
        switch (content.getKey()) {
            case "ok":
                log.info(content.getValue().asText());
                break;
            case "url":
                String url = content.getValue().asText();
                log.info("url: {}", url);
                slackResponse.setUrl(url);
                break;
            case "users":
                slackResponse.setSlackUserList( processUsers( content.getValue() ));
                break;
            case "groups":
                slackResponse.setSlackGroups( processGroup( content.getValue() ));
                break;
            case "channels":
                slackResponse.setSlackChannels( processChannel( content.getValue() ));
                break;

        }
    }

    private List<SlackChannel> processChannel(JsonNode jsonNode) {
        if (jsonNode.isArray()) {
            ArrayNode arrayNodes = (ArrayNode) jsonNode;
            return StreamSupport.stream(Spliterators.spliteratorUnknownSize( arrayNodes.elements()
                    , Spliterator.ORDERED) ,false)
                    .map(this::convertNodeToSlackChannel)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    private List<SlackGroup> processGroup(JsonNode jsonNode) {
        if (jsonNode.isArray()) {
            ArrayNode arrayNodes = (ArrayNode) jsonNode;
            return StreamSupport.stream(Spliterators.spliteratorUnknownSize( arrayNodes.elements()
                    , Spliterator.ORDERED) ,false)
                    .map(this::convertNodeToSlackGroup)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    private List<SlackUser> processUsers(JsonNode jsonNode) {
        if (jsonNode.isArray()) {
            ArrayNode arrayNodes = (ArrayNode) jsonNode;
            return StreamSupport.stream(Spliterators.spliteratorUnknownSize( arrayNodes.elements()
                    , Spliterator.ORDERED) ,false)
                    .map(this::convertNodeToSlackUser)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    private SlackGroup convertNodeToSlackGroup(JsonNode jsonNode) {
        SlackGroup slackGroup = new SlackGroup();
        slackGroup.setId(jsonNode.get("id").asText());
        slackGroup.setName(jsonNode.get("name").asText());
        return slackGroup;
    }

    private SlackUser convertNodeToSlackUser(JsonNode jsonNode) {
        SlackUser slackUser = new SlackUser();
        slackUser.setId(jsonNode.get("id").asText());
        slackUser.setTeamId(jsonNode.get("team_id").asText());
        slackUser.setName(jsonNode.get("name").asText());
        slackUser.setRealName(safeGetJsonNode(jsonNode,"real_name"));
        return slackUser;
    }

    private SlackChannel convertNodeToSlackChannel(JsonNode jsonNode) {
        SlackChannel slackChannel = new SlackChannel();
        slackChannel.setId(jsonNode.get("id").asText());
        slackChannel.setName(jsonNode.get("name").asText());
        return slackChannel;
    }

    private String safeGetJsonNode(JsonNode jsonNode, String name) {
        if(jsonNode.get(name) != null) {
            return jsonNode.get("real_name").asText();
        } else {
            return null;
        }
    }

}
