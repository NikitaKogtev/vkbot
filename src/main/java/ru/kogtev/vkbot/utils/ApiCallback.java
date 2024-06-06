package ru.kogtev.vkbot.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApiCallback {
    MESSAGE_REPLY(Constants.CALLBACK_API_EVENT_MESSAGE_REPLY),
    CONFIRMATION(Constants.CALLBACK_API_EVENT_CONFIRMATION),
    MESSAGE_NEW(Constants.CALLBACK_API_EVENT_MESSAGE_NEW);

    @JsonValue
    private final String type;

    ApiCallback(String type) {
        this.type = type;
    }
}
