package io.rong.models.message;

import io.rong.messages.BaseMessage;

public class DiscussionMessage {

    private String senderUserId;

    /**
     *
     * toUserId:接收用户 Id，可以实现向多人发送消息，每次上限为 1000 人。（必传）
     **/
    private String targetIds;
    /**
     *
     *content:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）。
     *
     **/
    private String objectName;
    private BaseMessage content;
    //定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息。如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。（可选）
    private String pushContent;
    //针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。（可选）
    private String pushData;
    //针对 iOS 平台，Push 时用来控制未读消息显示数，只有在 toUserId 为一个用户 Id 的时候有效。（可选）
    private String isPersisted;
    //当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选）
    private String isCounted;
    //发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。（可选）
    private String isIncludeSender;

    public DiscussionMessage(String senderUserId, String targetIds, String objectName, BaseMessage content, String pushContent, String pushData,
                             String isPersisted, String isCounted, String isIncludeSender) {
        this.senderUserId = senderUserId;
        this.targetIds = targetIds;
        this.objectName = objectName;
        this.content = content;
        this.pushContent = pushContent;
        this.pushData = pushData;
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.isIncludeSender = isIncludeSender;
    }

    public String getSenderUserId() {
        return this.senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getTargetIds() {
        return this.targetIds;
    }

    public void setTargetIds(String targetIds) {
        this.targetIds = targetIds;
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

    public String getIsPersisted() {
        return this.isPersisted;
    }

    public void setIsPersisted(String isPersisted) {
        this.isPersisted = isPersisted;
    }

    public String getIsCounted() {
        return this.isCounted;
    }

    public void setIsCounted(String isCounted) {
        this.isCounted = isCounted;
    }

    public String getIsIncludeSender() {
        return this.isIncludeSender;
    }

    public void setIsIncludeSender(String isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
