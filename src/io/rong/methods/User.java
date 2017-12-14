package io.rong.methods;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import io.rong.models.*;
import io.rong.models.user.UserResponseResult;
import io.rong.models.user.UserConstrants;
import io.rong.models.user.UserInfo;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import io.rong.util.HostType;


/**
 *
 *
 **/
public class User {

	private static final String UTF8 = "UTF-8";
	private String appKey;
	private String appSecret;
	
	public User(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}

	/**
	 * 获取 Token 方法 
	 *
	 * docs  "http://rongcloud.cn/docs/server.html#getToken"
	 * api  "/user/getToken"
	 *
	 * @param user 用户信息
	 *
	 * @return TokenResult
	 **/
	public  TokenResult getToken(UserInfo user) throws Exception {

		if (null == user.userId) {
			return new TokenResult(UserConstrants.USER_ID_NULL,
					"","","userId 长度超限 最大长度 64字节");
		}
		if (null == user.name) {
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
		if (null == user.userId) {
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
	
	/**
	 * 检查用户在线状态 方法（每秒钟限100次）
	 * 请不要频繁循环调用此接口，而是选择合适的频率和时机调用，此接口设置了一定的频率限制。
	 *
	 * @param  userId:用户 Id，最大长度 64 字节。是用户在 App 中的唯一标识码，必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。（必传）
	 *
	 * @return CheckOnlineResult
	 **/
	public CheckOnlineResult checkOnline(String userId) throws Exception {

		if (null == userId) {
			return new CheckOnlineResult(UserConstrants.USER_ID_NULL,null,
					"userId 长度超限 最大长度 64字节");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
				"/user/checkOnline.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CheckOnlineResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CheckOnlineResult.class);
	}
	
	/**
	 * 封禁用户方法（每秒钟限 100 次） 
	 * 
	 * @param  userId:用户 Id。（必传）
	 * @param  minute:封禁时长,单位为分钟，最大值为43200分钟。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public ResponseResult block(String userId, Integer minute) throws Exception {
		if (userId == null) {
			return new UserResponseResult(UserConstrants.USER_ID_NULL,
					"userId 长度超限 最大长度 64字节");		}
		
		if (minute == null) {
			return new UserResponseResult(UserConstrants.USER_PARAM_ERROR,
					"Paramer 'minute' is required");
		}

		if(!CommonUtil.validateParams(Integer.valueOf(minute),30*24*60)){
			return new UserResponseResult(UserConstrants.USER_GAG_TIMEOUT,
					"Paramer 'minute' should be less than 30*24*60 (30days)");
		}

	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
	    sb.append("&minute=").append(URLEncoder.encode(minute.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
				"/user/block.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (UserResponseResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), UserResponseResult.class);
	}
	
	/**
	 * 解除用户封禁方法（每秒钟限 100 次） 
	 * 
	 * @param  userId:用户 Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public ResponseResult unBlock(String userId) throws Exception {
		if (userId == null) {
			return new UserResponseResult(UserConstrants.USER_ID_NULL,
					"userId 长度超限 最大长度 64字节");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
				"/user/unblock.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (UserResponseResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), UserResponseResult.class);
	}
	
	/**
	 * 获取被封禁用户方法（每秒钟限 100 次） 
	 * 
	 *
	 * @return QueryBlockUserResult
	 **/
	public QueryBlockUserResult queryBlock() throws Exception {
	    StringBuilder sb = new StringBuilder();
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
				"/user/block/query.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (QueryBlockUserResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), QueryBlockUserResult.class);
	}
	
	/**
	 * 添加用户到黑名单方法（每秒钟限 100 次） 
	 * 
	 * @param  userId:用户 Id。（必传）
	 * @param  blackUserId:被加到黑名单的用户Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public ResponseResult addBlacklist(String userId, String blackUserId) throws Exception {
		if (userId == null) {
			return new UserResponseResult(UserConstrants.USER_ID_NULL,
					"userId 长度超限 最大长度 64字节");
		}
		
		if (blackUserId == null) {
			return new UserResponseResult(UserConstrants.USER_PARAM_ERROR,
					"Paramer 'blackUserId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
	    sb.append("&blackUserId=").append(URLEncoder.encode(blackUserId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
				"/user/blacklist/add.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (UserResponseResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), UserResponseResult.class);
	}
	
	/**
	 * 获取某用户的黑名单列表方法（每秒钟限 100 次） 
	 * 
	 * @param  userId:用户 Id。（必传）
	 *
	 * @return QueryBlacklistUserResult
	 **/
	public QueryBlacklistUserResult queryBlacklist(String userId) throws Exception {
		if (userId == null) {
			return new QueryBlacklistUserResult(UserConstrants.USER_ID_NULL,null,
					"userId 长度超限 最大长度 64字节");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
				"/user/blacklist/query.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (QueryBlacklistUserResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), QueryBlacklistUserResult.class);
	}
	
	/**
	 * 从黑名单中移除用户方法（每秒钟限 100 次） 
	 * 
	 * @param  userId:用户 Id。（必传）
	 * @param  blackUserId:被移除的用户Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public ResponseResult removeBlacklist(String userId, String blackUserId) throws Exception {
		if (userId == null) {
			return new UserResponseResult(UserConstrants.USER_ID_NULL,
					"Paramer 'userId' is required\"");
		}

		if (blackUserId == null) {
			return new UserResponseResult(UserConstrants.USER_PARAM_ERROR,
					"Paramer 'blackUserId' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
	    sb.append("&blackUserId=").append(URLEncoder.encode(blackUserId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
				"/user/blacklist/remove.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (UserResponseResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), UserResponseResult.class);
	}

}