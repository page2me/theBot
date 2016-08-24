package com.tum.thebot.webapp.slack;

import com.tum.thebot.webapp.config.TestConfig;
import com.tum.thebot.webapp.properties.SlackProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class SlackClientTest {

    @Autowired
    private SlackClient slackClient;

    @Test
    public void testStartToken() {
        slackClient.startRequest();
    }

}