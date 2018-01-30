package io.rong.example;

import io.rong.RongCloud;
import io.rong.messages.TxtMessage;
import io.rong.messages.VoiceMessage;
import io.rong.models.TemplateMessage;
import io.rong.models.message.ChatroomMessage;
import io.rong.models.message.GroupMessage;
import io.rong.models.message.PrivateMessage;
import io.rong.models.message.SystemMessage;
import io.rong.models.push.PushMessage;
import io.rong.models.push.UserTag;
import io.rong.models.response.*;
import io.rong.models.sms.SmsModel;
import io.rong.util.GsonUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.Assert.assertEquals;

/**
 * 一些api的调用示例
 */
public class Example {
	private static final String JSONFILE = Example.class.getClassLoader().getResource("jsonsource").getPath()+"/";
	private RongCloud rongCloud ;
	private static final TxtMessage txtMessage = new TxtMessage("hello", "helloExtra");
	private static final VoiceMessage voiceMessage = new VoiceMessage("hello", "helloExtra", 20L);
	SmsModel sms = new SmsModel("13500000000", "dsfdsfd", "86", "1408706337", "1408706337");

	@Before
	public void setUp() throws Exception {
		String appKey = "e0x9wycfx7flq";
		String appSecret = "STCevzDS6Xy18n";
		String api = "http://192.168.155.13:9200";
		rongCloud = RongCloud.getInstance(appKey, appSecret,api);
	}
	/**
	 * 系统模版消息测试
	 */
	@Test
	public void testPublishSystem() throws Exception {
		String[] targetIds = {"userId2","userid3","userId4"};
		//系统消息方法
		SystemMessage systemMessage = new SystemMessage("userId1", targetIds,txtMessage.getType(),txtMessage,"thisisapush",
				"{\"pushData\":\"hello\"}", 0, 0,0);
		ResponseResult result = rongCloud.message.system.publish(systemMessage);
		System.out.println("publishSystem:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 * 系统消息测试
	 */
	@Test
	public void testPublishSystemTemplate() throws Exception {
		Reader reader = null ;
		// 发送系统模板消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM.每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。）
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE+"TemplateMessage.json"));
			TemplateMessage publishSystemTemplateTemplateMessage  =  (TemplateMessage)GsonUtil.fromJson(reader, TemplateMessage.class);
			ResponseResult result = rongCloud.message.system.publishTemplate(publishSystemTemplateTemplateMessage);
			System.out.println("publishSystemTemplate:  " + result.toString());

			assertEquals("200",result.getCode().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != reader){
				reader.close();
			}
		}
	}
	/**
	 * 测试单聊模板消息
	 */
	@Test
	public void testPublishPrivateTemplate() throws Exception {
		Reader reader = null ;
		// 发送单聊模板消息方法（一个用户向多个用户发送不同消息内容，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人。）
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE+"TemplateMessage.json"));
			TemplateMessage publishTemplateTemplateMessage  =  (TemplateMessage) GsonUtil.fromJson(reader, TemplateMessage.class);
			ResponseResult messagePublishTemplateResult = rongCloud.message.aPrivate.publishTemplate(publishTemplateTemplateMessage);
			System.out.println("publishPrivateTemplate:  " + messagePublishTemplateResult.toString());

			assertEquals("200",messagePublishTemplateResult.getCode().toString());

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != reader){
				reader.close();
			}
		}
	}
	/**
	 * 测试单聊消息
	 * */
	@Test
	public void testPublishPrivate() throws Exception {
		Reader reader = null ;
		String[] targetIds = {"userId2","userid3","userId4"};
		VoiceMessage voiceMessage = new VoiceMessage("hello", "helloExtra", 20L);
		PrivateMessage privateMessage = new PrivateMessage("userId1", targetIds, voiceMessage.getType(),voiceMessage, "thisisapush",
				"{\"pushData\":\"hello\"}", "4", 0, 0, 0, 0);
		//发送单聊方法
		ResponseResult publishPrivateResult = rongCloud.message.aPrivate.publish(privateMessage);
		System.out.println("publishPrivate:  " + publishPrivateResult.toString());

		assertEquals("200",publishPrivateResult.getCode().toString());
	}
	/**
	 * 测试群组消息
	 * */
	@Test
	public void testPublishGroup() throws Exception {
		//群组消息
		String[] targetIds = {"groupId2","groupId3","groupId4"};
		GroupMessage groupMessage = new GroupMessage("userId1", targetIds,txtMessage.getType(), txtMessage,"thisisapush",
				"{\"pushData\":\"hello\"}", 0, 0,0,0,0);
		ResponseResult result = rongCloud.message.group.publish(groupMessage);
		System.out.println("publishGroup:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 测试聊天室消息
	 * */
	@Test
	public void testPublishChatroomPrivate() throws Exception {
		//聊天室消息
		TxtMessage txtMessage = new TxtMessage("hello", "helloExtra");
		String[] targetIds2 = {"chatroomId2","chatroomId3","chatroomId4"};
		ChatroomMessage message = new ChatroomMessage("userId1",targetIds2,txtMessage.getType(),txtMessage);
		ResponseResult result = rongCloud.message.chatroom.publish(message);
		System.out.println("publishChatroomPrivate:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 为用户打标签测试
	 */
	@Test
	public void test_setUserTag() throws Exception {
		Reader reader = null;
		// 添加 Push 标签方法
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE + "UserTag.json"));
			UserTag setUserPushTagUserTag = (UserTag) GsonUtil.fromJson(reader, UserTag.class);
			ResponseResult pushSetUserPushTagResult = rongCloud.push.setUserTag(setUserPushTagUserTag);

			assertEquals("200",pushSetUserPushTagResult.getCode().toString());
			System.out.println("setUserPushTag:  " + pushSetUserPushTagResult.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				reader.close();
			}
		}

	}

	/**
	 * 广播push测试
	 */
	@Test
	public void test_send() throws Exception {
		Reader reader = null;
		// 广播消息方法（fromuserid 和 message为null即为不落地的push）
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE + "PushMessage.json"));
			PushMessage broadcastPushPushMessage = (PushMessage) GsonUtil.fromJson(reader, PushMessage.class);

			ResponseResult pushBroadcastPushResult = rongCloud.push.send(broadcastPushPushMessage);

			System.out.println("broadcastPush:  " + pushBroadcastPushResult.toString());

			assertEquals("200",pushBroadcastPushResult.getCode().toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				reader.close();
			}
		}
	}

	/**
	 * 添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。）
	 * */
	@Test
	public void testAddSensitiveword() throws Exception {

		ResponseResult result = rongCloud.sensitiveword.add(
									"money",
									"****");
		System.out.println("add:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 查询敏感词列表方法
	 * */
	@Test
	public void testGetListSensitiveword() throws Exception {

		//
		ListWordfilterResult result = rongCloud.sensitiveword.getList(1);
		System.out.println("getList:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 移除敏感词方法（从敏感词列表中，移除某一敏感词。）
	 * */
	@Test
	public void testDeleteSensitiveword() throws Exception {

		ResponseResult result = rongCloud.sensitiveword.remove("money");
		System.out.println("SensitivewordDelete:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 批量移除敏感词方法（从敏感词列表中，批量移除某些敏感词，一次最多移除敏感词不超过 50 个，移除后 2 小时内生效.）
	 * */
	@Test
	public void testGetImageCode() throws Exception {
		// 获取图片验证码方法
		SMSImageCodeResult result = rongCloud.sms.code.getImage("app-key");
		System.out.println("getImageCode:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 批量移除敏感词方法（从敏感词列表中，批量移除某些敏感词，一次最多移除敏感词不超过 50 个，移除后 2 小时内生效.）
	 * */
	@Test
	public void testSendCode() throws Exception {
		// 发送短信验证码方法。
		SMSSendCodeResult result = rongCloud.sms.code.send(sms);
		System.out.println("sendCode:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 批量移除敏感词方法（从敏感词列表中，批量移除某些敏感词，一次最多移除敏感词不超过 50 个，移除后 2 小时内生效.）
	 * */
	@Test
	public void testVerifyCode() throws Exception {
		// 验证码验证方法
		SMSVerifyCodeResult result = rongCloud.sms.code.verify("2312312", "2312312");
		System.out.println("verifyCode:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}


	/**
	 * 批量移除敏感词方法（从敏感词列表中，批量移除某些敏感词，一次最多移除敏感词不超过 50 个，移除后 2 小时内生效.）
	 * */
	@Test
	public void testBatchDeleteSensitiveword() throws Exception {
		// 验证码验证方法
		SMSSendCodeResult result = rongCloud.sms.notify.send(sms,"aa","bb","cc");
		System.out.println("notify:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}




}