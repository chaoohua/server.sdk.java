package io.rong.models.message;

import io.rong.messages.BaseMessage;
import io.rong.util.ParamNotNull;

public class RecallMessage {

    public String senderUserId;
    public String conversationType;
    public String targetId;
    public BaseMessage content;
    public String messageUid;
    public String sentTime;
    /**
     * @param senderUserId	String	消息发送人用户 Id。（必传）
     * @param conversationType	Int	会话类型，二人会话是 1 、讨论组会话是 2 、群组会话是 3 。（必传）
     * @param targetId	String	目标 Id，根据不同的 ConversationType，可能是用户 Id、讨论组 Id、群组 Id。（必传）
     * @param messageUid	String	消息唯一标识，可通过服务端实时消息路由获取，对应名称为 msgUID。（必传）
     * @param sentTime
     *
     * */
    public RecallMessage(String senderUserId, String conversationType, String targetId,
                         BaseMessage content, String messageUid, String sentTime) {
        this.senderUserId = senderUserId;
        this.conversationType = conversationType;
        this.targetId = targetId;
        this.content = content;
        this.messageUid = messageUid;
        this.sentTime = sentTime;
    }

    public String getSenderUserId() {
        return this.senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getConversationType() {
        return this.conversationType;
    }

    public void setConversationType(String conversationType) {
        this.conversationType = conversationType;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public BaseMessage getContent() {
        return this.content;
    }

    public void setContent(BaseMessage content) {
        this.content = content;
    }

    public String getMessageUid() {
        return this.messageUid;
    }

    public void setMessageUid(String messageUid) {
        this.messageUid = messageUid;
    }

    public String getSentTime() {
        return this.sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }
}
