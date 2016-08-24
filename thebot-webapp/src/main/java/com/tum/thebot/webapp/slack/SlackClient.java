package com.tum.thebot.webapp.slack;

import com.tum.thebot.webapp.slack.data.SlackResponse;
import com.tum.thebot.webapp.properties.SlackProperties;
import com.tum.thebot.webapp.slack.mapper.RTMResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class SlackClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SlackProperties slackProperties;


    private static final String RTM_START = "https://slack.com/api/rtm.start";

    private static final String POST_MESSAGE = "https://slack.com/api/chat.postMessage";

    private static Logger logger = LoggerFactory.getLogger(SlackClient.class);

    public SlackResponse startRequest() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("token",slackProperties.getToken());
        String response = restTemplate.postForObject(RTM_START,form,String.class);
        logger.debug("response: {}", response);
        RTMResponseMapper tranformer = new RTMResponseMapper();
        SlackResponse obj = tranformer.transform(response);
        return obj;
    }

    public void sendMessage(String message, String channel) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("token",slackProperties.getToken());
        form.add("username",slackProperties.getUsername());
        form.add("channel", channel);
        form.add("text",message);
        String response = restTemplate.postForObject(POST_MESSAGE,form,String.class);
        logger.debug("response: {}", response);
    }
}
