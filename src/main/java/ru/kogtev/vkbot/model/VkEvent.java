package ru.kogtev.vkbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.kogtev.vkbot.utils.ApiCallback;
import ru.kogtev.vkbot.utils.Constants;

public class VkEvent {
    @JsonProperty(Constants.EVENT_TYPE)
    private ApiCallback type;

    @JsonProperty(Constants.EVENT_GROUP_ID)
    private Long groupId;

    @JsonProperty(Constants.EVENT_ID)
    private String eventId;

    @JsonProperty(Constants.EVENT_SECRET)
    private String secret;

    @JsonProperty(Constants.EVENT_OBJECT)
    private VkEventObject vkEventObject;

    public ApiCallback getType() {
        return type;
    }

    public void setType(ApiCallback type) {
        this.type = type;
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

    public VkEventObject getVkEventObject() {
        return vkEventObject;
    }

    public void setVkEventObject(VkEventObject vkEventObject) {
        this.vkEventObject = vkEventObject;
    }

    @Override
    public String toString()
    {
        String message;

        if (type == ApiCallback.MESSAGE_NEW)
        {
            message = "type: '" + type.name() + "', body: '" +
                    vkEventObject.getBody() + "', from user_id: " +
                    vkEventObject.getUserId();
        }
        else if (type == ApiCallback.MESSAGE_REPLY)
        {
            message = "type: '" + type.name() + "', body: '" +
                    vkEventObject.getBody() + "', to user_id: " +
                    vkEventObject.getUserId();
        }
        else
        {
            message = "type: '" + type.name();
        }

        return message;
    }
}
