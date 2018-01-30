package io.rong.models.message;

import io.rong.messages.BaseMessage;

public class SystemMessage {

    private String senderUserId;
    private String[] targetIds;
    private String objectName;
    private BaseMessage content;
    private String pushContent;
    private String pushData;
    private Integer isPersisted;
    private Integer isCounted;
    private Integer contentAvailable;

    /**
     * @param  senderUserId:发送人用户 Id。（必传）
     * @param  targetIds:接收用户 Id，提供多个本参数可以实现向多人发送消息，上限为 1000 人。（必传）
     * @param  objectName
     * @param  content:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）
     * @param  pushContent:如果为自定义消息，定义显示的 Push 内容，内容中定义标识通过 values 中设置的标识位内容进行替换.如消息类型为自定义不需要 Push 通知，则对应数组传空值即可。（可选）
     * @param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。如不需要 Push 功能对应数组传空值即可。（可选）
     * @param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。（可选）
     * @param  isCounted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选）
     * @param  contentAvailable
     * */
    public SystemMessage(String senderUserId, String[] targetIds, String objectName, BaseMessage content, String pushContent, String pushData,
                         Integer isPersisted, Integer isCounted, Integer contentAvailable) {
        this.senderUserId = senderUserId;
        this.targetIds = targetIds;
        this.objectName = objectName;
        this.content = content;
        this.pushContent = pushContent;
        this.pushData = pushData;
        this.isPersisted = isPersisted;
        this.isCounted = isCounted;
        this.contentAvailable = contentAvailable;
    }

    public String getSenderUserId() {
        return this.senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String[] getTargetIds() {
        return this.targetIds;
    }

    public void setTargetIds(String[] targetIds) {
        this.targetIds = targetIds;
    }

    public BaseMessage getContent() {
        return this.content;
    }

    public void setContent(BaseMessage content) {
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

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public void setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
