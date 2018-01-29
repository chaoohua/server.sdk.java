package io.rong.models.conversation;

public class ConversationModel {
    public String type;
    public String userId;
    public String targetId;
    /**
     * 构造函数。
     *
     * @param type:会话类型。
     * @param userId:设置消息免打扰的用户 Id
     * @param targetId:目标Id
     *
     **/
    public ConversationModel(String type, String userId, String targetId) {
        this.type = type;
        this.userId = userId;
        this.targetId = targetId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
