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
import ru.kogtev.vkbot.model.VkEvent;
import ru.kogtev.vkbot.utils.ApiMethod;
import ru.kogtev.vkbot.utils.Constants;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class VkBotResponse {
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
        nameValuePairs.add(new BasicNameValuePair("v", Constants.VK_API_VERSION));
        nameValuePairs.add(new BasicNameValuePair("random_id", String.valueOf(new SecureRandom().nextInt())));

        return nameValuePairs;
    }

    public void processResponse(ApiMethod method) {
        try (CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
                .build()) {
            HttpGet httpGet = new HttpGet(Constants.VK_API_ENDPOINT + method.getMethodPath());
            httpGet.setURI(new URIBuilder(httpGet.getURI()).addParameters(getQueryParameters()).build());
            processResponse(client, httpGet);
        } catch (Exception e) {
            //  LOG.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void processResponse(CloseableHttpClient client, HttpGet httpGet) {
        try (CloseableHttpResponse response = client.execute(httpGet)) {
            //   LOG.debug(httpGet.toString());

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            JsonNode jsonNode = new ObjectMapper().readTree(responseString);

            //    LOG.debug("Received: " + responseString);
            checkErrors(jsonNode.path("error"));
        } catch (Exception e) {
            //  LOG.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void checkErrors(JsonNode jsonNode) {
        if (!jsonNode.isEmpty()) {
            //  LOG.error("Received an error: '" + jsonNode.path("error_msg").asText() +
            //         "' with code [" + jsonNode.path("error_code").asText() + "]\n" +
            //          "The following request parameters were passed:\n" +
            //          jsonNode.path("request_params").toPrettyString());
        }
    }

}
