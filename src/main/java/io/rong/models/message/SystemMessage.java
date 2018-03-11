package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * 系统消息体
 * @author hc
 */
public class SystemMessage extends Message {

    /**
     * 当前版本有新的自定义消息，而老版本没有该自定义消息时，
     * 老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。（可选）
     *
     * */
    private Integer isPersisted;
    /**
     * 当前版本有新的自定义消息，而老版本没有该自定义消息时，
     * 老版本客户端收到消息后是否进行未读消息计数，0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选）
     * */
    private Integer isCounted;
    /**
     * 针对 iOS 平台，对 SDK 处于后台暂停状态时为静默推送，是 iOS7 之后推出的一种推送方式。
     * 允许应用在收到通知后在后台运行一段代码，且能够马上执行。1 表示为开启，0 表示为关闭，默认为 0（可选）
     * */
    private Integer contentAvailable;

    public SystemMessage() {
    }

    public SystemMessage(String senderUserId, String[] targetIds, String objectName, BaseMessage content,
                         String pushContent, String pushData, Integer isPersisted, Integer isCounted, Integer contentAvailable) {
        super(senderUserId, targetIds, objectName, content, pushContent, pushData);
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.contentAvailable = contentAvailable;
    }

    @Override
    public String getSenderUserId() {
        return this.senderUserId;
    }
    @Override
    public SystemMessage setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
        return this;
    }
    /**
     * 获取接受聊天室Id
     * @return String
     */
    @Override
    public String[] getTargetIds() {
        return this.targetIds;
    }
    /**
     * 接收用户Id，提供多个本参数可以实现向多用户发送系统消息，上限为 100 人
     */
    @Override
    public SystemMessage setTargetIds(String[] targetIds) {
        this.targetIds = targetIds;
        return this;
    }
    @Override
    public BaseMessage getContent() {
        return this.content;
    }
    @Override
    public SystemMessage setContent(BaseMessage content) {
        this.content = content;
        return this;

    }
    @Override
    public String getPushContent() {
        return this.pushContent;
    }
    @Override
    public SystemMessage setPushContent(String pushContent) {
        this.pushContent = pushContent;
        return this;
    }
    @Override
    public String getPushData() {
        return this.pushData;
    }
    @Override
    public SystemMessage setPushData(String pushData) {
        this.pushData = pushData;
        return this;
    }

    public Integer getIsPersisted() {
        return this.isPersisted;
    }

    public SystemMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    public Integer getIsCounted() {
        return this.isCounted;
    }

    public SystemMessage setIsCounted(Integer isCounted) {
        this.isCounted = isCounted;
        return this;
    }

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public SystemMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }
    @Override
    public String getObjectName() {
        return this.objectName;
    }
    @Override
    public SystemMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }
}
