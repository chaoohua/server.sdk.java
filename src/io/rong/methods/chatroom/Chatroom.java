package io.rong.methods.chatroom;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.methods.chatroom.gag.Gag;
import io.rong.methods.chatroom.keepalive.Keepalive;
import io.rong.methods.chatroom.priority.Priority;
import io.rong.methods.chatroom.whitelist.WhiteList;
import io.rong.methods.chatroom.block.Block;
import io.rong.models.ChatroomQueryResult;
import io.rong.models.ChatroomUserQueryResult;
import io.rong.models.CodeSuccessResult;
import io.rong.models.CommonConstrants;
import io.rong.models.chatroom.*;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Chatroom {

	private static final String UTF8 = "UTF-8";
	private static final String PATH = "chatroom";
	public static final String checkObject = "chatroom";
	private static String checkMethod = "";
	private String appKey;
	private String appSecret;
	public 	 Block block;
	public Gag gag;
	public Keepalive keepalive;
	public Priority priority;
	public WhiteList whiteList;
	private RongCloud rongCloud;

	public RongCloud getRongCloud() {
		return rongCloud;
	}

	public void setRongCloud(RongCloud rongCloud) {
		this.rongCloud = rongCloud;
		gag.setRongCloud(rongCloud);
		keepalive.setRongCloud(rongCloud);
		priority.setRongCloud(rongCloud);
		whiteList.setRongCloud(rongCloud);
		block.setRongCloud(rongCloud);

	}
	public Chatroom(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.gag = new Gag(appKey,appSecret);
		this.keepalive = new Keepalive(appKey,appSecret);
		this.priority = new Priority(appKey,appSecret);
		this.whiteList = new WhiteList(appKey,appSecret);
		this.block = new Block(appKey,appSecret);

	}
	/**
	 * 创建聊天室方法 
	 * 
	 * @param  chatRoom:id:要创建的聊天室的id；name:要创建的聊天室的name。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult create(ChatRoom[] chatRoom) throws Exception {
		checkMethod = "create";
		if (chatRoom == null) {
			throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL, "/chatroom/create","Paramer 'chatRoomInfo' is required");
		}
		
	   	StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i< chatRoom.length; i++) {
			ChatRoom child  = chatRoom[i];
			sb.append("&chatroom["+child.getId()+"]=").append(URLEncoder.encode(child.getName(), UTF8));
		}
		
	   	String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
	   	HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/create.json", "application/x-www-form-urlencoded");
	   	HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,checkMethod,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
	}
	/**
	 * 销毁聊天室方法
	 *
	 * @param  chatroomIds:要销毁的聊天室 Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult destroy(String[] chatroomIds) throws Exception {
		checkMethod = "destroy";
		if (chatroomIds == null) {
			throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL, "/chatroom/destroy", "Paramer 'chatroomId' is required");
		}
		StringBuilder sb = new StringBuilder();

		for (int i = 0 ; i< chatroomIds.length; i++) {
			String child  = chatroomIds[i];
			sb.append("&chatroomId=").append(URLEncoder.encode(child, UTF8));
		}

		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/destroy.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);


		return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,checkMethod,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
	}
	/**
	 * 查询聊天室信息方法 
	 * 
	 * @param  chatroomIds:要查询的聊天室id（必传）
	 *
	 * @return ChatroomQueryResult
	 **/
	public ChatroomQueryResult query(String[] chatroomIds) throws Exception {
		Chatroom.checkMethod = "query";
		if (chatroomIds == null) {
			throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL, "/chatroom/query", "Paramer 'chatroomIds' is required");
		}
	    StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0 ; i< chatroomIds.length; i++) {
			String child  = chatroomIds[i];
			sb.append("&chatroomId=").append(URLEncoder.encode(child, UTF8));
		}
		
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/query.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);

	    return (ChatroomQueryResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,checkMethod,HttpUtil.returnResult(conn)), ChatroomQueryResult.class);
	}
	
	/**
	 * 查询聊天室内用户方法 
	 * 
	 * @param  chatroomId:要查询的聊天室 ID。（必传）
	 * @param  count:要获取的聊天室成员数，上限为 500 ，超过 500 时最多返回 500 个成员。（必传）
	 * @param  order:加入聊天室的先后顺序， 1 为加入时间正序， 2 为加入时间倒序。（必传）
	 *
	 * @return ChatroomUserQueryResult
	 **/
	public ChatroomUserQueryResult getMembers(String chatroomId, String count, String order) throws Exception {
		Chatroom.checkMethod = "getMembers";
		String message = CommonUtil.checkParam("id",chatroomId,PATH,checkObject,checkMethod);
		if(null != message){
			return (ChatroomUserQueryResult)GsonUtil.fromJson(message,ChatroomUserQueryResult.class);
		}
		message = CommonUtil.checkParam("count",count,PATH,checkObject,checkMethod);
		if(null != message){
			return (ChatroomUserQueryResult)GsonUtil.fromJson(message,ChatroomUserQueryResult.class);
		}
		message = CommonUtil.checkParam("order",order,PATH,checkObject,checkMethod);
		if(null != message){
			return (ChatroomUserQueryResult)GsonUtil.fromJson(message,ChatroomUserQueryResult.class);
		}
	    StringBuilder sb = new StringBuilder();
	    sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
	    sb.append("&count=").append(URLEncoder.encode(count.toString(), UTF8));
	    sb.append("&order=").append(URLEncoder.encode(order.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/user/query.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (ChatroomUserQueryResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,checkMethod,HttpUtil.returnResult(conn)), ChatroomUserQueryResult.class);
	}
	
	/**
	 * 聊天室消息停止分发方法（可实现控制对聊天室中消息是否进行分发，停止分发后聊天室中用户发送的消息，融云服务端不会再将消息发送给聊天室中其他用户。） 
	 * 
	 * @param  chatroomId:聊天室 Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult stopDistribution(String chatroomId) throws Exception {
		Chatroom.checkMethod = "stopDistribution";
		String message = CommonUtil.checkParam("id",chatroomId,PATH,"chatroom",checkMethod);
		if(null != message){
			return (CodeSuccessResult)GsonUtil.fromJson(message,CodeSuccessResult.class);
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/message/stopDistribution.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,checkMethod,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
	}
	
	/**
	 * 聊天室消息恢复分发方法 
	 * 
	 * @param  chatroomId:聊天室 Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult resumeDistribution(String chatroomId) throws Exception {
		Chatroom.checkMethod = "resumeDistribution";
		String message = CommonUtil.checkParam("id",chatroomId,PATH,checkObject,checkMethod);
		if(null != message){
			return (CodeSuccessResult)GsonUtil.fromJson(message,CodeSuccessResult.class);
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/message/resumeDistribution.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,checkMethod,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
	}
}