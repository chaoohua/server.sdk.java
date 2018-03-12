package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * 讨论组消息体
 * @author hc
 */
public class DiscussionMessage extends Message {

    /**
     * 针对 iOS 平台，Push 时用来控制未读消息显示数，只有在 toUserId 为一个用户 Id 的时候有效。（可选）
     */
    public Integer isPersisted;
    /**
     * 当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，
     * 0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选）
     */
    public Integer isCounted;

    /**
     * 当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，
     * 0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选）
     */
    private Integer isIncludeSender;

    /**
     * ios静默推送 0关闭 1开启
     **/
    public Integer contentAvailable;

    public DiscussionMessage() {
    }

    public DiscussionMessage(String senderUserId, String[] targetId, String objectName, BaseMessage content, String pushContent, String pushData,
                             Integer isPersisted, Integer isCounted, Integer isIncludeSender, Integer contentAvailable) {
        super(senderUserId, targetId, objectName, content, pushContent, pushData);
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.isIncludeSender = isIncludeSender;
        this.contentAvailable = contentAvailable;
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
    public String[] getTargetId() {
        return this.targetId;
    }
    /**
     * 设置接受聊天室Id
     *
     * @return String
     */
    @Override
    public DiscussionMessage setTargetId(String[] targetId) {
        this.targetId = targetId;
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

    public Integer getIsPersisted() {
        return this.isPersisted;
    }

    public DiscussionMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    public Integer getIsCounted() {
        return this.isCounted;
    }

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

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

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
