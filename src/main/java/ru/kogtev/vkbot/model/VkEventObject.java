package ru.kogtev.vkbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.kogtev.vkbot.utils.Constants;

public class VkEventObject {
    @JsonProperty(Constants.EVENT_OBJECT_ID)
    private int id;

    @JsonProperty(Constants.EVENT_OBJECT_DATE)
    private int date;

    @JsonProperty(Constants.EVENT_OBJECT_OUT)
    private int out;

    @JsonProperty(Constants.EVENT_OBJECT_USER_ID)
    private int userId;

    @JsonProperty(Constants.EVENT_OBJECT_READ_STATE)
    private int readState;

    @JsonProperty(Constants.EVENT_OBJECT_TITLE)
    private String title;

    @JsonProperty(Constants.EVENT_OBJECT_BODY)
    private String body;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReadState() {
        return readState;
    }

    public void setReadState(int readState) {
        this.readState = readState;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
