package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * @author RongCloud
 */
public class PrivateMessage extends Message{
    /**
     * 针对 iOS 平台，Push 时用来控制未读消息显示数，只有在 toUserId 为一个用户 Id 的时候有效。（可选）
     **/
    public String count;
    /**
     *是否过滤发送人黑名单列表，0 表示为不过滤、 1 表示为过滤，默认为 0 不过滤。（可选
     */
    public Integer verifyBlacklist;
    /**
     * 发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。（可选）
     */
    public Integer isIncludeSender;

    public Integer contentAvailable;

    public PrivateMessage() {
    }

    public PrivateMessage(String senderUserId, String[] targetIds, String objectName, BaseMessage content,
                          String pushContent, String pushData, Integer isPersisted, Integer isCounted, String count,
                          Integer verifyBlacklist, Integer isIncludeSender, Integer contentAvailable) {
        super(senderUserId, targetIds, objectName, content, pushContent, pushData, isPersisted, isCounted);
        this.count = count;
        this.verifyBlacklist = verifyBlacklist;
        this.isIncludeSender = isIncludeSender;
        this.contentAvailable = contentAvailable;
    }

    @Override
    public String getSenderUserId() {
        return this.senderUserId;
    }

    @Override
    public PrivateMessage setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
        return this;
    }
    @Override
    public String[] getTargetIds() {
        return this.targetIds;
    }
    @Override
    public PrivateMessage setTargetIds(String[] targetIds) {
        this.targetIds = targetIds;
        return (PrivateMessage)this;
    }

    @Override
    public BaseMessage getContent() {
        return this.content;
    }

    @Override
    public PrivateMessage setContent(BaseMessage content) {
        this.content = content;
        return this;
    }
    @Override
    public String getPushContent() {
        return this.pushContent;
    }

    @Override
    public PrivateMessage setPushContent(String pushContent) {
        this.pushContent = pushContent;
        return this;
    }
    @Override
    public String getPushData() {
        return this.pushData;
    }
    @Override
    public PrivateMessage setPushData(String pushData) {
        this.pushData = pushData;
        return this;
    }

    public String getCount() {
        return this.count;
    }

    public PrivateMessage setCount(String count) {
        this.count = count;
        return this;
    }

    public Integer getVerifyBlacklist() {
        return this.verifyBlacklist;
    }

    public PrivateMessage setVerifyBlacklist(Integer verifyBlacklist) {
        this.verifyBlacklist = verifyBlacklist;
        return this;
    }
    @Override
    public Integer getIsPersisted() {
        return this.isPersisted;
    }
    @Override
    public PrivateMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }
    @Override
    public Integer getIsCounted() {
        return this.isCounted;
    }
    @Override
    public PrivateMessage setIsCounted(Integer isCounted) {
        this.isCounted = isCounted;
        return this;
    }

    public Integer getIsIncludeSender() {
        return this.isIncludeSender;
    }

    public PrivateMessage setIsIncludeSender(Integer isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
        return this;
    }
    @Override
    public String getObjectName() {
        return this.objectName;
    }
    @Override
    public PrivateMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }
    @Override
    public Integer getContentAvailable() {
        return this.contentAvailable;
    }
    @Override
    public PrivateMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }





}
