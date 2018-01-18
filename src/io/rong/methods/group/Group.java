package io.rong.methods.group;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.methods.group.gag.Gag;
import io.rong.models.*;
import io.rong.models.group.*;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Group {

	private static final String UTF8 = "UTF-8";
	private static final String PATH = "group";
	private String appKey;
	private String appSecret;
	public  Gag gag;
	private RongCloud rongCloud;

	public RongCloud getRongCloud() {
		return rongCloud;
	}

	public void setRongCloud(RongCloud rongCloud) {
		this.rongCloud = rongCloud;
		this.gag.setRongCloud(rongCloud);
	}
	public Group(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.gag = new Gag(appKey,appSecret);
		gag.setRongCloud(rongCloud);

	}
	/**
	 * 创建群组方法（创建群组，并将用户加入该群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人，App 内的群组数量没有限制.注：其实本方法是加入群组方法 /group/join 的别名。） 
	 *
	 * url /group/create.json
	 * docs http://rongcloud.cn/docs/server.html#group_sync"
	 *
	 * @param group 创建群组的群组信息
	 *
	 * @return GroupResponse
	 **/
	public ResponseResult create(GroupModel group) throws Exception {
		//需要校验的字段
		String[] fileds = {"id","merberIds","name"};
		String message = CommonUtil.checkFiled(fileds,group,PATH,"group",CheckMethod.CREATE);
		if(null != message){
			return (GroupResponse)GsonUtil.fromJson(message,GroupResponse.class);
		}
		StringBuilder sb = new StringBuilder();

	    for (int i = 0 ; i< group.getMerberIds().length; i++) {
			String child  = group.getMerberIds()[i];
			sb.append("&userId=").append(URLEncoder.encode(child, UTF8));
		}

	    sb.append("&groupId=").append(URLEncoder.encode(group.id.toString(), UTF8));
	    sb.append("&groupName=").append(URLEncoder.encode(group.name.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/group/create.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
		return (GroupResponse) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.CREATE,HttpUtil.returnResult(conn)), GroupResponse.class);
	}

	/**
	 * 同步用户所属群组方法（当第一次连接融云服务器时，需要向融云服务器提交 userId 对应的用户当前所加入的所有群组，此接口主要为防止应用中用户群信息同融云已知的用户所属群信息不同步。）
	 *
	 * @param  userId:被同步群信息的用户 Id。（必传）
	 * @param  group:该用户的群信息，如群 Id 已经存在，则不会刷新对应群组名称，如果想刷新群组名称请调用刷新群组信息方法。
	 *
	 * @return CodeSuccessResult
	 **/
	public ResponseResult sync(String userId, GroupModel[] group) throws Exception {
		String message = CommonUtil.checkParam("id",userId,PATH,"user",CheckMethod.SYNC);
		if(null != message){
			return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
		}
		if (group == null) {
			throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL, "/group/sync", "Paramer 'groupInfo' is required");
		}

	   	StringBuilder sb = new StringBuilder();
		sb.append("&userId=").append(URLEncoder.encode(userId, UTF8));

		for (int i = 0 ; i< group.length; i++) {
			GroupModel child  = group[i];
			sb.append("&group["+child.getId()+"]=").append(URLEncoder.encode(child.getName(), UTF8));
		}

	   	String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}

	   	HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/group/sync.json", "application/x-www-form-urlencoded");
	   	HttpUtil.setBodyParameter(body, conn);

	    return (GroupResponse) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.SYNC,HttpUtil.returnResult(conn)), GroupResponse.class);
	}

	/**
	 * 刷新群组信息方法
	 *
	 * @param  group:群组信息。id,name（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public ResponseResult refresh(GroupModel group) throws Exception {
		//需要校验的字段
		String[] fileds = {"id","name"};
		String message = CommonUtil.checkFiled(fileds,group,PATH,"group",CheckMethod.REFRESH);
		if(null != message){
			return (GroupResponse)GsonUtil.fromJson(message,GroupResponse.class);
		}
	    StringBuilder sb = new StringBuilder();
	    sb.append("&groupId=").append(URLEncoder.encode(group.id.toString(), UTF8));
	    sb.append("&groupName=").append(URLEncoder.encode(group.name.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/group/refresh.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    return (GroupResponse) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REFRESH,HttpUtil.returnResult(conn)), GroupResponse.class);
	}

	/**
	 * 将用户加入指定群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人。
	 *
	 * @param group 用户加入指定群组参数
	 *
	 * @return GroupResponse
	 **/
	public ResponseResult join(GroupModel group) throws Exception {
		//需要校验的字段
		String[] fileds = {"id","merberIds","name"};
		String message = CommonUtil.checkFiled(fileds,group,PATH,"group",CheckMethod.JOIN);
		if(null != message){
			return (GroupResponse)GsonUtil.fromJson(message,GroupResponse.class);
		}

	    StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0 ; i< group.merberIds.length; i++) {
			String child  = group.merberIds[i];
			sb.append("&userId=").append(URLEncoder.encode(child, UTF8));
		}
		
	    sb.append("&groupId=").append(URLEncoder.encode(group.id.toString(), UTF8));
	    sb.append("&groupName=").append(URLEncoder.encode(group.name.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/group/join.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (GroupResponse) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.JOIN,HttpUtil.returnResult(conn)), GroupResponse.class);
	}
	
	/**
	 * 查询群成员方法 
	 * 
	 * @param  groupId:群组Id。（必传）
	 *
	 * @return GroupUserQueryResult
	 **/
	public GroupUserQueryResult getMemberList(String groupId) throws Exception {
		//参数校验
		String message = CommonUtil.checkParam("id",groupId,PATH,"group",CheckMethod.GET_MEMBERS_LIST);
		if(null != message){
			return (GroupUserQueryResult)GsonUtil.fromJson(message,GroupUserQueryResult.class);
		}
	    StringBuilder sb = new StringBuilder();
	    sb.append("&groupId=").append(URLEncoder.encode(groupId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/group/user/query.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (GroupUserQueryResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GET_MEMBERS_LIST,HttpUtil.returnResult(conn)), GroupUserQueryResult.class);
	}
	
	/**
	 * 退出群组方法（将用户从群中移除，不再接收该群组的消息.） 
	 * 
	 * @param  group:群组.（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public ResponseResult quit(GroupModel group) throws Exception {

		//需要校验的字段
		String[] fileds = {"id","merberIds"};

		String message = CommonUtil.checkFiled(fileds,group,PATH,"group",CheckMethod.QUIT);
		System.out.println("message:"+message);

		if(null != message){
			return (GroupResponse)GsonUtil.fromJson(message,GroupResponse.class);
			//throw new ParamException(code);
		}


		
	    StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0 ; i< group.merberIds.length; i++) {
			String child  = group.merberIds[i];
			sb.append("&userId=").append(URLEncoder.encode(child, UTF8));
		}
		
	    sb.append("&groupId=").append(URLEncoder.encode(group.id.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/group/quit.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (GroupResponse) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.QUIT,HttpUtil.returnResult(conn)), GroupResponse.class);
	}
	/**
	 * 解散群组方法。（将该群解散，所有用户都无法再接收该群的消息。） 
	 * 
	 * @param  userId:操作解散群的用户 Id。（必传）
	 * @param  groupId:要解散的群 Id。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public GroupResponse dismiss(String userId, String groupId) throws Exception {
		//参数校验
		String message = CommonUtil.checkParam("id",userId,PATH,"user",CheckMethod.DISMISS);
		if(null != message){
			return (GroupResponse)GsonUtil.fromJson(message,GroupResponse.class);
			//throw new ParamException(code);
		}
		message = CommonUtil.checkParam("id",userId,PATH,"group",CheckMethod.DISMISS);
		if(null != message){
			return (GroupResponse)GsonUtil.fromJson(message,GroupResponse.class);
			//throw new ParamException(code);
		}

	    StringBuilder sb = new StringBuilder();
	    sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
	    sb.append("&groupId=").append(URLEncoder.encode(groupId.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/group/dismiss.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (GroupResponse) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.DISMISS,HttpUtil.returnResult(conn)), GroupResponse.class);
	}
}