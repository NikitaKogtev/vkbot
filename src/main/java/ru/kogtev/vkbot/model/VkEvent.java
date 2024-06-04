package ru.kogtev.vkbot.model;

public class VkEvent {
    private String type;
    private VkEventObject vkEventObject;
    private Long groupId;
    private String eventId;
    private String secret;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public VkEventObject getObject() {
        return vkEventObject;
    }

    public void setObject(VkEventObject vkEventObject) {
        this.vkEventObject = vkEventObject;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
