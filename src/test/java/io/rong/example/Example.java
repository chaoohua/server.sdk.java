package io.rong.example;

import io.rong.RongCloud;
import io.rong.messages.TxtMessage;
import io.rong.messages.VoiceMessage;
import io.rong.models.Result;
import io.rong.models.group.GroupModel;
import io.rong.models.group.UserGroup;
import io.rong.models.message.*;
import io.rong.models.push.PushMessage;
import io.rong.models.push.UserTag;
import io.rong.models.response.*;
import io.rong.models.sensitiveword.SensitiveWordModel;
import io.rong.models.sms.SmsModel;
import io.rong.models.user.UserModel;
import io.rong.util.GsonUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.Assert.assertEquals;

/**
 * 一些api的调用示例和测试
 */
public class Example {
	private static final String JSONFILE = Example.class.getClassLoader().getResource("jsonsource").getPath()+"/";
	private RongCloud rongCloud ;
	private static final TxtMessage txtMessage = new TxtMessage("hello", "helloExtra");
	private static final VoiceMessage voiceMessage = new VoiceMessage("hello", "helloExtra", 20L);
	private static final String[] targetIds = {"userId2","userid3","userId4"};

	@Before
	public void setUp() throws Exception {
		/*String appKey = "e0x9wycfx7flq";8luwapkv8s7pl
		String appSecret = "STCevzDS6Xy18n";*/
		String appKey = "8luwapkv8s7pl";
		String appSecret = "lmkgpHuXezTjV2";
		//String api = "http://192.168.155.13:9200";
		rongCloud = RongCloud.getInstance(appKey, appSecret);
	}

	/**
	 *
	 * 检查用户在线状态 方法
	 */
	@Test
	public void testCheckOnline() throws Exception {
		UserModel user = new UserModel();
		user.setId("userId");

		CheckOnlineResult result = rongCloud.user.onlineStatus.check(user);

		assertEquals("200",result.getCode().toString());
		System.out.println("checkOnline:  " + result.toString());
	}

	/**
	 *
	 * 用户注册测试
	 */
	@Test
	public void testRegister() throws Exception {

		UserModel user = new UserModel()
				.setId("userId1")
				.setName("username")
				.setPortrait("http://www.rongcloud.cn/images/logo.png");

		TokenResult result = rongCloud.user.register(user);

		System.out.println("getToken:  " + result.toString());
		assertEquals("200",result.getCode().toString());

	}

