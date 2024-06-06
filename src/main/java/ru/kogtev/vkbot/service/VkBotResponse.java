package ru.kogtev.vkbot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.kogtev.vkbot.model.VkEvent;
import ru.kogtev.vkbot.utils.SenderApi;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class VkBotResponse {
    public static final String VK_API_ENDPOINT = "https://api.vk.com/method/";
    public static final String VK_API_VERSION = "5.89";

    private static final Logger LOGGER = LogManager.getLogger(VkBotResponse.class);


    private final VkEvent vkEvent;

    private final String accessToken;

    public VkBotResponse(VkEvent vkEvent, String accessToken) {
        this.vkEvent = vkEvent;
        this.accessToken = accessToken;
    }

    public List<NameValuePair> getQueryParameters() {
        List<NameValuePair> nameValuePairs = new ArrayList<>();

        nameValuePairs.add(new BasicNameValuePair("message", "Вы написали: " + vkEvent.getVkEventObject().getBody()));
        nameValuePairs.add(new BasicNameValuePair("peer_id", String.valueOf(vkEvent.getVkEventObject().getUserId())));
        nameValuePairs.add(new BasicNameValuePair("access_token", accessToken));
        nameValuePairs.add(new BasicNameValuePair("v", VK_API_VERSION));
        nameValuePairs.add(new BasicNameValuePair("random_id", String.valueOf(new SecureRandom().nextInt())));

        return nameValuePairs;
    }

    public void processResponse(SenderApi method) {
        try (CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
                .build()) {
            HttpGet httpGet = new HttpGet(VK_API_ENDPOINT + method.getMethodPath());
            httpGet.setURI(new URIBuilder(httpGet.getURI()).addParameters(getQueryParameters()).build());
            processResponse(client, httpGet);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void processResponse(CloseableHttpClient client, HttpGet httpGet) {
        try (CloseableHttpResponse response = client.execute(httpGet)) {
            LOGGER.debug(httpGet.toString());

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            JsonNode jsonNode = new ObjectMapper().readTree(responseString);

            LOGGER.debug("Received: {}", responseString);
            checkErrors(jsonNode.path("error"));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void checkErrors(JsonNode jsonNode) {
        if (!jsonNode.isEmpty()) {
            LOGGER.error("Received an error: '{}' with code [{}]\nThe following request parameters were " +
                    "passed:\n{}", jsonNode.path("error_msg").asText(),
                    jsonNode.path("error_code").asText(),
                    jsonNode.path("request_params").toPrettyString());
        }
    }
}
