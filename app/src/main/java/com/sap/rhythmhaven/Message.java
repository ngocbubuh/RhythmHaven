package com.sap.rhythmhaven;

public class Message {
    private String content;
    private boolean isReceived;

    public Message(String content, boolean isReceived) {
        this.content = content;
        this.isReceived = isReceived;
    }

    public String getContent() {
        return content;
    }

    public boolean isReceived() {
        return isReceived;
    }
}
