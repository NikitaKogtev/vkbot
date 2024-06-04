package ru.kogtev.vkbot.utils;

public enum VkCallbackEventType {
    MESSAGE_REPLY("message_reply"),
    CONFIRMATION("confirmation"),
    MESSAGE_NEW("message_new");

    private final String eventName;

    VkCallbackEventType(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public static VkCallbackEventType fromString(String text) {
        for (VkCallbackEventType eventType : VkCallbackEventType.values()) {
            if (eventType.eventName.equalsIgnoreCase(text)) {
                return eventType;
            }
        }
        return null;
    }
}
