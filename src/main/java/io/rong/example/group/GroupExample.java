package io.rong.example.group;

import io.rong.RongCloud;
import io.rong.methods.group.Group;
import io.rong.models.Result;
import io.rong.models.group.GroupModel;
import io.rong.models.group.UserGroup;
import io.rong.models.response.GroupUserQueryResult;
import io.rong.models.response.ListGagGroupUserResult;

/**
 * group api的调用示例
 */
public class GroupExample {
	private static final String JSONFILE = GroupExample.class.getClassLoader().getResource("jsonsource").getPath()+"/";
	private static final String appKey = "z3v5yqkbvy9f0";
	private static final String appSecret = "plhr2PA386a";
	private static final RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
	private static final Group Group = rongCloud.group;

	/**
	 * 本地调用测试
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		/**
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/group/group.html#create
		 *
		 * 创建群组方法
		 *
		 */
		String[] members = {"userId1","userid2","userId3"};

		GroupModel group = new GroupModel()
				.setId("groupId")
				.setMerberIds(members)
				.setName("groupName");
		Result groupCreateResult = (Result)Group.create(group);
		System.out.println("group create result:  " + groupCreateResult.toString());

		/**
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/group/group.html#sync
		 *
		 * 	同步用户所属群组方法
		 */

		GroupModel group1 = new GroupModel()
				.setId("groupId1")
				.setName("groupName1");
		GroupModel group2 = new GroupModel()
				.setId("groupId2")
				.setName("groupName2");
		GroupModel[] groups = {group1,group2};
		UserGroup user = new UserGroup()
				.setId("jhkoi90jj")
				.setGroups(groups);

		Result syncResult = (Result)Group.sync(user);
		System.out.println("group sync:  " + syncResult.toString());


		/**
		 *
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/group/group.html#refresh
		 *  刷新群组信息方法
		 */
		String[] memberIds = {"userId2","userid3","userId4"};
		group = new GroupModel()
				.setId("groupId")
				.setMerberIds(memberIds)
				.setName("groupName");
		Result refreshResult = (Result)Group.refresh(group);
		System.out.println("refresh:  " + refreshResult.toString());

		/**
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/group/group.html#join
		 *
		 * 邀请用户加入群组
		 *
		 */
		group = new GroupModel().setId("hdiuj87jj")
				.setMerberIds(memberIds)
				.setName("groupName");
		Result groupInviteResult = (Result)rongCloud.group.invite(group);
		System.out.println("invite:  " + groupInviteResult.toString());

		/**
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/group/group.html#join
		 *
		 * 用户加入指定群组
		 *
		 */
		group = new GroupModel().setId("groupId")
				.setMerberIds(memberIds)
				.setName("groupName");
		Result groupJoinResult = (Result)rongCloud.group.join(group);
		System.out.println("join:  " + groupJoinResult.toString());

		/**
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/group/group.html#getMembers
		 *
		 * 查询群成员方法
		 *
		 */
		group = new GroupModel().setId("figk97h");
		GroupUserQueryResult getMemberesult = rongCloud.group.getMemberList(group);
		System.out.println("group getMember:  " + getMemberesult.toString());

		/**
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/group/group.html#quit
		 *
		 * 退出群组
		 *
		 */
		group = new GroupModel()
				.setId("groupId")
				.setMerberIds(memberIds)
				.setName("groupName");
		Result groupQuitResult = (Result)rongCloud.group.quit(group);
		System.out.println("quit:  " + groupQuitResult.toString());

		/**
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/group/group.html#quit
		 *
		 * 移除群组
		 *
		 */
		group = new GroupModel()
				.setId("groupId")
				.setMerberIds(memberIds)
				.setName("groupName");
		Result kickResult = (Result)rongCloud.group.kick(group);
		System.out.println("kick Result:  " + kickResult.toString());


		/**
		 *
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/group/group.html#dismiss
		 *
		 * 解散群组
		 *
		 */
		Result groupDismissResult = (Result)rongCloud.group.dismiss("hjkl876yh", "hjikkjd97y");
		System.out.println("dismiss:  " + groupDismissResult.toString());

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

		GroupModel group1 = new GroupModel()
				.setId("groupId1")
				.setName("groupName1");
		GroupModel group2 = new GroupModel().setId("groupId2").setName("groupName2");
		GroupModel[] groupSyncGroups = {group1,group2};

		UserGroup user = new UserGroup().setId("jhkoi90jj").setGroups(groupSyncGroups);


		Result result = (Result)rongCloud.group.sync(user);

		System.out.println("sync:  " + result.toString());

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

		GroupModel group = new GroupModel().setId("figk97h");

		GroupUserQueryResult result = rongCloud.group.getMemberList(group);
		System.out.println("groupQueryUser:  " + result.toString());
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



	// 解散群组方法。（将该群解散，所有用户都无法再接收该群的消息。）
	public static void testGroupDismissR(RongCloud rongCloud) throws Exception {
		Result groupDismissResult = (Result)rongCloud.group.dismiss("userId1", "groupId1");
		System.out.println("dismiss:  " + groupDismissResult.toString());
	}

}