package io.rong.models.message;

import io.rong.messages.BaseMessage;

public class GroupMessage {
    public String senderUserId;
    public String targetId;
    public String[] targetIds;
    public String objectName;
    public BaseMessage content;
    public String pushContent;
    public String pushData;
    public Integer isPersisted;
    public Integer isCounted;
    public Integer isIncludeSender;
    public Integer isMentioned;
    private Integer contentAvailable;

    public GroupMessage() {
    }

    /**
     * @param  senderUserId:发送人用户 Id 。（必传）
     * @param  targetIds:接收群Id，提供多个本参数可以实现向多群发送消息，最多不超过 3 个群组。（必传）
     * @param  content:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）
     * @param  pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息. 如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。（可选）
     * @param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。（可选）
     * @param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。（可选）
     * @param  isCounted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选）
     * @param  isIncludeSender:发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。（可选）
     *
     * */
    public GroupMessage(String senderUserId, String[] targetIds, String objectName, BaseMessage content, String pushContent, String pushData,
                        Integer isPersisted, Integer isCounted, Integer isIncludeSender, Integer isMentioned, Integer contentAvailable) {
        this.senderUserId = senderUserId;
        this.targetIds = targetIds;
        this.objectName = objectName;
        this.content = content;
        this.pushContent = pushContent;
        this.pushData = pushData;
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.isIncludeSender = isIncludeSender;
        this.isMentioned = isMentioned;
        this.contentAvailable = contentAvailable;
    }


    public String getSenderUserId() {
        return this.senderUserId;
    }

    public GroupMessage setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
        return this;
    }

    public String[] getTargetIds() {
        return this.targetIds;
    }

    public GroupMessage setTargetIds(String[] targetIds) {
        this.targetIds = targetIds;
        return this;
    }

    public BaseMessage getContent() {
        return this.content;
    }

    public GroupMessage setContent(BaseMessage content) {
        this.content = content;
        return this;
    }

    public String getPushContent() {
        return this.pushContent;
    }

    public GroupMessage setPushContent(String pushContent) {
        this.pushContent = pushContent;
        return this;
    }

    public String getPushData() {
        return this.pushData;
    }

    public GroupMessage setPushData(String pushData) {
        this.pushData = pushData;
        return this;
    }

    public Integer getIsPersisted() {
        return this.isPersisted;
    }

    public GroupMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    public Integer getIsCounted() {
        return this.isCounted;
    }

    public GroupMessage setIsCounted(Integer isCounted) {
        this.isCounted = isCounted;
        return this;
    }

    public Integer getIsIncludeSender() {
        return this.isIncludeSender;
    }

    public GroupMessage setIsIncludeSender(Integer isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
        return this;
    }

    public Integer getIsMentioned() {
        return this.isMentioned;
    }

    public GroupMessage setIsMentioned(Integer isMentioned) {
        this.isMentioned = isMentioned;
        return this;
    }

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public GroupMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public GroupMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }
}
