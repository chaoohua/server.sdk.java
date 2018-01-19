package io.rong.example;

import io.rong.RongCloud;
import io.rong.models.*;
import io.rong.models.chatroom.ChatroomQueryResult;
import io.rong.models.chatroom.ChatroomUserQueryResult;
import io.rong.models.sms.SmsModel;
import io.rong.models.user.UserModel;
import io.rong.util.GsonUtil;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * 一些api的调用示例
 */
public class Example {
	private static final String JSONFILE = Example.class.getClassLoader().getResource("jsonsource").getPath()+"/";
	/**
	 * 本地调用测试
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String appKey = "appkey";//替换成您的appkey
		String appSecret = "secret";//替换成匹配上面key的secret
		
		Reader reader = null ;
		RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
				
		
		System.out.println("************************User********************");
		UserModel user = new UserModel("userId1", "username", "http://www.rongcloud.cn/images/logo.png");


		// 获取 Token 方法
		TokenResult userGetTokenResult = rongCloud.user.getToken(user);
		System.out.println("getToken:  " + userGetTokenResult.toString());

		/*// 刷新用户信息方法
		CodeSuccessResult userRefreshResult = rongCloud.user.refresh(user);
		System.out.println("refresh:  " + userRefreshResult.toString());
		// 检查用户在线状态 方法 
		CheckOnlineResult userCheckOnlineResult = rongCloud.user.checkOnline("userId1");
		System.out.println("checkOnline:  " + userCheckOnlineResult.toString());
		
		// 封禁用户方法（每秒钟限 100 次） 
		CodeSuccessResult userBlockResult = rongCloud.user.block("userId4", 10);
		System.out.println("block:  " + userBlockResult.toString());
		
		// 解除用户封禁方法（每秒钟限 100 次） 
		CodeSuccessResult userUnBlockResult = rongCloud.user.unBlock("userId2");
		System.out.println("unBlock:  " + userUnBlockResult.toString());
		
		// 获取被封禁用户方法（每秒钟限 100 次） 
		QueryBlockUserResult userQueryBlockResult = rongCloud.user.queryBlock();
		System.out.println("queryBlock:  " + userQueryBlockResult.toString());
		
		// 添加用户到黑名单方法（每秒钟限 100 次） 
		CodeSuccessResult userAddBlacklistResult = rongCloud.user.addBlacklist("userId1", "userId2");
		System.out.println("addBlacklist:  " + userAddBlacklistResult.toString());
		
		// 获取某用户的黑名单列表方法（每秒钟限 100 次） 
		QueryBlacklistUserResult userQueryBlacklistResult = rongCloud.user.queryBlacklist("userId1");
		System.out.println("queryBlacklist:  " + userQueryBlacklistResult.toString());
		
		// 从黑名单中移除用户方法（每秒钟限 100 次） 
		CodeSuccessResult userRemoveBlacklistResult = rongCloud.user.removeBlacklist("userId1", "userId2");
		System.out.println("removeBlacklist:  " + userRemoveBlacklistResult.toString());
		
