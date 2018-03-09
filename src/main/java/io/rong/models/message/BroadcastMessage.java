package io.rong.models.message;

import io.rong.messages.BaseMessage;

public class BroadcastMessage extends Message{

    /**
     *
     * 定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息。
     * 如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。（可选）
     *
     * */
    public String pushContent;
    /**
     * 针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。（可选）
     */
    public String pushData;
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
