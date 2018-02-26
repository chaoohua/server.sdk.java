package io.rong.example.group;

import io.rong.RongCloud;
import io.rong.models.Result;
import io.rong.models.group.GroupModel;
import io.rong.models.response.GroupUserQueryResult;
import io.rong.models.response.ListGagGroupUserResult;

/**
 * group api的调用示例
 */
public class GroupExample {
	private static final String JSONFILE = GroupExample.class.getClassLoader().getResource("jsonsource").getPath()+"/";
	/**
	 * 本地调用测试
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		/*String appKey = "appkey";//替换成您的appkeypwe86ga5pilt6

		String appSecret = "secret";//替换成匹配上面key的secret ngx3lbOishj1R

		String api = "http://api.cn.ronghub.com";*/

		String appKey = "pwe86ga5pilt6";//替换成您的appkey

		String appSecret = "ngx3lbOishj1R";//替换成匹配上面key的secret

		String api = "http://api.cn.ronghub.com";

/*
		String appKey = "e0x9wycfx7flq";
		String appSecret = "STCevzDS6Xy18n";
		String api = "http://192.168.155.13:9200";*/
		
		RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

		//RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

		String[] groupCreateUserId = {"userId1","userid2","userId3"};
		System.out.println("************************Group********************");

		/**
		 *  创建群组
		 */
		//groupCreate(rongCloud);

		/**
		 *  同步用户所属群组方
		 */
		//groupSync(rongCloud);

		/**
		 *   刷新群组信息方
		 */
		//groupRefresh(rongCloud);

		/**
		 *  将用户加入指定群组，
		 */
		//testGroupJoin(rongCloud);

		/**
		 *  查询群成员方法
		 */
		testGroupQueryUser(rongCloud);

		/**
		 *  退出群组
		 */
		//groupQuit(rongCloud);

		/**
		 *  添加禁言群成员方
		 */
		//groupAddGagUser(rongCloud);

		/**
		 *  查询被禁言群成员
		 */
		testGroupLisGagUser(rongCloud);

		/**
		 *  移除禁言群成员方法
		 */
		//groupRollBackGagUser(rongCloud);

		/**
		 *  解散群组方法
		 */
		//groupDismissR(rongCloud);

	 }


	/**
	 * 创建群组方法（创建群组，并将用户加入该群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，
	 * 每个群最大至 3000 人，App 内的群组数量没有限制.注：其实本方法是加入群组方法 /group/join 的别名。）
	 */
	public static void testGroupCreate(RongCloud rongCloud) throws Exception {

		 String[] members = {"userId1","userid2","userId3"};

		 GroupModel groupInfo = new GroupModel().setId("groupId")
				 .setMerberIds(members)
				 .setName("groupName");
		 Result groupCreateResult = (Result)rongCloud.group.create(groupInfo);
		 System.out.println("create result:  " + groupCreateResult.toString());
		 return;
	 }


	/**
	 * 	同步用户所属群组方法（当第一次连接融云服务器时，需要向融云服务器提交 userId 对应的用户当前所加入的所有群组，
	 * 	此接口主要为防止应用中用户群信息同融云已知的用户所属群信息不同步。）
	 */
	public static void testGroupSync(RongCloud rongCloud) throws Exception {

		GroupModel group1 = new GroupModel().setId("groupId1").setName("groupName1");
		GroupModel group2 = new GroupModel().setId("groupId2").setName("groupName2");
		GroupModel[] groupSyncGroups = {group1,group2};

		Result groupSyncResult = (Result)rongCloud.group.sync("userId1", groupSyncGroups);

		System.out.println("sync:  " + groupSyncResult.toString());
	 }

	/**
	 *  刷新群组信息方法
	 */
	 public static void testroupRefresh(RongCloud rongCloud) throws Exception {

		 String[] memberIds = {"userId2","userid3","userId4"};
		 GroupModel group = new GroupModel().setId("groupId")
				 .setMerberIds(memberIds)
				 .setName("groupName");
		 Result result = (Result)rongCloud.group.refresh(group);
		 System.out.println("refresh:  " + result.toString());

	 }


	/**
	 * 将用户加入指定群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人。
	 */
	public static void testGroupJoin(RongCloud rongCloud) throws Exception {
		System.out.println("************************Group********************");
		String[] memberIds = {"userId2","userid3","userId4"};
		GroupModel group = new GroupModel().setId("groupId")
				.setMerberIds(memberIds)
				.setName("groupName");
		Result groupJoinResult = (Result)rongCloud.group.join(group);
		System.out.println("join:  " + groupJoinResult.toString());
	}
	// 查询群成员方法
	public static void testGroupQueryUser(RongCloud rongCloud) throws Exception {

		GroupUserQueryResult groupQueryUserResult = rongCloud.group.getMemberList("25");
		System.out.println("queryUser:  " + groupQueryUserResult.toString());
	}

	// 退出群组方法（将用户从群中移除，不再接收该群组的消息.）
	public static void testGroupQuit(RongCloud rongCloud) throws Exception {

		String[] memberIds = {"userId2","userid3","userId4"};
		GroupModel group = new GroupModel().setId("groupId")
				.setMerberIds(memberIds)
				.setName("groupName");
		Result groupQuitResult = (Result)rongCloud.group.quit(group);
		System.out.println("quit:  " + groupQuitResult.toString());
	}

	// 添加禁言群成员方法（在 App 中如果不想让某一用户在群中发言时，可将此用户在群组中禁言，被禁言用户可以接收查看群组中用户聊天信息，但不能发送消息。）
	public static void testGroupAddGagUser(RongCloud rongCloud) throws Exception {

		String[] memberIds = {"userId1","userid2","userId3"};
		GroupModel group = new GroupModel().setMerberIds(memberIds);
		String munite = "1";

		Result groupAddGagUserResult = (Result)rongCloud.group.gag.add(group, "1");
		System.out.println("group.gag.add(:  " + groupAddGagUserResult.toString());

	}

	// 查询被禁言群成员方法
	public static void testGroupLisGagUser(RongCloud rongCloud) throws Exception {
		ListGagGroupUserResult groupLisGagUserResult = rongCloud.group.gag.getList("25");
		System.out.println("group.gag.getList:  " + groupLisGagUserResult.toString());
	}

	// 移除禁言群成员方法
	public static void testGroupRollBackGagUser(RongCloud rongCloud) throws Exception {
		String[] memberIds = {"userId2","userid3","userId4"};
		GroupModel group = new GroupModel().setMerberIds(memberIds);

		Result groupRollBackGagUserResult = (Result)rongCloud.group.gag.remove(group);
		System.out.println("group.gag.remove:  " + groupRollBackGagUserResult.toString());
	}

	// 解散群组方法。（将该群解散，所有用户都无法再接收该群的消息。）
	public static void testGroupDismissR(RongCloud rongCloud) throws Exception {
		Result groupDismissResult = (Result)rongCloud.group.dismiss("userId1", "groupId1");
		System.out.println("dismiss:  " + groupDismissResult.toString());
	}

}