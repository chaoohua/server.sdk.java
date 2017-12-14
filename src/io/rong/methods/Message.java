package io.rong.methods;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import io.rong.messages.*;
import io.rong.models.*;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import io.rong.util.HostType;


public class Message {

	private static final String UTF8 = "UTF-8";
	private String appKey;
	private String appSecret;
	
	public Message(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}

	/**
	 * 发送单聊消息方法（一个用户向另外一个用户发送消息，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人，如：一次发送 1000 人时，示为 1000 条消息。） 
	 * 
	 * @param  fromUserId:发送人用户 Id。（必传）
	 * @param  toUserId:接收用户 Id，可以实现向多人发送消息，每次上限为 1000 人。（必传）
	 * @param  message:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）。
	 * @param  pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息。如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。（可选）
	 * @param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。（可选）
	 * @param  count:针对 iOS 平台，Push 时用来控制未读消息显示数，只有在 toUserId 为一个用户 Id 的时候有效。（可选）
	 * @param  verifyBlacklist:是否过滤发送人黑名单列表，0 表示为不过滤、 1 表示为过滤，默认为 0 不过滤。（可选）
	 * @param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。（可选）
	 * @param  isCounted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选）
	 * @param  isIncludeSender:发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。（可选）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult publishPrivate(String fromUserId, String[] toUserId, BaseMessage message, String pushContent, String pushData, String count, Integer verifyBlacklist, Integer isPersisted, Integer isCounted, Integer isIncludeSender) throws Exception {

		if (fromUserId == null) {
			throw new IllegalArgumentException("Paramer 'fromUserId' is required");
		}
		
		if (toUserId == null) {
			throw new IllegalArgumentException("Paramer 'toUserId' is required");
		}
		
		if (message == null) {
			throw new IllegalArgumentException("Paramer 'message' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&fromUserId=").append(URLEncoder.encode(fromUserId.toString(), UTF8));
	    
	    for (int i = 0 ; i< toUserId.length; i++) {
			String child  = toUserId[i];
			sb.append("&toUserId=").append(URLEncoder.encode(child, UTF8));
		}
		
	    sb.append("&objectName=").append(URLEncoder.encode(message.getType(), UTF8));
   	    sb.append("&content=").append(URLEncoder.encode(message.toString(), UTF8));
	    
	    if (pushContent != null) {
	    	sb.append("&pushContent=").append(URLEncoder.encode(pushContent.toString(), UTF8));
	    }
	    
	    if (pushData != null) {
	    	sb.append("&pushData=").append(URLEncoder.encode(pushData.toString(), UTF8));
	    }
	    
	    if (count != null) {
	    	sb.append("&count=").append(URLEncoder.encode(count.toString(), UTF8));
	    }
	    
	    if (verifyBlacklist != null) {
	    	sb.append("&verifyBlacklist=").append(URLEncoder.encode(verifyBlacklist.toString(), UTF8));
	    }
	    
	    if (isPersisted != null) {
	    	sb.append("&isPersisted=").append(URLEncoder.encode(isPersisted.toString(), UTF8));
	    }
	    
	    if (isCounted != null) {
	    	sb.append("&isCounted=").append(URLEncoder.encode(isCounted.toString(), UTF8));
	    }
	    
	    if (isIncludeSender != null) {
	    	sb.append("&isIncludeSender=").append(URLEncoder.encode(isIncludeSender.toString(), UTF8));
	    }
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/message/private/publish.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);

	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 发送单聊模板消息方法（一个用户向多个用户发送不同消息内容，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人。） 
	 * 
	 * @param  templateMessage:单聊模版消息。
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult publishTemplate(TemplateMessage templateMessage) throws Exception {
		if (templateMessage == null) {
			throw new IllegalArgumentException("Paramer 'templateMessage' is required");
		}
		
	    HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/message/private/publish_template.json", "application/json");
	    HttpUtil.setBodyParameter(templateMessage.toString(), conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 发送系统消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM。每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。） 
	 * 
	 * @param  fromUserId:发送人用户 Id。（必传）
	 * @param  toUserId:接收用户 Id，提供多个本参数可以实现向多人发送消息，上限为 1000 人。（必传）
	 * @param  message:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）
	 * @param  pushContent:如果为自定义消息，定义显示的 Push 内容，内容中定义标识通过 values 中设置的标识位内容进行替换.如消息类型为自定义不需要 Push 通知，则对应数组传空值即可。（可选）
	 * @param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。如不需要 Push 功能对应数组传空值即可。（可选）
	 * @param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。（可选）
	 * @param  isCounted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult PublishSystem(String fromUserId, String[] toUserId, BaseMessage message, String pushContent, String pushData, Integer isPersisted, Integer isCounted) throws Exception {
		if (fromUserId == null) {
			throw new IllegalArgumentException("Paramer 'fromUserId' is required");
		}
		
		if (toUserId == null) {
			throw new IllegalArgumentException("Paramer 'toUserId' is required");
		}
		
		if (message == null) {
			throw new IllegalArgumentException("Paramer 'message' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&fromUserId=").append(URLEncoder.encode(fromUserId.toString(), UTF8));
	    
	    for (int i = 0 ; i< toUserId.length; i++) {
			String child  = toUserId[i];
			sb.append("&toUserId=").append(URLEncoder.encode(child, UTF8));
		}
		
	    sb.append("&objectName=").append(URLEncoder.encode(message.getType(), UTF8));
   	    sb.append("&content=").append(URLEncoder.encode(message.toString(), UTF8));
	    
	    if (pushContent != null) {
	    	sb.append("&pushContent=").append(URLEncoder.encode(pushContent.toString(), UTF8));
	    }
	    
	    if (pushData != null) {
	    	sb.append("&pushData=").append(URLEncoder.encode(pushData.toString(), UTF8));
	    }
	    
	    if (isPersisted != null) {
	    	sb.append("&isPersisted=").append(URLEncoder.encode(isPersisted.toString(), UTF8));
	    }
	    
	    if (isCounted != null) {
	    	sb.append("&isCounted=").append(URLEncoder.encode(isCounted.toString(), UTF8));
	    }
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/message/system/publish.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 发送系统模板消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM.每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。） 
	 * 
	 * @param  templateMessage:系统模版消息。
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult publishSystemTemplate(TemplateMessage templateMessage) throws Exception {
		if (templateMessage == null) {
			throw new IllegalArgumentException("Paramer 'templateMessage' is required");
		}
		
	    HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/message/system/publish_template.json", "application/json");
	    HttpUtil.setBodyParameter(templateMessage.toString(), conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 发送群组消息方法（以一个用户身份向群组发送消息，单条消息最大 128k.每秒钟最多发送 20 条消息，每次最多向 3 个群组发送，如：一次向 3 个群组发送消息，示为 3 条消息。） 
	 * 
	 * @param  fromUserId:发送人用户 Id 。（必传）
	 * @param  toGroupId:接收群Id，提供多个本参数可以实现向多群发送消息，最多不超过 3 个群组。（必传）
	 * @param  message:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）
	 * @param  pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息. 如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。（可选）
	 * @param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。（可选）
	 * @param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息。（可选）
	 * @param  isCounted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选）
	 * @param  isIncludeSender:发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。（可选）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult publishGroup(String fromUserId, String[] toGroupId, BaseMessage message, String pushContent, String pushData, Integer isPersisted, Integer isCounted, Integer isIncludeSender) throws Exception {
		if (fromUserId == null) {
			throw new IllegalArgumentException("Paramer 'fromUserId' is required");
		}
		
		if (toGroupId == null) {
			throw new IllegalArgumentException("Paramer 'toGroupId' is required");
		}
		
		if (message == null) {
			throw new IllegalArgumentException("Paramer 'message' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&fromUserId=").append(URLEncoder.encode(fromUserId.toString(), UTF8));
	    
	    for (int i = 0 ; i< toGroupId.length; i++) {
			String child  = toGroupId[i];
			sb.append("&toGroupId=").append(URLEncoder.encode(child, UTF8));
		}
		
	    sb.append("&objectName=").append(URLEncoder.encode(message.getType(), UTF8));
   	    sb.append("&content=").append(URLEncoder.encode(message.toString(), UTF8));
	    
	    if (pushContent != null) {
	    	sb.append("&pushContent=").append(URLEncoder.encode(pushContent.toString(), UTF8));
	    }
	    
	    if (pushData != null) {
	    	sb.append("&pushData=").append(URLEncoder.encode(pushData.toString(), UTF8));
	    }
	    
	    if (isPersisted != null) {
	    	sb.append("&isPersisted=").append(URLEncoder.encode(isPersisted.toString(), UTF8));
	    }
	    
	    if (isCounted != null) {
	    	sb.append("&isCounted=").append(URLEncoder.encode(isCounted.toString(), UTF8));
	    }
	    
	    if (isIncludeSender != null) {
	    	sb.append("&isIncludeSender=").append(URLEncoder.encode(isIncludeSender.toString(), UTF8));
	    }
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/message/group/publish.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 发送讨论组消息方法（以一个用户身份向讨论组发送消息，单条消息最大 128k，每秒钟最多发送 20 条消息.） 
	 * 
	 * @param  fromUserId:发送人用户 Id。（必传）
	 * @param  toDiscussionId:接收讨论组 Id。（必传）
	 * @param  message:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。（必传）
	 * @param  pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息. 如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知。（可选）
	 * @param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData.（可选）
	 * @param  isPersisted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行存储，0 表示为不存储、 1 表示为存储，默认为 1 存储消息.（可选）
	 * @param  isCounted:当前版本有新的自定义消息，而老版本没有该自定义消息时，老版本客户端收到消息后是否进行未读消息计数，0 表示为不计数、 1 表示为计数，默认为 1 计数，未读消息数增加 1。（可选）
	 * @param  isIncludeSender:发送用户自已是否接收消息，0 表示为不接收，1 表示为接收，默认为 0 不接收。（可选）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult publishDiscussion(String fromUserId, String toDiscussionId, BaseMessage message, String pushContent, String pushData, Integer isPersisted, Integer isCounted, Integer isIncludeSender) throws Exception {
		if (fromUserId == null) {
			throw new IllegalArgumentException("Paramer 'fromUserId' is required");
		}
		
		if (toDiscussionId == null) {
			throw new IllegalArgumentException("Paramer 'toDiscussionId' is required");
		}
		
		if (message == null) {
			throw new IllegalArgumentException("Paramer 'message' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&fromUserId=").append(URLEncoder.encode(fromUserId.toString(), UTF8));
	    sb.append("&toDiscussionId=").append(URLEncoder.encode(toDiscussionId.toString(), UTF8));
	    sb.append("&objectName=").append(URLEncoder.encode(message.getType(), UTF8));
   	    sb.append("&content=").append(URLEncoder.encode(message.toString(), UTF8));
	    
	    if (pushContent != null) {
	    	sb.append("&pushContent=").append(URLEncoder.encode(pushContent.toString(), UTF8));
	    }
	    
	    if (pushData != null) {
	    	sb.append("&pushData=").append(URLEncoder.encode(pushData.toString(), UTF8));
	    }
	    
	    if (isPersisted != null) {
	    	sb.append("&isPersisted=").append(URLEncoder.encode(isPersisted.toString(), UTF8));
	    }
	    
	    if (isCounted != null) {
	    	sb.append("&isCounted=").append(URLEncoder.encode(isCounted.toString(), UTF8));
	    }
	    
	    if (isIncludeSender != null) {
	    	sb.append("&isIncludeSender=").append(URLEncoder.encode(isIncludeSender.toString(), UTF8));
	    }
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/message/discussion/publish.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 发送聊天室消息方法（一个用户向聊天室发送消息，单条消息最大 128k。每秒钟限 100 次。） 
	 * 
	 * @param  fromUserId:发送人用户 Id。（必传）
	 * @param  toChatroomId:接收聊天室Id，提供多个本参数可以实现向多个聊天室发送消息。（必传）
	 * @param  message:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。融云消息类型在messages下，自定义消息继承BaseMessage即可（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult publishChatroom(String fromUserId, String[] toChatroomId, BaseMessage message) throws Exception {
		if (fromUserId == null) {
			throw new IllegalArgumentException("Paramer 'fromUserId' is required");
		}
		
		if (toChatroomId == null) {
			throw new IllegalArgumentException("Paramer 'toChatroomId' is required");
		}
		
		if (message == null) {
			throw new IllegalArgumentException("Paramer 'message' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&fromUserId=").append(URLEncoder.encode(fromUserId.toString(), UTF8));
	    
	    for (int i = 0 ; i< toChatroomId.length; i++) {
			String child  = toChatroomId[i];
			sb.append("&toChatroomId=").append(URLEncoder.encode(child, UTF8));
		}
		
	    sb.append("&objectName=").append(URLEncoder.encode(message.getType(), UTF8));
   	    sb.append("&content=").append(URLEncoder.encode(message.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/message/chatroom/publish.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}

	/**
	 * 发送聊天室消息方法（一个用户向聊天室发送消息，单条消息最大 128k。每秒钟限 100 次。）
	 *
	 * @param  fromUserId:发送人用户 Id。（必传）
	 * @param  message:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。融云消息类型在messages下，自定义消息继承BaseMessage即可（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult broadcastChatroom ( String fromUserId, BaseMessage message) throws Exception {
		if (fromUserId == null) {
			throw new IllegalArgumentException("Paramer 'fromUserId' is required");
		}

		if (message == null) {
			throw new IllegalArgumentException("Paramer 'message' is required");
		}

		StringBuilder sb = new StringBuilder();
		sb.append("&fromUserId=").append(URLEncoder.encode(fromUserId.toString(), UTF8));


		sb.append("&objectName=").append(URLEncoder.encode(message.getType(), UTF8));
		sb.append("&content=").append(URLEncoder.encode(message.toString(), UTF8));
		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/message/chatroom/broadcast.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);

		return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 发送广播消息方法（发送消息给一个应用下的所有注册用户，如用户未在线会对满足条件（绑定手机终端）的用户发送 Push 信息，单条消息最大 128k，会话类型为 SYSTEM。每小时只能发送 2 次，每天最多发送 3 次。）
	 * 该功能开发环境下可免费使用。生产环境下，您需要登录开发者后台，在“应用/IM 服务/高级功能设置”中开通公有云专业版后，在“广播消息和推送”中，开启后才能使用
	 *
	 * @param  fromUserId:发送人用户 Id。（必传）
	 * @param  message:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。融云消息类型在messages下，自定义消息继承BaseMessage即可（必传）
	 * @param  pushContent:定义显示的 Push 内容，如果 objectName 为融云内置消息类型时，则发送后用户一定会收到 Push 信息. 如果为自定义消息，则 pushContent 为自定义消息显示的 Push 内容，如果不传则用户不会收到 Push 通知.（可选）
	 * @param  pushData:针对 iOS 平台为 Push 通知时附加到 payload 中，Android 客户端收到推送消息时对应字段名为 pushData。（可选）
	 * @param  os:针对操作系统发送 Push，值为 iOS 表示对 iOS 手机用户发送 Push ,为 Android 时表示对 Android 手机用户发送 Push ，如对所有用户发送 Push 信息，则不需要传 os 参数。（可选）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult broadcast(String fromUserId, BaseMessage message, String pushContent, String pushData, String os) throws Exception {
		if (fromUserId == null) {
			throw new IllegalArgumentException("Paramer 'fromUserId' is required");
		}
		
		if (message == null) {
			throw new IllegalArgumentException("Paramer 'message' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&fromUserId=").append(URLEncoder.encode(fromUserId.toString(), UTF8));
	    sb.append("&objectName=").append(URLEncoder.encode(message.getType(), UTF8));
   	    sb.append("&content=").append(URLEncoder.encode(message.toString(), UTF8));
	    
	    if (pushContent != null) {
	    	sb.append("&pushContent=").append(URLEncoder.encode(pushContent.toString(), UTF8));
	    }
	    
	    if (pushData != null) {
	    	sb.append("&pushData=").append(URLEncoder.encode(pushData.toString(), UTF8));
	    }
	    
	    if (os != null) {
	    	sb.append("&os=").append(URLEncoder.encode(os.toString(), UTF8));
	    }
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/message/broadcast.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}
	
	/**
	 * 消息历史记录下载地址获取 方法消息历史记录下载地址获取方法。获取 APP 内指定某天某小时内的所有会话消息记录的下载地址。（目前支持二人会话、讨论组、群组、聊天室、客服、系统通知消息历史记录下载） 
	 * 
	 * @param  date:指定北京时间某天某小时，格式为2014010101,表示：2014年1月1日凌晨1点。（必传）
	 *
	 * @return HistoryMessageResult
	 **/
	public HistoryMessageResult getHistory(String date) throws Exception {
		if (date == null) {
			throw new IllegalArgumentException("Paramer 'date' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&date=").append(URLEncoder.encode(date.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/message/history.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (HistoryMessageResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), HistoryMessageResult.class);
	}
	
	/**
	 * 消息历史记录删除方法（删除 APP 内指定某天某小时内的所有会话消息记录。调用该接口返回成功后，date参数指定的某小时的消息记录文件将在随后的5-10分钟内被永久删除。） 
	 * 
	 * @param  date:指定北京时间某天某小时，格式为2014010101,表示：2014年1月1日凌晨1点。（必传）
	 *
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult deleteMessage(String date) throws Exception {
		if (date == null) {
			throw new IllegalArgumentException("Paramer 'date' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&date=").append(URLEncoder.encode(date.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/message/history/delete.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}

	/**
	 * 设置用户某会话接收新消息时是否进行消息提醒。
	 *
	 * @param conversationType:会话类型。（必传）
	 * @param requestId:设置消息免打扰的用户 Id
	 * @param targetId:目标Id
	 * @param isMuted:消息免打扰设置状态，0 表示为关闭，1 表示为开启。（必传）
	 * @return CodeSuccessResult
	 **/
	public CodeSuccessResult setConversationNotification(String conversationType,String requestId,String targetId,Integer isMuted) throws Exception {
		if (conversationType == null) {
			throw new IllegalArgumentException("Paramer 'conversationType' is required");
		}
		if (requestId == null) {
			throw new IllegalArgumentException("Paramer 'requestId' is required");
		}
		if (targetId == null) {
			throw new IllegalArgumentException("Paramer 'targetId' is required");
		}
		if (isMuted == null) {
			throw new IllegalArgumentException("Paramer 'isMuted' is required");
		}
		//conversationType=1&requestId=b5NwvIrW8&targetId=UAhIaLkR0&isMuted=0
		StringBuilder sb = new StringBuilder();
		sb.append("&conversationType=").append(URLEncoder.encode(conversationType.toString(), UTF8));
		sb.append("&requestId=").append(URLEncoder.encode(requestId.toString(), UTF8));
		sb.append("&targetId=").append(URLEncoder.encode(targetId.toString(), UTF8));
		sb.append("&isMuted=").append(URLEncoder.encode(isMuted.toString(), UTF8));
		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/conversation/notification/set.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);

		return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
	}

	/**
	 * 设置用户某会话接收新消息时是否进行消息提醒。
	 *
	 * @param conversationType:会话类型。（必传）
	 * @param requestId:设置消息免打扰的用户 Id
	 * @param targetId:目标Id
	 * @return CodeSuccessResult
	 **/
	public ConversationNotificationResult getConversationNotification(String conversationType,String requestId,String targetId) throws Exception {

		if (null == conversationType) {
			throw new IllegalArgumentException("Paramer 'conversationType' is required");
		}
		if (null == requestId){
			throw new IllegalArgumentException("Paramer 'requestId' is required");
		}
		if (null == targetId) {
			throw new IllegalArgumentException("Paramer 'targetId' is required");
		}
		//conversationType=1&requestId=b5NwvIrW8&targetId=UAhIaLkR0&isMuted=0
		StringBuilder sb = new StringBuilder();
		sb.append("&conversationType=").append(URLEncoder.encode(conversationType.toString(), UTF8));
		sb.append("&requestId=").append(URLEncoder.encode(requestId.toString(), UTF8));
		sb.append("&targetId=").append(URLEncoder.encode(targetId.toString(), UTF8));
		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/conversation/notification/get.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);

		return (ConversationNotificationResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), ConversationNotificationResult.class);
	}
	 
}