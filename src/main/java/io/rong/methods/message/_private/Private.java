package io.rong.methods.message._private;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.Result;
import io.rong.models.message.Message;
import io.rong.models.message.RecallMessage;
import io.rong.models.message.TemplateMessage;
import io.rong.models.response.ResponseResult;
import io.rong.models.Templates;
import io.rong.models.message.PrivateMessage;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	 * @param message 单聊消息
	 *
	 * @return ResponseResult
	 **/
	public ResponseResult send(Message message) throws Exception {
		if(null == message){

		}
		PrivateMessage privateMessage = (PrivateMessage)message;
		String msgErr = CommonUtil.checkFiled(privateMessage,PATH, CheckMethod.SEND);
		if(null != msgErr){
			return (ResponseResult)GsonUtil.fromJson(msgErr,ResponseResult.class);
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
	    
	    if (privateMessage.getIsPersisted() != null) {
	    	sb.append("&isPersisted=").append(URLEncoder.encode(privateMessage.isPersisted.toString(), UTF8));
	    }
	    
	    if (privateMessage.getIsCounted() != null) {
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
	 * @param  template:单聊模版消息。
	 *
	 * @return ResponseResult
	 **/
	public ResponseResult sendTemplate(TemplateMessage template) throws Exception {

		String message = CommonUtil.checkFiled(template,PATH, CheckMethod.SENDTEMPLATE);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
		}

		Templates templateMessage = new Templates();

		ArrayList<String> toUserIds = new ArrayList<>();
		List<Map<String,String>> values = new ArrayList<>();
		List<String> push = new ArrayList<>();

		for(Map.Entry<String, TemplateMessage.Data> vo : template.getContent().entrySet()){
			toUserIds.add(vo.getKey());
			values.add(vo.getValue().getData());
			push.add(vo.getValue().getPush());
		}
		templateMessage.setFromUserId(template.getSenderId());
		templateMessage.setToUserId(toUserIds.toArray(new String[toUserIds.size()]));
		templateMessage.setObjectName(template.getObjectName());
		templateMessage.setContent(template.getTemplate().toString());
		templateMessage.setValues(values);
		templateMessage.setPushContent(push.toArray(new String[push.size()]));
		templateMessage.setPushData(template.getPushData());
		templateMessage.setVerifyBlacklist(template.getVerifyBlacklist());
		templateMessage.setContentAvailable(template.getContentAvailable());


		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/private/publish_template.json", "application/json");
	    HttpUtil.setBodyParameter(templateMessage.toString(), conn);
	    
	    return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISHTEMPLATE,HttpUtil.returnResult(conn)), ResponseResult.class);
	}

	/**
	 * 设置用户某会话接收新消息时是否进行消息提醒。
	 *
	 *@param recallMessage
	 *
	 * @return ResponseResult
	 **/
	public Result recall(RecallMessage recallMessage) throws Exception {
		//需要校验的字段
		String message = CommonUtil.checkFiled(recallMessage,PATH,CheckMethod.RECALL);
		if(null != message){
			return (Result)GsonUtil.fromJson(message,Result.class);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("&conversationType=").append(URLEncoder.encode("1", UTF8));
		sb.append("&fromUserId=").append(URLEncoder.encode(recallMessage.senderUserId.toString(), UTF8));
		sb.append("&targetId=").append(URLEncoder.encode(recallMessage.targetId.toString(), UTF8));
		sb.append("&messageUID=").append(URLEncoder.encode(recallMessage.uId.toString(), UTF8));
		sb.append("&sentTime=").append(URLEncoder.encode(recallMessage.sentTime.toString(), UTF8));
		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/recall.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);

		return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.RECALL,HttpUtil.returnResult(conn)), ResponseResult.class);
	}
	 
}