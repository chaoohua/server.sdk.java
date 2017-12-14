package io.rong.models;
/**
 *  基础 http 成功返回结果
 */
public abstract class ResponseResult {
    //// 返回码，200 为正常。
    Integer code;
    // 错误信息。
    String errorMessage;

    public ResponseResult(Integer code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }
    public ResponseResult() {

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

    public abstract String toString();
}
