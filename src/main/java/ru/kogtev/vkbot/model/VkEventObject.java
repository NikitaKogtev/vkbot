package ru.kogtev.vkbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VkEventObject {
    public static final String EVENT_OBJECT_OUT = "out";
    public static final String EVENT_OBJECT_USER_ID = "peer_id";
    public static final String EVENT_OBJECT_BODY = "text";

    @JsonProperty(EVENT_OBJECT_OUT)
    private int out;

    @JsonProperty(EVENT_OBJECT_USER_ID)
    private int userId;

    @JsonProperty(EVENT_OBJECT_BODY)
    private String body;

    public int getUserId() {
        return userId;
    }

    public String getBody() {
        return body;
    }
}
