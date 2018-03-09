package io.rong.models.message;

public class MentionMessage {
    public String senderUserId;
    public String targetId;
    public String[] mentionIds;
    public String objectName;
    public GroupMentionContent content;
    public String pushContent;
    public String pushData;
    public Integer isPersisted;
    public Integer isCounted;
    public Integer isIncludeSender;
    public Integer isMentioned;
    private Integer contentAvailable;

    public MentionMessage() {
    }

    public MentionMessage(String senderUserId, String targetId, String[] mentionIds, String objectName,
                          GroupMentionContent content, String pushContent, String pushData, Integer isPersisted,
                          Integer isCounted, Integer isIncludeSender, Integer isMentioned, Integer contentAvailable) {
        this.senderUserId = senderUserId;
        this.targetId = targetId;
        this.mentionIds = mentionIds;
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

    public MentionMessage setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
        return this;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public MentionMessage setTargetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public String[] getMentionIds() {
        return this.mentionIds;
    }

    public MentionMessage setMentionIds(String[] mentionIds) {
        this.mentionIds = mentionIds;
        return this;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public MentionMessage setObjectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public GroupMentionContent getContent() {
        return this.content;
    }

    public MentionMessage setContent(GroupMentionContent content) {
        this.content = content;
        return this;
    }

    public String getPushContent() {
        return this.pushContent;
    }

    public MentionMessage setPushContent(String pushContent) {
        this.pushContent = pushContent;
        return this;
    }

    public String getPushData() {
        return this.pushData;
    }

    public MentionMessage setPushData(String pushData) {
        this.pushData = pushData;
        return this;
    }

    public Integer getIsPersisted() {
        return this.isPersisted;
    }

    public MentionMessage setIsPersisted(Integer isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }

    public Integer getIsCounted() {
        return this.isCounted;
    }

    public MentionMessage setIsCounted(Integer isCounted) {
        this.isCounted = isCounted;
        return this;
    }

    public Integer getIsIncludeSender() {
        return this.isIncludeSender;
    }

    public MentionMessage setIsIncludeSender(Integer isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
        return this;
    }

    public Integer getIsMentioned() {
        return this.isMentioned;
    }

    public MentionMessage setIsMentioned(Integer isMentioned) {
        this.isMentioned = isMentioned;
        return this;
    }

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public MentionMessage setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
        return this;
    }
}
