package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * 单聊 消息体
 * @author RongCloud
 */
public class PrivateMessage extends Message {

    /**
     * 针对 iOS 平台，Push 时用来控制未读消息显示数，只有在 toUserId 为一个用户 Id 的时候有效。（可选）
     **/
    public String count;
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
        super(senderUserId, targetIds, objectName, content, pushContent, pushData);
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
    /**
     * 获取接受用户id
     *
     * @return String
     */
    @Override
    public String[] getTargetIds() {
        return this.targetIds;
    }
    /**
     * 设置接受用户id
     */
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
    public Integer getIsPersisted() {
        return this.isPersisted;
    }

    public PrivateMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }
    public Integer getIsCounted() {
        return this.isCounted;
    }

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

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public PrivateMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }





}
