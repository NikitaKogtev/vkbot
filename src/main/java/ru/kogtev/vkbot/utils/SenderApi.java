package ru.kogtev.vkbot.utils;

public enum SenderApi {
    MESSAGE_SEND("messages.send");

    private final String methodPath;

    SenderApi(String methodPath) {
        this.methodPath = methodPath;
    }

    public String getMethodPath() {
        return methodPath;
    }
}

