package io.rong.models.message;

import io.rong.messages.BaseMessage;

public class ChatroomMessage {
    public String senderUserId;
    public String[] targetIds;
    public String objectName;
    public BaseMessage content;

    public ChatroomMessage() {
    }

    public ChatroomMessage(String senderUserId, String[] targetIds, String objectName, BaseMessage content) {
        this.senderUserId = senderUserId;
        this.targetIds = targetIds;
        this.objectName = objectName;
        this.content = content;
    }

    public String getSenderUserId() {
        return this.senderUserId;
    }

    public ChatroomMessage setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
        return this;
    }

    public String[] getTargetIds() {
        return this.targetIds;
    }

    public ChatroomMessage setTargetIds(String[] targetIds) {
        this.targetIds = targetIds;
        return this;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public ChatroomMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public BaseMessage getContent() {
        return this.content;
    }

    public ChatroomMessage setContent(BaseMessage content) {
        this.content = content;
        return this;
    }
}
