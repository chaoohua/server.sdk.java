package io.rong.example.group;

import io.rong.RongCloud;
import io.rong.models.*;
import io.rong.models.group.GroupModel;
import io.rong.models.group.GroupResponse;
import io.rong.models.group.GroupResponse;
import io.rong.models.group.GroupModel;

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

		String appKey = "appkey";//替换成您的appkey

		String appSecret = "secret";//替换成匹配上面key的secret
		
		RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

		String[] groupCreateUserId = {"userId1","userid2","userId3"};
		System.out.println("************************Group********************");

		/**
		 *  创建群组
		 */
		groupCreate(rongCloud);

		/**
		 *  同步用户所属群组方
		 */
		groupSync(rongCloud);

		/**
		 *   刷新群组信息方
		 */
		groupRefresh(rongCloud);

		/**
		 *  将用户加入指定群组，
		 */
		groupJoin(rongCloud);

		/**
		 *  查询群成员方法
		 */
		groupQueryUser(rongCloud);

		/**
		 *  退出群组
		 */
		groupQuit(rongCloud);

		/**
		 *  添加禁言群成员方
		 */
		groupAddGagUser(rongCloud);

		/**
		 *  查询被禁言群成员
		 */
		groupLisGagUser(rongCloud);

		/**
		 *  移除禁言群成员方法
		 */
		groupRollBackGagUser(rongCloud);

		/**
		 *  解散群组方法
		 */
		groupDismissR(rongCloud);

	 }


	/**
	 * 创建群组方法（创建群组，并将用户加入该群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，
	 * 每个群最大至 3000 人，App 内的群组数量没有限制.注：其实本方法是加入群组方法 /group/join 的别名。）
	 */
	public static void groupCreate(RongCloud rongCloud) throws Exception {

		 String[] groupCreateUserId = {"userId1","userid2","userId3"};

		GroupModel groupUsersParam = new GroupModel("groupId",groupCreateUserId,"groupName1");
		 GroupResponse groupCreateResult = (GroupResponse)rongCloud.group.create(groupUsersParam);
		 System.out.println("create result:  " + groupCreateResult.toString());
		 return;
	 }


	/**
	 * 	同步用户所属群组方法（当第一次连接融云服务器时，需要向融云服务器提交 userId 对应的用户当前所加入的所有群组，
	 * 	此接口主要为防止应用中用户群信息同融云已知的用户所属群信息不同步。）
	 */
	public static void groupSync(RongCloud rongCloud) throws Exception {

		GroupModel[] groupSyncGroupInfo = {new GroupModel("groupId1",null ,"groupName"), new GroupModel("groupId2",null,"groupName2" ), new GroupModel("groupId3",null,"groupName3" )};

		 GroupResponse groupSyncResult = (GroupResponse)rongCloud.group.sync("userId1", groupSyncGroupInfo);

		 System.out.println("sync:  " + groupSyncResult.toString());
	 }

	/**
	 *  刷新群组信息方法
	 */
	 public static void groupRefresh(RongCloud rongCloud) throws Exception {

		 String[] groupCreateUserId = {"userId1","userid2","userId3"};
		 GroupModel group = new GroupModel("groupId",groupCreateUserId,"groupName1");
		 //CodeSuccessResult groupRefreshResult = rongCloud.group.refresh(groupRefreshRaram);
		 //System.out.println("refresh:  " + groupRefreshResult.toString());
		 GroupResponse groupRefreshResult = (GroupResponse)rongCloud.group.refresh(group);
		 System.out.println("refresh:  " + groupRefreshResult.toString());

	 }


	/**
	 *  	// 将用户加入指定群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人。
	 */
	public static void groupJoin(RongCloud rongCloud) throws Exception {
		System.out.println("************************Group********************");
		String[] groupCreateUserId = {"userId1","userid2","userId3"};
		GroupModel group = new GroupModel("",groupCreateUserId,"groupName1");
		GroupResponse groupJoinResult = (GroupResponse)rongCloud.group.join(group);
		System.out.println("join:  " + groupJoinResult.toString());
	}
	// 查询群成员方法
	public static void groupQueryUser(RongCloud rongCloud) throws Exception {

		GroupUserQueryResult groupQueryUserResult = rongCloud.group.getMemberList("groupId1");
		System.out.println("queryUser:  " + groupQueryUserResult.toString());
	}

	// 退出群组方法（将用户从群中移除，不再接收该群组的消息.）
	public static void groupQuit(RongCloud rongCloud) throws Exception {

		String[] memberIds = {"userId2","userid3","userId4"};
		GroupModel group = new GroupModel("",memberIds,"groupName1");
		GroupResponse groupQuitResult = (GroupResponse)rongCloud.group.quit(group);
		System.out.println("quit:  " + groupQuitResult.toString());
	}

	// 添加禁言群成员方法（在 App 中如果不想让某一用户在群中发言时，可将此用户在群组中禁言，被禁言用户可以接收查看群组中用户聊天信息，但不能发送消息。）
	public static void groupAddGagUser(RongCloud rongCloud) throws Exception {
		String[] memberIds = {"userId2","userid3","userId4"};
		GroupModel group = new GroupModel("",memberIds,null);
		GroupResponse groupAddGagUserResult = (GroupResponse)rongCloud.group.gag.add(group, "1");
		System.out.println("addGagUser:  " + groupAddGagUserResult.toString());

	}

	// 查询被禁言群成员方法
	public static void groupLisGagUser(RongCloud rongCloud) throws Exception {
		ListGagGroupUserResult groupLisGagUserResult = rongCloud.group.gag.getList("groupId1");
		System.out.println("lisGagUser:  " + groupLisGagUserResult.toString());
	}

	// 移除禁言群成员方法
	public static void groupRollBackGagUser(RongCloud rongCloud) throws Exception {
		String[] memberIds = {"userId2","userid3","userId4"};
		GroupModel group = new GroupModel("",memberIds,null);
		GroupResponse groupRollBackGagUserResult = (GroupResponse)rongCloud.group.gag.remove(group);
		System.out.println("rollBackGagUser:  " + groupRollBackGagUserResult.toString());
	}

	// 解散群组方法。（将该群解散，所有用户都无法再接收该群的消息。）
	public static void groupDismissR(RongCloud rongCloud) throws Exception {
		GroupResponse groupDismissResult = (GroupResponse)rongCloud.group.dismiss("userId1", "groupId1");
		System.out.println("dismiss:  " + groupDismissResult.toString());
	}

}