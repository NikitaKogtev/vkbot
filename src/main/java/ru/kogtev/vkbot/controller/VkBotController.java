package ru.kogtev.vkbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kogtev.vkbot.config.AppConfig;
import ru.kogtev.vkbot.model.VkEvent;
import ru.kogtev.vkbot.service.VkBotService;

@RestController
public class VkBotController {
    private final VkBotService vkBotService;
    private final AppConfig appConfig;

    public VkBotController(VkBotService vkBotService, AppConfig appConfig) {
        this.vkBotService = vkBotService;
        this.appConfig = appConfig;
    }

    @PostMapping
    public ResponseEntity<String> handleVkWebhook(@RequestBody VkEvent event) {
        // Проверяем, что секретный ключ совпадает с ожидаемым
        if (!event.getSecret().equals(appConfig.getSecretKey())){
            // Если секретный ключ не совпадает, возвращаем ошибку
            return ResponseEntity.badRequest().body("Invalid secret key");
        }
        // Передаем событие на обработку сервису
        vkBotService.processEvent(event);

        // Возвращаем успешный ответ
        return ResponseEntity.ok("accept");
    }
}
