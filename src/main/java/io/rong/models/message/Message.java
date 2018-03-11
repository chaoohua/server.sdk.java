package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * 发送消息的消息体
 * @author RongCloud
 */
public class Message {

    public String senderUserId;
    /**
     *
     * 接受 Id 可能是用户Id，聊天Id ，群组Id，讨论组Id（必传）
     **/
    public String[] targetIds;
    /**
     *消息类型 （必传）
     **/
    public String objectName;
    /**
     * 发送消息内容，参考融云消息类型表.示例说明；如果 objectName
     * 为自定义消息类型，该参数可自定义格式。（必传）。
     **/
    public BaseMessage content;
    /**
     * 定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，
     * 则发送后用户一定会收到 Push 信息。如果为自定义消息，则 pushContent
     * 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。（可选）
     */
    public String pushContent;
    /**
     * 针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。（可选）
     */
    public String pushData;


    public Message() {
    }

    public Message(String senderUserId, String[] targetIds, String objectName, BaseMessage content,
                   String pushContent, String pushData) {
        this.senderUserId = senderUserId;
        this.targetIds = targetIds;
        this.objectName = objectName;
        this.content = content;
        this.pushContent = pushContent;
        this.pushData = pushData;
    }

    public String[] getTargetIds() {
        return this.targetIds;
    }

    public Message setTargetIds(String[] targetIds) {
        this.targetIds = targetIds;
        return this;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public Message setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public BaseMessage getContent() {
        return this.content;
    }

    public Message setContent(BaseMessage content) {
        this.content = content;
        return this;
    }

    public String getPushContent() {
        return this.pushContent;
    }

    public Message setPushContent(String pushContent) {
        this.pushContent = pushContent;
        return this;
    }

    public String getPushData() {
        return this.pushData;
    }

    public Message setPushData(String pushData) {
        this.pushData = pushData;
        return this;
    }

    public String getSenderUserId() {
        return this.senderUserId;
    }

    public Message setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
        return this;
    }


}
