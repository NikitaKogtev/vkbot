package ru.kogtev.vkbot.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CallbackApi {
    MESSAGE_REPLY("message_reply"),
    CONFIRMATION("confirmation"),
    MESSAGE_NEW("message_new");

    @JsonValue
    private final String type;

    CallbackApi(String type) {
        this.type = type;
    }
}
