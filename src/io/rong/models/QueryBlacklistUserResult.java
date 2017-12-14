package io.rong.models;

import io.rong.messages.ErrorCodeMessage;
import io.rong.models.user.UserConstrants;
import io.rong.util.GsonUtil;
import io.rong.util.RcloudServiceConstrants;

/**
 * queryBlacklistUser返回结果
 */
public class QueryBlacklistUserResult {
	// 返回码，200 为正常。
	Integer code;
	// 黑名单用户列表。
	String[] users;
	// 错误信息。
	String errorMessage;
	
	public QueryBlacklistUserResult(Integer code, String[] users, String errorMessage) {
		this.code = code;
		this.users = users;
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
	 * 设置users
	 *
	 */	
	public void setUsers(String[] users) {
		this.users = users;
	}
	
	/**
	 * 获取users
	 *
	 * @return String[]
	 */
	public String[] getUsers() {
		return users;
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
		return GsonUtil.toJson(this, QueryBlacklistUserResult.class);
	}
}
