package io.rong.methods.user;

import io.rong.methods.user.blacklist.BlackList;
import io.rong.methods.user.block.Block;
import io.rong.methods.user.onlinestatus.OnlineStatus;
import io.rong.models.*;
import io.rong.models.user.UserConstrants;
import io.rong.models.user.UserInfo;
import io.rong.models.user.UserResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;


/**
 *
 *
 **/
public class User {

	private static final String UTF8 = "UTF-8";
	private String appKey;
	private String appSecret;
	public Block block;
	public BlackList blackList;
	public OnlineStatus onlineStatus;
	public User(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.block = new Block(appKey,appSecret);
		this.blackList = new BlackList(appKey,appSecret);
		this.onlineStatus = new OnlineStatus(appKey,appSecret);
	}
	/**
	 * 获取 Token 方法 
	 * url  "/user/getToken"
	 * @see  "http://rongcloud.cn/docs/server.html#getToken"
	 *
	 * @param user 用户信息
	 *
	 * @return TokenResult
	 **/
	public  TokenResult getToken(UserInfo user) throws Exception {
		if (user.userId == null) {
			return new TokenResult(UserConstrants.USER_ID_NULL,
					"","","userId 长度超限 最大长度 64字节");
		}
		if (user.name == null) {
			return new TokenResult(UserConstrants.USER_ID_NULL,
					"","","userId 长度超限 最大长度 64字节");
		}
		if (user.portraitUri == null) {
			return new TokenResult(UserConstrants.USER_ID_NULL,
					"","","userId 长度超限 最大长度 64字节");
		}
		if(CommonUtil.validateParams(user.userId,64)){
			return new TokenResult(UserConstrants.USER_ID_LEN_OUT,
					"","","userId 长度超限 最大长度 64字节");
		}
		if(CommonUtil.validateParams(user.name,128)){
			return new TokenResult(UserConstrants.USER_NAME_LEN_OUT,
					"","","用户名长度超限, 最大长度 128 字节");
		}
		if(CommonUtil.validateParams(user.portraitUri,1024)){
			return new TokenResult(UserConstrants.USER_PORTRAIT_OUT,
					"","","头像长度超限， 最大长度 1024 字节");
		}
	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(user.userId.toString(), UTF8));
	    sb.append("&name=").append(URLEncoder.encode(user.name.toString(), UTF8));
	    sb.append("&portraitUri=").append(URLEncoder.encode(user.portraitUri.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/user/getToken.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (TokenResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), TokenResult.class);
	}
	
	/**
	 * 刷新用户信息方法 
	 *
	 * @param user 用户信息
	 *
	 * @return CodeSuccessResult
	 **/
	public ResponseResult refresh(UserInfo user) throws Exception {
		if (user.userId == null) {
			return new UserResponseResult(UserConstrants.USER_ID_NULL,
					"userId 长度超限 最大长度 64字节");
		}
		if(!CommonUtil.validateParams(user.userId,64)){
			return new UserResponseResult(UserConstrants.USER_ID_LEN_OUT,
					"userId 长度超限 最大长度 64字节");
		}
		if(!CommonUtil.validateParams(user.name,128)){
			return new UserResponseResult(UserConstrants.USER_NAME_LEN_OUT,
					"用户名长度超限, 最大长度 128 字节");
		}
		if(!CommonUtil.validateParams(user.portraitUri,1024)){
			return new UserResponseResult(UserConstrants.USER_PORTRAIT_OUT,
					"头像长度超限， 最大长度 1024 字节");
		}

		StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(user.userId.toString(), UTF8));
	    
	    if (user.name != null) {
	    	sb.append("&name=").append(URLEncoder.encode(user.name.toString(), UTF8));
	    }
	    
	    if (user.portraitUri != null) {
	    	sb.append("&portraitUri=").append(URLEncoder.encode(user.portraitUri.toString(), UTF8));
	    }
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
				"/user/refresh.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (UserResponseResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), UserResponseResult.class);
	}


}