package io.rong.models.message;

import io.rong.messages.BaseMessage;

public class ChatroomMessage {
    public String senderUserId;
    public String[] targetIds;
    public String objectName;
    public BaseMessage content;

    public ChatroomMessage(String senderUserId, String[] targetIds, String objectName, BaseMessage content) {
        this.senderUserId = senderUserId;
        this.targetIds = targetIds;
        this.objectName = objectName;
        this.content = content;
    }

    public String getSenderUserId() {
        return this.senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String[] getTargetIds() {
        return this.targetIds;
    }

    public void setTargetIds(String[] targetIds) {
        this.targetIds = targetIds;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public BaseMessage getContent() {
        return this.content;
    }

    public void setContent(BaseMessage content) {
        this.content = content;
    }
}
