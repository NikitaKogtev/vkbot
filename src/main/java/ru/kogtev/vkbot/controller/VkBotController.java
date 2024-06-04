package ru.kogtev.vkbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kogtev.vkbot.config.AppConfig;
import ru.kogtev.vkbot.model.VkEvent;
import ru.kogtev.vkbot.model.VkEventObject;
import ru.kogtev.vkbot.service.VkBotService;

@RestController
public class VkBotController {
    private final VkBotService vkBotService;
    private final AppConfig appConfig;

    public VkBotController(VkBotService vkBotService, AppConfig appConfig) {
        this.vkBotService = vkBotService;
        this.appConfig = appConfig;
    }

    @PostMapping("/vk-webhook")
    public ResponseEntity<String> handleVkWebhook(@ResponseBody VkEvent event) {
        if (!event.getSecret().equals(appConfig.getSecretKey())){

        }
    }
}
