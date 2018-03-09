package io.rong.models.message;

import io.rong.messages.BaseMessage;

public class DiscussionMessage extends Message{

    /**
     * 当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，
     * 0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选）
     */
    private Integer isIncludeSender;

    public DiscussionMessage() {
    }

    public DiscussionMessage(String senderUserId, String[] targetIds, String objectName, BaseMessage content, String pushContent, String pushData,
                             Integer isPersisted, Integer isCounted, Integer isIncludeSender) {
        super(senderUserId, targetIds, objectName, content, pushContent, pushData, isPersisted, isCounted);
        this.isIncludeSender = isIncludeSender;
    }

    @Override
    public String getSenderUserId() {
        return this.senderUserId;
    }
    @Override
    public DiscussionMessage setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
        return this;
    }
    /**
     * 获取接受聊天室Id
     *
     * @return String
     */
    @Override
    public String[] getTargetIds() {
        return this.targetIds;
    }
    /**
     * 设置接受聊天室Id
     *
     * @return String
     */
    @Override
    public DiscussionMessage setTargetIds(String[] targetIds) {
        this.targetIds = targetIds;
        return this;
    }

    @Override
    public BaseMessage getContent() {
        return this.content;
    }
    @Override
    public DiscussionMessage setContent(BaseMessage content) {
        this.content = content;
        return this;
    }
    @Override
    public String getPushContent() {
        return this.pushContent;
    }
    @Override
    public DiscussionMessage setPushContent(String pushContent) {
        this.pushContent = pushContent;
        return this;
    }
    @Override
    public String getPushData() {
        return this.pushData;
    }
    @Override
    public DiscussionMessage setPushData(String pushData) {
        this.pushData = pushData;
        return this;
    }
    @Override
    public Integer getIsPersisted() {
        return this.isPersisted;
    }
    @Override
    public DiscussionMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }
    @Override
    public Integer getIsCounted() {
        return this.isCounted;
    }
    @Override
    public DiscussionMessage setIsCounted(Integer isCounted) {
        this.isCounted = isCounted;
        return this;
    }
    @Override
    public String getObjectName() {
        return this.objectName;
    }
    @Override
    public DiscussionMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }
    @Override
    public Integer getContentAvailable() {
        return this.contentAvailable;
    }
    @Override
    public DiscussionMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }

    public Integer getIsIncludeSender() {
        return this.isIncludeSender;
    }

    public DiscussionMessage setIsIncludeSender(Integer isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
        return this;
    }
}
