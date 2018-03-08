package io.rong.models.message;

import io.rong.messages.BaseMessage;

public class MentionMessage {
    public String senderUserId;
    public String targetId;
    public String[] targetIds;
    public String objectName;
    public GroupContent content;
    public String pushContent;
    public String pushData;
    public Integer isPersisted;
    public Integer isCounted;
    public Integer isIncludeSender;
    public Integer isMentioned;
    private Integer contentAvailable;

    public MentionMessage(String senderUserId, String targetId, String[] targetIds, String objectName,
                          GroupContent content, String pushContent, String pushData, Integer isPersisted,
                          Integer isCounted, Integer isIncludeSender, Integer isMentioned, Integer contentAvailable) {
        this.senderUserId = senderUserId;
        this.targetId = targetId;
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

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String[] getTargetIds() {
        return this.targetIds;
    }

    public void setTargetIds(String[] targetIds) {
        this.targetIds = targetIds;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public GroupContent getContent() {
        return this.content;
    }

    public void setContent(GroupContent content) {
        this.content = content;
    }

    public String getPushContent() {
        return this.pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent;
    }

    public String getPushData() {
        return this.pushData;
    }

    public void setPushData(String pushData) {
        this.pushData = pushData;
    }

    public Integer getIsPersisted() {
        return this.isPersisted;
    }

    public void setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
    }

    public Integer getIsCounted() {
        return this.isCounted;
    }

    public void setIsCounted(Integer isCounted) {
        this.isCounted = isCounted;
    }

    public Integer getIsIncludeSender() {
        return this.isIncludeSender;
    }

    public void setIsIncludeSender(Integer isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
    }

    public Integer getIsMentioned() {
        return this.isMentioned;
    }

    public void setIsMentioned(Integer isMentioned) {
        this.isMentioned = isMentioned;
    }

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public void setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
    }
}
