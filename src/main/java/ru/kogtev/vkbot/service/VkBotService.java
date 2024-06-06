package ru.kogtev.vkbot.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kogtev.vkbot.config.AppConfig;
import ru.kogtev.vkbot.model.VkEvent;
import ru.kogtev.vkbot.utils.SenderApi;


@Service
public class VkBotService {
    private static final Logger LOGGER = LogManager.getLogger(VkBotService.class);

    private final AppConfig config;

    @Autowired
    public VkBotService(AppConfig config) {
        this.config = config;
    }

    public String doResponse(VkEvent vkEvent) {
        if (!vkEvent.getSecret().equals(config.getSecretKey())) {
            LOGGER.error("Received secret key does not match the one specified in the application.properties" +
                    " configuration: {}", vkEvent.getSecret());

            return "error";
        }

        LOGGER.debug("Received: {}", vkEvent.toString());

        switch (vkEvent.getType()) {
            case CONFIRMATION:
                return config.getConfirmationToken();
            case MESSAGE_NEW:
                new VkBotResponse(vkEvent, config.getAccessToken()).processResponse(SenderApi.MESSAGE_SEND);
                break;
        }

        return "ok";
    }
}
