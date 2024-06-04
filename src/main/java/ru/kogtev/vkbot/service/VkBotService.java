package ru.kogtev.vkbot.service;

import org.springframework.stereotype.Service;
import ru.kogtev.vkbot.config.AppConfig;
import ru.kogtev.vkbot.model.VkEvent;
import ru.kogtev.vkbot.model.VkEventObject;
import ru.kogtev.vkbot.utils.VkCallbackEventType;

@Service
public class VkBotService {
    private final AppConfig appConfig;

    public VkBotService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public String processEvent(VkEvent event) {
        VkCallbackEventType eventType = VkCallbackEventType.fromString(event.getType());
        if (eventType == null) {
            // Неизвестный тип события
            return "Unknown event type";
        }

        switch (eventType) {
            case CONFIRMATION:
                // Обработка запроса подтверждения сервера
                return appConfig.getConfirmationToken();
            case MESSAGE_NEW:
                // Обработка нового сообщения
                return handleMessageNew(event.getObject());
            case MESSAGE_REPLY:
                // Обработка ответа на сообщение
                return handleReply(event.getObject());
            default:
                // Неизвестный тип события
                return "Unknown event type";
        }
    }

    private String handleMessageNew(VkEventObject eventObject) {
        // Получаем текст сообщения
        String messageText = eventObject.getBody();

        // Проверяем, что сообщение не пустое
        if (messageText != null && !messageText.isEmpty()) {
            // Логика обработки нового сообщения
            // В данном случае, просто отправляем подтверждение обработки события
            return "ok";
        } else {
            // Если сообщение пустое, возвращаем ошибку
            return "error";
        }
    }

    private String handleReply(VkEventObject eventObject) {
        // Получаем текст ответа на сообщение
        String replyText = eventObject.getBody();

        // Проверяем, что ответ не пустой
        if (replyText != null && !replyText.isEmpty()) {
            // Логика обработки ответа на сообщение
            // В данном случае, просто отправляем подтверждение обработки события
            return "ok";
        } else {
            // Если ответ пустой, возвращаем ошибку
            return "error";
        }
    }



}
