package io.rong.methods.message._private;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.ResponseResult;
import io.rong.models.TemplateMessage;
import io.rong.models.message.PrivateMessage;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 * 发送单聊消息方法
 * docs : http://www.rongcloud.cn/docs/server.html#message_private_publish
 * @author hc
 * @date 2017/12/30
 */
public class Private {

	private static final String UTF8 = "UTF-8";
	private static final String PATH = "message/_private";
	private String appKey;
	private String appSecret;
	private RongCloud rongCloud;

	public RongCloud getRongCloud() {
		return rongCloud;
	}

	public void setRongCloud(RongCloud rongCloud) {
		this.rongCloud = rongCloud;
	}
	public Private(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}

	/**
	 * 发送单聊消息方法（一个用户向另外一个用户发送消息，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人，如：一次发送 1000 人时，示为 1000 条消息。） 
	 * 
	 * @param privateMessage 单聊消息
	 *
	 * @return ResponseResult
	 **/
	public ResponseResult publish(PrivateMessage privateMessage) throws Exception {

		String message = CommonUtil.checkFiled(privateMessage,PATH, CheckMethod.PUBLISH);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
		}

	    StringBuilder sb = new StringBuilder();
	    sb.append("&fromUserId=").append(URLEncoder.encode(privateMessage.senderUserId.toString(), UTF8));
	    
	    for (int i = 0 ; i< privateMessage.targetIds.length; i++) {
			String child  = privateMessage.targetIds[i];
			sb.append("&toUserId=").append(URLEncoder.encode(child, UTF8));
		}
		
	    sb.append("&objectName=").append(URLEncoder.encode(privateMessage.content.getType(), UTF8));
   	    sb.append("&content=").append(URLEncoder.encode(privateMessage.content.toString(), UTF8));
	    
	    if (privateMessage.pushContent != null) {
	    	sb.append("&pushContent=").append(URLEncoder.encode(privateMessage.pushContent.toString(), UTF8));
	    }
	    
	    if (privateMessage.pushData != null) {
	    	sb.append("&pushData=").append(URLEncoder.encode(privateMessage.pushData.toString(), UTF8));
	    }
	    
	    if (privateMessage.count != null) {
	    	sb.append("&count=").append(URLEncoder.encode(privateMessage.count.toString(), UTF8));
	    }
	    
	    if (privateMessage.verifyBlacklist != null) {
	    	sb.append("&verifyBlacklist=").append(URLEncoder.encode(privateMessage.verifyBlacklist.toString(), UTF8));
	    }
	    
	    if (privateMessage.isPersisted != null) {
	    	sb.append("&isPersisted=").append(URLEncoder.encode(privateMessage.isPersisted.toString(), UTF8));
	    }
	    
	    if (privateMessage.isCounted != null) {
	    	sb.append("&isCounted=").append(URLEncoder.encode(privateMessage.isCounted.toString(), UTF8));
	    }
	    
	    if (privateMessage.isIncludeSender != null) {
	    	sb.append("&isIncludeSender=").append(URLEncoder.encode(privateMessage.isIncludeSender.toString(), UTF8));
	    }
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/private/publish.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);

	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISH,HttpUtil.returnResult(conn)), ResponseResult.class);
	}
	
	/**
	 * 发送单聊模板消息方法（一个用户向多个用户发送不同消息内容，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人。） 
	 * 
	 * @param  templateMessage:单聊模版消息。
	 *
	 * @return ResponseResult
	 **/
	public ResponseResult publishTemplate(TemplateMessage templateMessage) throws Exception {

		String message = CommonUtil.checkFiled(templateMessage,PATH, CheckMethod.PUBLISH);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
		}
		
	    HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/private/publish_template.json", "application/json");
	    HttpUtil.setBodyParameter(templateMessage.toString(), conn);
	    
	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISHTEMPLATE,HttpUtil.returnResult(conn)), ResponseResult.class);
	}
	 
}