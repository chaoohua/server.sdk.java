package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * 广播消息体
 * @author hc
 */
public class BroadcastMessage extends Message {

    public String os;

    public BroadcastMessage() {
    }

    public BroadcastMessage(String senderUserId, String objectName,
                            BaseMessage content, String pushContent, String pushData, String os) {
        this.senderUserId = senderUserId;
        this.objectName = objectName;
        this.content = content;
        this.pushContent = pushContent;
        this.pushData = pushData;
        this.os = os;
    }

    @Override
    public String getSenderUserId() {
        return this.senderUserId;
    }
    @Override
    public BroadcastMessage setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
        return this;
    }
    @Override
    public String getObjectName() {
        return this.objectName;
    }
    @Override
    public BroadcastMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }
    @Override
    public BaseMessage getContent() {
        return this.content;
    }
    @Override
    public BroadcastMessage setContent(BaseMessage content) {
        this.content = content;
        return this;
    }
    @Override
    public String getPushContent() {
        return this.pushContent;
    }
    @Override
    public BroadcastMessage setPushContent(String pushContent) {
        this.pushContent = pushContent;
        return this;
    }
    @Override
    public String getPushData() {
        return this.pushData;
    }
    @Override
    public BroadcastMessage setPushData(String pushData) {
        this.pushData = pushData;
        return this;
    }

    public String getOs() {
        return this.os;
    }

    public BroadcastMessage setOs(String os) {
        this.os = os;
        return this;
    }
}
