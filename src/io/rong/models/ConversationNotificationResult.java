package io.rong.models;

import io.rong.util.GsonUtil;

public class ConversationNotificationResult {
    // 返回码，200 为正常.如果您正在使用开发环境的 AppKey，您的应用只能注册 100 名用户，达到上限后，将返回错误码 2007.如果您需要更多的测试账户数量，您需要在应用配置中申请“增加测试人数”。
    Integer code;
    //消息免打扰设置状态，0 表示为关闭，1 表示为开启。
    Integer isMuted;

    // 错误信息。
    String errorMessage;

    public ConversationNotificationResult(Integer code, Integer isMuted, String errorMessage) {
        this.code = code;
        this.isMuted = isMuted;
        this.errorMessage = errorMessage;
    }

    /**
     * 设置code
     *
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取code
     *
     * @return Integer
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 获取Muted
     *
     * @return String
     */
    public Integer getIsMuted() {
        return this.isMuted;
    }
    /**
     * 设置Muted状态
     *
     */
    public void setIsMuted(Integer isMuted) {
        this.isMuted = isMuted;
    }

    /**
     * 设置errorMessage
     *
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 获取errorMessage
     *
     * @return String
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, ConversationNotificationResult.class);
    }
}
