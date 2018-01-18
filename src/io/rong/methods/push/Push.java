package io.rong.methods.push;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.models.CheckMethod;
import io.rong.models.CodeSuccessResult;
import io.rong.models.PushMessage;
import io.rong.models.UserTag;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;

public class Push {
	private static final String UTF8 = "UTF-8";
	private static final String PATH = "push";
	private String appKey;
	private String appSecret;
	private RongCloud rongCloud;

	public RongCloud getRongCloud() {
		return rongCloud;
	}

	public void setRongCloud(RongCloud rongCloud) {
		this.rongCloud = rongCloud;
	}
	public Push(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}
	
	
	/**
	 * 添加 Push 标签方法 
	 * 
	 * @param  userTag:用户标签。
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult setUserTag(UserTag userTag) throws Exception {
		if (userTag == null) {
			throw new ParamException("Paramer 'userTag' is required");
		}
		
	    HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/user/tag/set.json", "application/json");
	    HttpUtil.setBodyParameter(userTag.toString(), conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.SET_TAG,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
	}
	
	/**
	 * 广播消息和推送方法（fromuserid 和 message为null即为不落地的push）
	 * 
	 * @param  pushMessage:json数据
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult send(PushMessage pushMessage) throws Exception {
		String[] fields = {"platform","audience","is_to_all","notification","alert"};

		if (pushMessage == null) {
			throw new ParamException("Paramer 'pushMessage' is required");
		}
		
	    HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/push.json", "application/json");
	    HttpUtil.setBodyParameter(pushMessage.toString(), conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.SEND,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
	}

	 
}