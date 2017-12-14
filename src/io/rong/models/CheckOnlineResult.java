package io.rong.models;

import io.rong.messages.ErrorCodeMessage;
import io.rong.models.user.UserConstrants;
import io.rong.util.GsonUtil;
import io.rong.util.RcloudServiceConstrants;

/**
 * checkOnlineUser返回结果
 */
public class CheckOnlineResult {
	// 返回码，200 为正常。
	Integer code;
	// 在线状态，1为在线，0为不在线。
	String status;
	// 错误信息。
	String errorMessage;
	
	public CheckOnlineResult(Integer code, String status, String errorMessage) {
		this.code = code;
		this.status = status;
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
	 * 设置status
	 *
	 */	
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 获取status
	 *
	 * @return String
	 */
	public String getStatus() {
		return status;
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
		switch (this.getCode()) {

			case RcloudServiceConstrants.RCLOUD_PARAM_ERROR:
				this.setCode(UserConstrants.USER_PARAM_ERROR);
				break;
			case RcloudServiceConstrants.RCLOUD_METHOD_LIMITED:
				this.setCode(UserConstrants.USER_NAME_LEN_OUT);
				break;
			case RcloudServiceConstrants.RCLOUD_PARAM_LEN_OUT:
				this.setCode(UserConstrants.USER_PARAM_ERROR);
				break;
			case RcloudServiceConstrants.SENSITIVEWORD:
				break;
			case RcloudServiceConstrants.SMS:
				break;
			case RcloudServiceConstrants.USER:
				break;
			case RcloudServiceConstrants.WORDFILTER:
				break;
			default:
				break;
		}
		if(this.getCode() != 200 && ErrorCodeMessage.errorCodeMaps.containsKey(this.getCode())){
			this.setErrorMessage(ErrorCodeMessage.errorCodeMaps.get(this.getCode()));
		}
		return GsonUtil.toJson(this, CheckOnlineResult.class);
	}
}
