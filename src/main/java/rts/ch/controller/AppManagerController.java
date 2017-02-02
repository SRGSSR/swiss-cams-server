package rts.ch.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rts.ch.model.FilterParameters;
import rts.ch.model.Metadata;
import rts.ch.model.TweeterData;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/")
public class AppManagerController {
    private static final Pattern EMPTY_URL_PATTERN = Pattern.compile("([^:]/)/+");
    private static final String TWEETER_URL = "https://api.twitter.com/1.1/search/tweets.json";
    private static final String ROUNDSHOT_URL = "https://api.roundshot.com/api/get?key=ed63bfa6f55f2311d2efbee5e0b34f2c11641689";
    private ObjectMapper mapper = new ObjectMapper();

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

    /**
     *
     * @param params, request paramaters for roundshot call api
     * @return list of metadatas from roundshot
     */
    @RequestMapping(value = "/search", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Metadata> postSearchResponse(@RequestBody String params) {
        final URL url;
        try {
            final FilterParameters filterParams = mapper.readValue(params, FilterParameters.class);
            url = new URL(ROUNDSHOT_URL.concat(filterParams.toString()));
            return mapper.<ArrayList<Metadata>>readValue(url, new TypeReference<List<Metadata>>() {})
                    .stream()
                    .filter(meta -> !EMPTY_URL_PATTERN.matcher(meta.imageUrl).find())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param urlResource, the url resource
     * @return resource in array byte
     */
    @RequestMapping(value = "/searchResource", method = GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[]  getResourceResponse(@RequestParam(value = "urlResource") String[] urlResource) {
        final String urlName = "https:".concat(urlResource[0] + (urlResource.length > 1 ? "?" + urlResource[1] : ""));
        try {
            final InputStream in = new URL(urlName).openStream();
            return ByteStreams.toByteArray(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
