package io.rong.models;

import io.rong.messages.ErrorCodeMessage;
import io.rong.models.group.GroupConstrants;
import io.rong.util.CodeUtil;
import io.rong.util.GsonUtil;
import io.rong.util.RcloudServiceConstrants;

/**
 *  http 成功返回结果
 */
public class CodeSuccessResult {
	// 返回码，200 为正常。
	Integer code;
	// 错误信息。
	String errorMessage;

	public Integer flag;
	
	public CodeSuccessResult(Integer code, String errorMessage) {
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
	
	@Override
	public String toString() {
		if(this.flag == CodeUtil.ServiceType.group.getResultCode()){
			switch (this.getCode()) {
				case 1008:
					this.setCode(GroupConstrants.GROUP_SYNC_OVERFLOW);
					break;
				case RcloudServiceConstrants.CHATROOM:
					System.out.println("");
				case RcloudServiceConstrants.MESSAGE:

				case RcloudServiceConstrants.PUSH:

				case RcloudServiceConstrants.SENSITIVEWORD:

				case RcloudServiceConstrants.SMS:

				case RcloudServiceConstrants.USER:

				case RcloudServiceConstrants.WORDFILTER:

				default:
					break;
			}
		}
		if(this.code != 200 && ErrorCodeMessage.errorCodeMaps.containsKey(this.code)){
			this.setErrorMessage(ErrorCodeMessage.errorCodeMaps.get(code));
		}
		return GsonUtil.toJson(this, CodeSuccessResult.class);
	}
}
