package ru.kogtev.vkbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.kogtev.vkbot.utils.CallbackApi;

public class VkEvent {
    public static final String EVENT_TYPE = "type";
    public static final String EVENT_OBJECT = "object";
    public static final String EVENT_SECRET = "secret";

    @JsonProperty(EVENT_TYPE)
    private CallbackApi type;

    @JsonProperty(EVENT_SECRET)
    private String secret;

    @JsonProperty(EVENT_OBJECT)
    private VkEventObject vkEventObject;

    public CallbackApi getType() {
        return type;
    }

    public String getSecret() {
        return secret;
    }

    public VkEventObject getVkEventObject() {
        return vkEventObject;
    }

    @Override
    public String toString() {
        return "VkEvent{" +
                "type=" + type +
                ", secret='" + secret + '\'' +
                ", vkEventObject=" + vkEventObject +
                '}';
    }
}