	/**
	 *  刷新用户信息测试
	 */
	@Test
	public void testUserRefresh() throws Exception {
		UserModel user = new UserModel()
				.setId("userId1")
				.setName("username")
				.setPortrait("http://www.rongcloud.cn/images/logo.png");


		Result result = (ResponseResult)rongCloud.user.refresh(user);
		System.out.println("refresh:  " + result.toString());
		assertEquals("200",result.getCode().toString());


	}
	/**
	 * 测试用户封禁方法（每秒钟限 100 次）
	 */
	@Test
	public void testUserAddBlock() throws Exception {
		UserModel user = new UserModel()
				.setId("hkjo09h")
				.setMinute(1000);
		Result result = (ResponseResult)rongCloud.user.block.add(user);
		System.out.println("userAddBlock:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 * 测试解除用户封禁方法（每秒钟限 100 次）
	 */
	@Test
	public void testUserUnBlock() throws Exception {
		ResponseResult result = (ResponseResult)rongCloud.user.block.remove("userId2");
		System.out.println("unBlock:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  获取被封禁用户方法（每秒钟限 100 次）
	 */
	@Test
	public void testUserQueryBlock() throws Exception {

		BlockUserResult result = (BlockUserResult)rongCloud.user.block.getList();
		System.out.println("queryBlock:  " + result.toString());

		assertEquals("200",result.getCode().toString());


	}
	/**
	 * 添加用户到黑名单方法（每秒钟限 100 次）
	 */
	@Test
	public void testAddBlacklist() throws Exception {

		Result result = (Result)rongCloud.user.blackList.add("userId1", "userId2");
		System.out.println("addBlacklist:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  获取某用户的黑名单列表方法（每秒钟限 100 次）
	 */
	@Test
	public void testGetBlacklist() throws Exception {

		BlackListResult result = rongCloud.user.blackList.getList("userId1");
		System.out.println("queryBlacklist:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *
	 *  从黑名单中移除用户方法（每秒钟限 100 次）
	 */
	@Test
	public void testRemoveBlacklist() throws Exception {
		Result result = (Result)rongCloud.user.blackList.remove("userId1", "userId2");
		System.out.println("removeBlacklist:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 系统消息测试
	 */
	@Test
	public void testSendSystem() throws Exception {
		SystemMessage systemMessage = new SystemMessage()
					.setSenderUserId("usetId")
					.setTargetIds(targetIds)
					.setObjectName(txtMessage.getType())
					.setContent(txtMessage)
					.setPushContent("this is a push")
					.setPushData("{'pushData':'hello'}")
					.setIsPersisted(0)
					.setIsCounted(0)
					.setContentAvailable(0);
		ResponseResult result = rongCloud.message.system.send(systemMessage);
		System.out.println("publishSystem:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 * 发送系统模板消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，
	 * 会话类型为 SYSTEM.每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：
	 * 一次发送 100 人时，示为 100 条消息。）
	 */
	@Test
	public void testSendSystemTemplate() throws Exception {
		Reader reader = null ;
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE+"/message/"+"Template.json"));

			Template template = (Template)GsonUtil.fromJson(reader, Template.class);

			ResponseResult result = rongCloud.message.system.sendTemplate(template);
			System.out.println("sendSystemTemplate:  " + result.toString());

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
	 * 系统消息测试
	 */
	@Test
	public void testSendBroadcast() throws Exception {
		Message message = new BroadcastMessage()
				.setSenderUserId("Hji8yh76")
				.setObjectName(txtMessage.getType())
				.setContent(txtMessage)
				.setPushContent("this is a push")
				.setPushData("{'pushData':'hello'}")
				.setOs("iOS");
		ResponseResult result = rongCloud.message.system.broadcast(message);
		System.out.println("send broadcast:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 * 测试单聊模板消息
	 */
	@Test
	public void testSendPrivateTemplate() throws Exception {
		Reader reader = null ;
		// 发送单聊模板消息方法（一个用户向多个用户发送不同消息内容，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人。）
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE+"/message/"+"Template.json"));
			Template template  =  (Template) GsonUtil.fromJson(reader, Template.class);
			ResponseResult messagePublishTemplateResult = rongCloud.message.aPrivate.sendTemplate(template);
			System.out.println("sendPrivateTemplate:  " + messagePublishTemplateResult.toString());

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
	public void testSendPrivate() throws Exception {
		Reader reader = null ;
		PrivateMessage  privateMessage = new PrivateMessage()
				.setSenderUserId("userId")
				.setTargetIds(targetIds)
				.setObjectName(voiceMessage.getType())
				.setContent(voiceMessage)
				.setPushContent("")
				.setPushData("{\"pushData\":\"hello\"}")
				.setCount("4")
				.setVerifyBlacklist(0)
				.setIsPersisted(0)
				.setIsCounted(0)
				.setIsIncludeSender(0);

		//发送单聊方法
		ResponseResult publishPrivateResult = rongCloud.message.aPrivate.send(privateMessage);
		System.out.println("sendPrivate:  " + publishPrivateResult.toString());

		assertEquals("200",publishPrivateResult.getCode().toString());
	}
	/**
	 * 测试撤回单聊消息
	 * */
	@Test
	public void testRecallPrivate() throws Exception {
		RecallMessage message = new RecallMessage()
				.setSenderUserId("sea9901")
				.setTargetId("markoiwm")
				.setuId("5GSB-RPM1-KP8H-9JHF")
				.setSentTime("1519444243981");
		ResponseResult result = (ResponseResult)rongCloud.message.group.recall(message);

		System.out.println("recall private message:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 * 测试群组消息
	 * */
	@Test
	public void testSendGroup() throws Exception {
		//群组消息
		GroupMessage groupMessage = new GroupMessage()
				.setSenderUserId("Hji8yh76")
				.setTargetIds(targetIds)
				.setObjectName(txtMessage.getType())
				.setContent(txtMessage)
				.setPushContent("this is a push")
				.setPushData("{\"pushData\":\"hello\"}")
				.setIsPersisted(0)
				.setIsCounted(0)
				.setIsIncludeSender(0)
				.setContentAvailable(0);
		ResponseResult result = rongCloud.message.group.send(groupMessage);

		System.out.println("send group:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 测试群组@消息
	 * */
	@Test
	public void testSendGroupMention() throws Exception {

		//要@的人
		String[] userIds = {"Hji8yh76","sea9901"};
		MentionedInfo mentionedInfo = new MentionedInfo(1,userIds,"");

		GroupMentionContent content = new GroupMentionContent(txtMessage,mentionedInfo);

		MentionMessage mentionMessage = new MentionMessage()
				.setSenderUserId("userId")
				.setMentionIds(targetIds)
				.setObjectName(txtMessage.getType())
				.setContent(content)
				.setPushContent("this is a push")
				.setPushData("{\"pushData\":\"hello\"}")
				.setIsPersisted(0)
				.setIsCounted(0)
				.setIsIncludeSender(0)
				.setIsMentioned(0)
				.setContentAvailable(0);
		ResponseResult result = rongCloud.message.group.sendMention(mentionMessage);

		System.out.println("send group:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}
	/**
	 * 测试群组消息
	 * */
	@Test
	public void testRecallGroup() throws Exception {
		RecallMessage message = new RecallMessage()
				.setSenderUserId("sea9901")
				.setTargetId("markoiwm")
				.setuId("5GSB-RPM1-KP8H-9JHF")
				.setSentTime("1519444243981");
		ResponseResult result = (ResponseResult)rongCloud.message.group.recall(message);

		System.out.println("send recall message:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 测试聊天室消息
	 * */
	@Test
	public void testSendChatroom() throws Exception {
		//聊天室消息
		ChatroomMessage message = new ChatroomMessage()
				.setSenderUserId("targetIds")
				.setTargetIds(targetIds)
				.setContent(txtMessage)
				.setObjectName(txtMessage.getType());
		ResponseResult result = rongCloud.message.chatroom.send(message);
		System.out.println("publishChatroomPrivate:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 为用户打标签测试
	 */
	@Test
	public void testSetUserTag() throws Exception {
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
	 * 广播push测试fromuserid 和 message为null即为不落地的push）
	 */
	@Test
	public void testSendPush() throws Exception {
		Reader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream(JSONFILE + "PushMessage.json"));
			PushMessage broadcastPushPushMessage = (PushMessage) GsonUtil.fromJson(reader, PushMessage.class);

			ResponseResult result = rongCloud.push.send(broadcastPushPushMessage);

			System.out.println("broadcastPush:  " + result.toString());

			assertEquals("200",result.getCode().toString());

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
		SensitiveWordModel sentiveWord = new SensitiveWordModel()
				.setType(0)
				.setKeyWord("黄赌毒")
				.setReplace("***");
		ResponseResult result = rongCloud.sensitiveword.add(sentiveWord);
		System.out.println("add:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 查询敏感词列表方法
	 * */
	@Test
	public void testGetListSensitiveword() throws Exception {

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
	 * 短信通知消息
	 * */
	@Test
	public void testSendSmsNotify() throws Exception {
		SmsModel sms =new SmsModel()
				.setMobile("13500000000")
				.setRegion("86")
				.setTemplateId("dsfdsfd")
				.setVerifyCode("1408706337")
				.setVerifyId("1408706337");

		SMSSendCodeResult result = rongCloud.sms.notify.send(sms,"aa","bb","cc");
		System.out.println("notify:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}
	/**
	 * 图片验证码方法
	 * */
	@Test
	public void testGetImageCode() throws Exception {

		SMSImageCodeResult result = rongCloud.sms.code.getImage("app-key");
		System.out.println("getImageCode:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 发送短信验证码方法
	 * */
	@Test
	public void testSendCode() throws Exception {
		SmsModel sms =new SmsModel()
				.setMobile("13500000000")
				.setRegion("86")
				.setTemplateId("dsfdsfd")
				.setVerifyCode("1408706337")
				.setVerifyId("1408706337");
		SMSSendCodeResult result = rongCloud.sms.code.send(sms);
		System.out.println("sendCode:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 验证码验证方法
	 * */
	@Test
	public void testVerifyCode() throws Exception {

		SMSVerifyCodeResult result = rongCloud.sms.code.verify("2312312", "2312312");
		System.out.println("verifyCode:  " + result.toString());
		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 创建群组方法（创建群组，并将用户加入该群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，
	 * 每个群最大至 3000 人，App 内的群组数量没有限制.注：其实本方法是加入群组方法 /group/join 的别名。）
	 */
	@Test
	public void testGroupCreate() throws Exception {

		String[] members = {"userId1","userid2","userId3"};

		GroupModel group = new GroupModel()
				.setId("groupId")
				.setMerberIds(members)
				.setName("groupName");
		Result result = (Result)rongCloud.group.create(group);
		System.out.println("group create result:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 * 	同步用户所属群组方法测试（当第一次连接融云服务器时，需要向融云服务器提交 userId 对应的用户当前所加入的所有群组，
	 * 	此接口主要为防止应用中用户群信息同融云已知的用户所属群信息不同步。）
	 */
	@Test
	public  void testGroupSync() throws Exception {

		GroupModel group1 = new GroupModel().setId("groupId1").setName("groupName1");
		GroupModel group2 = new GroupModel().setId("groupId2").setName("groupName2");
		GroupModel[] groupSyncGroups = {group1,group2};

		UserGroup user = new UserGroup();
		user.setGroups(groupSyncGroups);
		user.setId("jhkoi90jj");

		Result result = (Result)rongCloud.group.sync(user);

		System.out.println("sync:  " + result.toString());
		assertEquals("200",result.getCode().toString());

	}

	/**
	 *  刷新群组信息方法测试
	 */
	@Test
	public void testGroupRefresh() throws Exception {

		String[] memberIds = {"he4kfpk","he4kfp2","he4kfp4"};
		GroupModel group = new GroupModel()
				.setId("hiuyr743k")
				.setMerberIds(memberIds)
				.setName("RongCloud");
		Result result = (Result)rongCloud.group.refresh(group);
		System.out.println("refresh:  " + result.toString());

		assertEquals("200",result.getCode().toString());
	}

	/**
	 * 将用户加入指定群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人。
	 */
	@Test
	public void testGroupJoin() throws Exception {
		String[] memberIds = {"userId2","userid3","userId4"};
		GroupModel group = new GroupModel()
				.setId("hgir769ll")
				.setMerberIds(memberIds)
				.setName("RongClooud");
		Result result = (Result)rongCloud.group.join(group);
		System.out.println("join:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  查询群成员方法
	 */
	@Test
	public  void testGroupQueryUser() throws Exception {

		GroupModel group = new GroupModel().setId("figk97h");

		GroupUserQueryResult result = rongCloud.group.getMemberList(group);
		System.out.println("groupQueryUser:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  退出群组方法测试（将用户从群中移除，不再接收该群组的消息.）
	 */
	@Test
	public void testGroupQuit() throws Exception {

		String[] memberIds = {"userId2","userid3","userId4"};
		GroupModel group = new GroupModel()
				.setId("groupId")
				.setMerberIds(memberIds)
				.setName("groupName");
		Result result = (Result)rongCloud.group.quit(group);
		System.out.println("quit:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  添加禁言群成员方法测试
	 */
	@Test
	public void testGroupAddGagUser() throws Exception {

		String[] memberIds = {"userId1","userid2","userId3"};
		GroupModel group = new GroupModel()
				.setId("lmkgpHuXezTjV2")
				.setMerberIds(memberIds)
				.setMunite(5);
		Result result = (Result)rongCloud.group.gag.add(group);
		System.out.println("group.gag.add:  " + result.toString());

		assertEquals("200",result.getCode().toString());


	}
	/**
	 *  查询被禁言群成员方法
	 */
	@Test
	public void testGroupLisGagUser() throws Exception {
		ListGagGroupUserResult result = rongCloud.group.gag.getList("25");
		System.out.println("group.gag.getList:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  移除禁言群成员方法
	 */
	@Test
	public void testGroupRollBackGagUser() throws Exception {
		String[] memberIds = {"userId2","userid3","userId4"};
		GroupModel group = new GroupModel()
				.setMerberIds(memberIds)
				.setId("jhgui85hh");

		ResponseResult result = (ResponseResult)rongCloud.group.gag.remove(group);
		System.out.println("group.gag.remove:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
	/**
	 *  解散群组方法
	 */
	@Test
	public void testGroupDismissResult() throws Exception {
		Result result = (Result)rongCloud.group.dismiss("hjUkjik11", "hjUkjik");
		System.out.println("groupDismissResult:  " + result.toString());

		assertEquals("200",result.getCode().toString());

	}
}