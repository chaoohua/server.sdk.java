package io.rong.models;

import io.rong.util.GsonUtil;

public class BaseModel {
    // 返回码，200 为正常。
    Integer code;
    // 错误信息。
    String errorMessage;
    //建议信息
    String suggestMessage;

    public BaseModel(Integer code, String errorMessage) {
        this.code = code;
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

    public String getSuggestMessage() {
        return this.suggestMessage;
    }

    public void setSuggestMessage(String suggestMessage) {
        this.suggestMessage = suggestMessage;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, BaseModel.class);
    }
}