		*/
		/*
		System.out.println("************************Message********************");
		// 发送单聊消息方法（一个用户向另外一个用户发送消息，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人，如：一次发送 1000 人时，示为 1000 条消息。） 
		String[] messagePublishPrivateToUserId = {"userId2","userid3","userId4"};
		VoiceMessage messagePublishPrivateVoiceMessage = new VoiceMessage("hello", "helloExtra", 20L);
		CodeSuccessResult messagePublishPrivateResult = rongCloud.message.publishPrivate("userId1", messagePublishPrivateToUserId, messagePublishPrivateVoiceMessage, "thisisapush", "{\"pushData\":\"hello\"}", "4", 0, 0, 0, 0);
		System.out.println("publishPrivate:  " + messagePublishPrivateResult.toString());
		
		// 发送单聊模板消息方法（一个用户向多个用户发送不同消息内容，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人。） 
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE+"TemplateMessage.json"));
			TemplateMessage publishTemplateTemplateMessage  =  (TemplateMessage)GsonUtil.fromJson(reader, TemplateMessage.class);
			CodeSuccessResult messagePublishTemplateResult = rongCloud.message.publishTemplate(publishTemplateTemplateMessage);
			System.out.println("publishTemplate:  " + messagePublishTemplateResult.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(null != reader){
				reader.close();
			}
		} 
		
		// 发送系统消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM。每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。） 
		String[] messagePublishSystemToUserId = {"userId2","userid3","userId4"};
		TxtMessage messagePublishSystemTxtMessage = new TxtMessage("hello", "helloExtra");
		CodeSuccessResult messagePublishSystemResult = rongCloud.message.PublishSystem("userId1", messagePublishSystemToUserId, messagePublishSystemTxtMessage, "thisisapush", "{\"pushData\":\"hello\"}", 0, 0);
		System.out.println("PublishSystem:  " + messagePublishSystemResult.toString());
		
		// 发送系统模板消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM.每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。） 
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE+"TemplateMessage.json"));
			TemplateMessage publishSystemTemplateTemplateMessage  =  (TemplateMessage)GsonUtil.fromJson(reader, TemplateMessage.class);
			CodeSuccessResult messagePublishSystemTemplateResult = rongCloud.message.publishSystemTemplate(publishSystemTemplateTemplateMessage);
			System.out.println("publishSystemTemplate:  " + messagePublishSystemTemplateResult.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(null != reader){
				reader.close();
			}
		} 
		
		// 发送群组消息方法（以一个用户身份向群组发送消息，单条消息最大 128k.每秒钟最多发送 20 条消息，每次最多向 3 个群组发送，如：一次向 3 个群组发送消息，示为 3 条消息。） 
		String[] messagePublishGroupToGroupId = {"groupId1","groupId2","groupId3"};
		TxtMessage messagePublishGroupTxtMessage = new TxtMessage("hello", "helloExtra");
		CodeSuccessResult messagePublishGroupResult = rongCloud.message.publishGroup("userId", messagePublishGroupToGroupId, messagePublishGroupTxtMessage, "thisisapush", "{\"pushData\":\"hello\"}", 1, 1, 0);
		System.out.println("publishGroup:  " + messagePublishGroupResult.toString());
		
		// 发送讨论组消息方法（以一个用户身份向讨论组发送消息，单条消息最大 128k，每秒钟最多发送 20 条消息.） 
		TxtMessage messagePublishDiscussionTxtMessage = new TxtMessage("hello", "helloExtra");
		CodeSuccessResult messagePublishDiscussionResult = rongCloud.message.publishDiscussion("userId1", "discussionId1", messagePublishDiscussionTxtMessage, "thisisapush", "{\"pushData\":\"hello\"}", 1, 1, 0);
		System.out.println("publishDiscussion:  " + messagePublishDiscussionResult.toString());
		
		// 发送聊天室消息方法（一个用户向聊天室发送消息，单条消息最大 128k。每秒钟限 100 次。） 
		String[] messagePublishChatroomToChatroomId = {"ChatroomId1","ChatroomId2","ChatroomId3"};
		TxtMessage messagePublishChatroomTxtMessage = new TxtMessage("hello", "helloExtra");
		CodeSuccessResult messagePublishChatroomResult = rongCloud.message.publishChatroom("userId1", messagePublishChatroomToChatroomId, messagePublishChatroomTxtMessage);
		System.out.println("publishChatroom:  " + messagePublishChatroomResult.toString());

		//发送聊天室广播消息方法(向应用中的所有聊天室发送一条消息，单条消息最大 128k)
		//String[] messagePublishChatroomToChatroomId = {"ChatroomId1","ChatroomId2","ChatroomId3"};
		TxtMessage messageBroadcastChatroomTxtMessage = new TxtMessage("hello", "helloExtra");
		CodeSuccessResult messageBroadcastChatroomResult = rongCloud.message.broadcastChatroom("userId1", messagePublishChatroomTxtMessage);
		System.out.println("publishChatroom:  " + messageBroadcastChatroomResult.toString());


		// 发送广播消息方法（发送消息给一个应用下的所有注册用户，如用户未在线会对满足条件（绑定手机终端）的用户发送 Push 信息，单条消息最大 128k，会话类型为 SYSTEM。每小时只能发送 1 次，每天最多发送 3 次。） 
		TxtMessage messageBroadcastTxtMessage = new TxtMessage("hello", "helloExtra");
		CodeSuccessResult messageBroadcastResult = rongCloud.message.broadcast("userId1", messageBroadcastTxtMessage, "thisisapush", "{\"pushData\":\"hello\"}", "iOS");
		System.out.println("broadcast:  " + messageBroadcastResult.toString());
		
		// 消息历史记录下载地址获取 方法消息历史记录下载地址获取方法。获取 APP 内指定某天某小时内的所有会话消息记录的下载地址。（目前支持二人会话、讨论组、群组、聊天室、客服、系统通知消息历史记录下载） 
		HistoryMessageResult messageGetHistoryResult = rongCloud.message.getHistory("2014010101");
		System.out.println("getHistory:  " + messageGetHistoryResult.toString());
		
		// 消息历史记录删除方法（删除 APP 内指定某天某小时内的所有会话消息记录。调用该接口返回成功后，date参数指定的某小时的消息记录文件将在随后的5-10分钟内被永久删除。） 
		CodeSuccessResult messageDeleteMessageResult = rongCloud.message.deleteMessage("2014010101");
		System.out.println("deleteMessage:  " + messageDeleteMessageResult.toString());
		
		*/
		
		System.out.println("************************Wordfilter********************");
		//敏感词服务已升级，Wordfilter为原敏感词服务。 仍然可以使用，但是不再继续更新。
		// 添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。） 
		CodeSuccessResult wordfilterAddResult = rongCloud.wordfilter.add("money");
		System.out.println("add:  " + wordfilterAddResult.toString());
		
		// 查询敏感词列表方法 
		ListWordfilterResult wordfilterGetListResult = rongCloud.wordfilter.getList();
		System.out.println("getList:  " + wordfilterGetListResult.toString());
		
