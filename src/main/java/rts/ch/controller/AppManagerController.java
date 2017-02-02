package rts.ch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rts.ch.model.TweeterData;

import java.io.IOException;
import java.net.URL;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/")
public class AppManagerController {
    private static final String TWEETER_URL = "https://api.twitter.com/1.1/search/tweets.json";
    private ObjectMapper mapper;

    @RequestMapping(value = "/twitter", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    TweeterData getConfig() {
        final URL url;
        final String apiUrl = TWEETER_URL;
        try {
            url = new URL(apiUrl);
            return mapper.readValue(url, TweeterData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
