package io.rong.example.group;

import io.rong.RongCloud;
import io.rong.methods.group.Group;
import io.rong.models.Result;
import io.rong.models.group.GroupModel;
import io.rong.models.group.UserGroup;
import io.rong.models.response.GroupUserQueryResult;

/**
 *
 * 群组服务示例
 * @author hc
 * @date 2017/12/30
 * @version
 */
public class GroupExample {
	private static final String JSONFILE = GroupExample.class.getClassLoader().getResource("jsonsource").getPath()+"/";
	/**
	 * 此处替换成您的appKey
	 * */
	private static final String appKey = "8luwapkv8s7pl";
	/**
	 * 此处替换成您的appSecret
	 * */
	private static final String appSecret = "lmkgpHuXezTjV2";

	/**
	 * 本地调用测试
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
		Group Group = rongCloud.group;

		/**
		 * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/group/group.html#create
		 *
		 * 创建群组方法
		 *
		 */
		String[] memberIds = {"userId1","userid2","userId3"};

		GroupModel group = new GroupModel()
				.setId("groupId")
				.setMerberIds(memberIds)
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
		memberIds = new String[]{"userId2", "userid3", "userId4"};
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
		group = new GroupModel()
				.setId("hdiuj87jj")
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
		group = new GroupModel()
				.setId("groupId")
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

}