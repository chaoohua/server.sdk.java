package io.rong.models.message;

import io.rong.messages.BaseMessage;

public class PrivateMessage {
  //发送人用户 Id。（必传）
  private String fromUserId;
  //toUserId:接收用户 Id，可以实现向多人发送消息，每次上限为 1000 人。（必传）
  private String toUserId;
  //发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）。
  private BaseMessage message;
  //定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息。如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。（可选）
  private String pushContent;
  //针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。（可选）
  private String pushData;
  //针对 iOS 平台，Push 时用来控制未读消息显示数，只有在 toUserId 为一个用户 Id 的时候有效。（可选）
  private String count;
  //是否过滤发送人黑名单列表，0 表示为不过滤、 1 表示为过滤，默认为 0 不过滤。（可选）
  private String verifyBlacklist;
  //当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。（可选）
  private String isPersisted;
  //当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选）
  private String isCounted;
  //发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。（可选）
  private String isIncludeSender;

    public String getToUserId() {
        return this.toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public BaseMessage getMessage() {
        return this.message;
    }

    public void setMessage(BaseMessage message) {
        this.message = message;
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

    public String getCount() {
        return this.count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getVerifyBlacklist() {
        return this.verifyBlacklist;
    }

    public void setVerifyBlacklist(String verifyBlacklist) {
        this.verifyBlacklist = verifyBlacklist;
    }

    public String getIsPersisted() {
        return this.isPersisted;
    }

    public void setIsPersisted(String isPersisted) {
        this.isPersisted = isPersisted;
    }

    public String getIsCounted() {
        return this.isCounted;
    }

    public void setIsCounted(String isCounted) {
        this.isCounted = isCounted;
    }

    public String getIsIncludeSender() {
        return this.isIncludeSender;
    }

    public void setIsIncludeSender(String isIncludeSender) {
        this.isIncludeSender = isIncludeSender;
    }
}
