package com.tum.thebot.webapp.slack.mapper;

import com.tum.thebot.webapp.slack.data.SlackChannel;
import com.tum.thebot.webapp.slack.data.SlackGroup;
import com.tum.thebot.webapp.slack.data.SlackResponse;
import com.tum.thebot.webapp.slack.data.SlackUser;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventMapperBuilder {

    public SlackResponse slackResponse;

    public EventMapperBuilder withSlackResponse(SlackResponse slackResponse) {
        this.slackResponse = slackResponse;
        return this;
    }

    public EventMapper build() {
        EventMapper eventMapper = new EventMapper();
        eventMapper.setSlackChannelMapper(createChannelMapper(slackResponse.getSlackGroups()
                ,slackResponse.getSlackChannels()));
        eventMapper.setSlackUserMapper(createUserMapper(slackResponse.getSlackUserList()));
        return eventMapper;
    }

    private Map<String,String> createUserMapper(List<SlackUser> slackUsers) {
        if (slackUsers != null) {
            return slackUsers.stream()
                    .collect(Collectors.toMap(SlackUser::getId, SlackUser::getName));
        } else {
            return Collections.emptyMap();
        }
    }

    private Map<String, String> createChannelMapper(List<SlackGroup> slackGroups, List<SlackChannel> slackChannels) {
        Map<String, String> map1;
        if (slackGroups != null) {
            map1 = slackGroups.stream()
                    .collect(Collectors.toMap(SlackGroup::getId, SlackGroup::getName));
        } else {
            map1 = new HashMap<>();
        }

        Map<String, String> map2;
        if (slackChannels != null) {
            map2 = slackChannels.stream()
                    .collect(Collectors.toMap(SlackChannel::getId, SlackChannel::getName));
        } else {
            map2 = new HashMap<>();
        }

        map1.putAll(map2);
        return map1;
    }

}
