package ru.kogtev.vkbot.model;

public class VkEvent {
    private String type;
    private VkEventObject object;
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
        return object;
    }

    public void setObject(VkEventObject object) {
        this.object = object;
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