		// 移除敏感词方法（从敏感词列表中，移除某一敏感词。） 
		CodeSuccessResult wordfilterDeleteResult = rongCloud.wordfilter.delete("money");
		System.out.println("delete:  " + wordfilterDeleteResult.toString());


		//敏感词服务已升级，Sensitiveword新敏感词服务。

		System.out.println("************************Sensitiveword********************");
		// 添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。） 
		CodeSuccessResult sensitivewordAddResult = rongCloud.sensitiveword.add("money", "****");
		System.out.println("add:  " + sensitivewordAddResult.toString());
		
		// 查询敏感词列表方法 
		ListWordfilterResult sensitivewordGetListResult = rongCloud.sensitiveword.getList(1);
		System.out.println("getList:  " + sensitivewordGetListResult.toString());
		
		// 移除敏感词方法（从敏感词列表中，移除某一敏感词。） 
		CodeSuccessResult sensitivewordDeleteResult = rongCloud.sensitiveword.remove("money");
		System.out.println("delete:  " + sensitivewordDeleteResult.toString());


		List<String> list =  new ArrayList<String>();
		String[] words = {"money","money1"};
		// 批量移除敏感词方法（从敏感词列表中，批量移除某些敏感词，一次最多移除敏感词不超过 50 个，移除后 2 小时内生效.）
		CodeSuccessResult wordfilterBatchDeleteResult = rongCloud.sensitiveword.batchDelete(words);
		System.out.println("delete:  " + wordfilterBatchDeleteResult.toString());

		// 加入聊天室方法 
		/*
		String[] chatroomJoinUserId = {"userId2","userid3","userId4"};
		CodeSuccessResult chatroomJoinResult = rongCloud.chatroom.join(chatroomJoinUserId, "chatroomId1");
		System.out.println("join:  " + chatroomJoinResult.toString());
		*/
		
		// 查询聊天室信息方法 
		String[] chatroomQueryChatroomId = {"chatroomId1","chatroomId2","chatroomId3"};
		ChatroomQueryResult chatroomQueryResult = rongCloud.chatroom.query(chatroomQueryChatroomId);
		System.out.println("query:  " + chatroomQueryResult.toString());
		
		// 查询聊天室内用户方法 
		ChatroomUserQueryResult chatroomQueryUserResult = rongCloud.chatroom.getMembers("chatroomId1", "500", "2");
		System.out.println("queryUser:  " + chatroomQueryUserResult.toString());
		
		// 聊天室消息停止分发方法（可实现控制对聊天室中消息是否进行分发，停止分发后聊天室中用户发送的消息，融云服务端不会再将消息发送给聊天室中其他用户。） 
		CodeSuccessResult chatroomStopDistributionMessageResult = rongCloud.chatroom.stopDistribution("chatroomId1");
		System.out.println("stopDistributionMessage:  " + chatroomStopDistributionMessageResult.toString());
		
		// 聊天室消息恢复分发方法 
		CodeSuccessResult chatroomResumeDistributionMessageResult = rongCloud.chatroom.resumeDistribution("chatroomId1");
		System.out.println("resumeDistributionMessage:  " + chatroomResumeDistributionMessageResult.toString());
		
		System.out.println("************************Push********************");
		// 添加 Push 标签方法 
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE+"UserTag.json"));
			UserTag setUserPushTagUserTag  =  (UserTag)GsonUtil.fromJson(reader, UserTag.class);
			CodeSuccessResult pushSetUserPushTagResult = rongCloud.push.setUserTag(setUserPushTagUserTag);
			System.out.println("setUserPushTag:  " + pushSetUserPushTagResult.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(null != reader){
				reader.close();
			}
		} 
		
		// 广播消息方法（fromuserid 和 message为null即为不落地的push） 
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE+"PushMessage.json"));
			PushMessage broadcastPushPushMessage  =  (PushMessage)GsonUtil.fromJson(reader, PushMessage.class);
			CodeSuccessResult pushBroadcastPushResult = rongCloud.push.send(broadcastPushPushMessage);
			System.out.println("broadcastPush:  " + pushBroadcastPushResult.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(null != reader){
				reader.close();
			}
		}

		SmsModel sms = new SmsModel("13500000000", "dsfdsfd", "86", "1408706337", "1408706337");
		
		System.out.println("************************SMS********************");
		// 获取图片验证码方法 
		SMSImageCodeResult sMSGetImageCodeResult = rongCloud.sms.code.getImage("app-key");
		System.out.println("getImageCode:  " + sMSGetImageCodeResult.toString());
		
		// 发送短信验证码方法。 
		SMSSendCodeResult sMSSendCodeResult = rongCloud.sms.code.send(sms);
		System.out.println("sendCode:  " + sMSSendCodeResult.toString());
		
		// 验证码验证方法 
		SMSVerifyCodeResult sMSVerifyCodeResult = rongCloud.sms.code.verify("2312312", "2312312");
		System.out.println("verifyCode:  " + sMSVerifyCodeResult.toString());
		
	 }
}