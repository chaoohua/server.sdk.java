package io.rong.models;
/**
 *  基础 http 成功返回结果
 */
public abstract class Result {
    /**
     * 返回码，200 为正常。
     *
     */
    public Integer code;
    /**
     * 错误信息。
     *
     */
    public String errorMessage;

    public Result(Integer code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }
    public Result() {

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

    @Override
    public abstract String toString();
}
