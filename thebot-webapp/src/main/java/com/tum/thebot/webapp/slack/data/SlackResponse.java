package com.tum.thebot.webapp.slack.data;

import java.util.List;

public class SlackResponse {

    private String url;

    private List<SlackUser> slackUserList;

    private List<SlackGroup> slackGroups;

    private List<SlackChannel> slackChannels;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<SlackUser> getSlackUserList() {
        return slackUserList;
    }

    public void setSlackUserList(List<SlackUser> slackUserList) {
        this.slackUserList = slackUserList;
    }

    public List<SlackGroup> getSlackGroups() {
        return slackGroups;
    }

    public void setSlackGroups(List<SlackGroup> slackGroups) {
        this.slackGroups = slackGroups;
    }

    public List<SlackChannel> getSlackChannels() {
        return slackChannels;
    }

    public void setSlackChannels(List<SlackChannel> slackChannels) {
        this.slackChannels = slackChannels;
    }
}
