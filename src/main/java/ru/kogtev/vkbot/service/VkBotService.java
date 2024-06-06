package ru.kogtev.vkbot.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kogtev.vkbot.utils.ApiCallback;
import ru.kogtev.vkbot.config.AppConfig;
import ru.kogtev.vkbot.utils.ApiMethod;
import ru.kogtev.vkbot.model.VkEvent;


@Service
public class VkBotService {
    private static final Logger LOG = LogManager.getLogger(VkBotService.class);

    private final AppConfig config;

    @Autowired
    public VkBotService(AppConfig config) {
        this.config = config;
    }

    public String doResponse(VkEvent event) {
        if (!event.getSecret().equals(config.getSecretKey())) {
            LOG.error("Received secret key does not match the one specified in the application.yaml configuration: {}", event.getSecret());

            return "error";
        }

        // LOG.debug("Received: {}", event.toString());

        if (event.getType() == ApiCallback.CONFIRMATION) {
            return config.getConfirmationToken();
        } else if (event.getType() == ApiCallback.MESSAGE_NEW) {
            System.out.println(event.getType());
            System.out.println(event.getVkEventObject());
            new VkBotResponse(event, config.getAccessToken()).processResponse(ApiMethod.MESSAGE_SEND);
        }
        return "ok";
    }
}
