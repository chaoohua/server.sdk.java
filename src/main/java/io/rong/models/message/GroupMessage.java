package io.rong.models.message;

import io.rong.messages.BaseMessage;

public class GroupMessage extends Message{

    /**
     * 发送者自己是否接收此条消息, 0: 不接收, 1: 接收, 默认: 0
     **/
    public Integer isIncludeSender;
    /**
     * ios静默推送 0关闭 1开启
     **/
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
        this.contentAvailable = contentAvailable;
    }

    @Override
    public String getSenderUserId() {
        return this.senderUserId;
    }
    @Override
    public GroupMessage setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
        return this;
    }

    /**
     * 获取接收群组Id
     *
     * @return String
     */
    @Override
    public String[] getTargetIds() {
        return this.targetIds;
    }
    /**
     * 设置接收群组Id
     *
     * @return String
     */
    @Override
    public GroupMessage setTargetIds(String[] targetIds) {
        this.targetIds = targetIds;
        return this;
    }
    @Override
    public BaseMessage getContent() {
        return this.content;
    }
    @Override
    public GroupMessage setContent(BaseMessage content) {
        this.content = content;
        return this;
    }
    @Override
    public String getPushContent() {
        return this.pushContent;
    }
    @Override
    public GroupMessage setPushContent(String pushContent) {
        this.pushContent = pushContent;
        return this;
    }
    @Override
    public String getPushData() {
        return this.pushData;
    }
    @Override
    public GroupMessage setPushData(String pushData) {
        this.pushData = pushData;
        return this;
    }
    @Override
    public Integer getIsPersisted() {
        return this.isPersisted;
    }
    @Override
    public GroupMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }
    @Override
    public Integer getIsCounted() {
        return this.isCounted;
    }
    @Override
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
    @Override
    public Integer getContentAvailable() {
        return this.contentAvailable;
    }
    @Override
    public GroupMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }
    @Override
    public String getObjectName() {
        return this.objectName;
    }
    @Override
    public GroupMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }
}
