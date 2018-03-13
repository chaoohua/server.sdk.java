package io.rong.models.message;

import io.rong.messages.BaseMessage;

/**
 * 聊天室消息体
 * @author RongCloud
 */
public class ChatroomMessage extends MessageModel {

    public ChatroomMessage() {

    }

    public ChatroomMessage(String senderUserId, String[] targetId, String objectName, BaseMessage content) {
        super(senderUserId, targetId, objectName, content, null, null);
    }

   @Override
    public ChatroomMessage setSenderUserId(String senderUserId) {
        setSenderUserId(senderUserId);
        return this;
    }
    /**
     * 获取接受聊天室Id
     *
     * @return String
     */
    @Override
    public String[] getTargetId() {
        return getTargetId();
    }
    /**
     * 设置接受聊天室Id
     *
     * @return String
     */
    @Override
    public ChatroomMessage setTargetId(String[] targetId) {
        setTargetId(targetId);
        return this;
    }
    @Override
    public ChatroomMessage setObjectName(String objectName) {
        setObjectName(objectName);
        return this;
    }

    @Override
    public ChatroomMessage setContent(BaseMessage content) {
        setContent(content);
        return this;
    }
}
