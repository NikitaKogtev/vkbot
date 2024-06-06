package ru.kogtev.vkbot.utils;

public enum ApiMethod {
    MESSAGE_SEND(Constants.VK_API_METHOD_MESSAGE_SEND);

    private final String methodPath;

    ApiMethod(String methodPath) {
        this.methodPath = methodPath;
    }

    public String getMethodPath() {
        return methodPath;
    }
}

