package com.tum.thebot.webapp.slack.transform;

import com.tum.thebot.webapp.slack.mapper.RTMResponseMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by tatum on 8/16/2016 AD.
 */
public class RTMResponseTranformerTest {

    private RTMResponseMapper rtmResponseTranformer = new RTMResponseMapper();

    @Test
    public void transform() throws Exception {

        try(InputStream in = RTMResponseTranformerTest.class.getResourceAsStream("/respon.json")) {
            String response = IOUtils.toString( in,
                    StandardCharsets.UTF_8);

            rtmResponseTranformer.transform(response);
        }
    }

}