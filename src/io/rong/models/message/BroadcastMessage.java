package io.rong.models.message;

import io.rong.messages.BaseMessage;

public class BroadcastMessage {
    public String senderUserId;
    //toUserId:接收用户 Id，可以实现向多人发送消息，每次上限为 1000 人。（必传）
    public String objectName;
    public BaseMessage content;
    /**
    *
    * 定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息。
    * 如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。（可选）
    *
    * */
    public String pushContent;
    //针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。（可选）
    public String pushData;
    //针对操作系统发送 Push，值为 iOS 表示对 iOS 手机用户发送 Push ,为 Android 时表示对 Android 手机用户发送 Push ，如对所有用户发送 Push 信息，则不需要传 os 参数。（可选）
    public String os;

    public BroadcastMessage(String senderUserId, String objectName,
                            BaseMessage content, String pushContent, String pushData, String os) {
        this.senderUserId = senderUserId;
        this.objectName = objectName;
        this.content = content;
        this.pushContent = pushContent;
        this.pushData = pushData;
        this.os = os;
    }

    public String getSenderUserId() {
        return this.senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
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

    public String getPushContent() {
        return this.pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent;
    }

    public String getPushData() {
        return this.pushData;
    }

    public void setPushData(String pushData) {
        this.pushData = pushData;
    }

    public String getOs() {
        return this.os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
