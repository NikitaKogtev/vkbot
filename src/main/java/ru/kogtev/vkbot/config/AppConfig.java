package ru.kogtev.vkbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${vk.token.confirmation}")
    private String confirmationToken;

    @Value("${vk.token.access}")
    private String accessToken;

    @Value("${vk.secret.key}")
    private String secretKey;

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
