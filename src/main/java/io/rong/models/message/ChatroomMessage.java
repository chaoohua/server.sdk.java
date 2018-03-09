package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * @author RongCloud
 */
public class ChatroomMessage extends Message{

    public ChatroomMessage() {

    }

    public ChatroomMessage(String senderUserId, String[] targetIds, String objectName, BaseMessage content,
                           String pushContent, String pushData, Integer isPersisted, Integer isCounted) {
        super(senderUserId, targetIds, objectName, content, pushContent, pushData, isPersisted, isCounted);
    }

    @Override
    public String getSenderUserId() {
        return this.senderUserId;
    }
    @Override
    public ChatroomMessage setSenderUserId(String senderUserId) {
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
    public ChatroomMessage setTargetIds(String[] targetIds) {
        this.targetIds = targetIds;
        return this;
    }
    @Override
    public String getObjectName() {
        return this.objectName;
    }
    @Override
    public ChatroomMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }
    @Override
    public BaseMessage getContent() {
        return  this.content;
    }

    @Override
    public ChatroomMessage setContent(BaseMessage content) {
        this.content = content;
        return this;
    }
}
