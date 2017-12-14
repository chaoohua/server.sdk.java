package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.models.*;
import io.rong.models.chatroom.*;
import io.rong.models.chatroom.ChatRoom;
import io.rong.models.user.UserInfo;
import io.rong.models.user.UserResponseResult;

import java.io.Reader;

public class ChatroomExample {
    public ChatroomExample() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        String appKey = "appkey";//替换成您的appkey
        String appSecret = "secret";//替换成匹配上面key的secret

        Reader reader = null;
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);


        System.out.println("************************Chatroom********************");
        // 创建聊天室方法
        ChatRoom[] chatroomCreateChatRoomInfo = {new ChatRoom("chatroomId1", "chatroomName1"), new ChatRoom("chatroomId2", "chatroomName2"), new ChatRoom("chatroomId3", "chatroomName3")};
        CodeSuccessResult chatroomCreateResult = rongCloud.chatroom.create(chatroomCreateChatRoomInfo);
        System.out.println("create:  " + chatroomCreateResult.toString());

        // 加入聊天室方法
        /*
        String[] chatroomJoinUserId = {"userId2","userid3","userId4"};
		CodeSuccessResult chatroomJoinResult = rongCloud.chatroom.join(chatroomJoinUserId, "chatroomId1");
		System.out.println("join:  " + chatroomJoinResult.toString());
		*/

        // 查询聊天室信息方法
        String[] chatroomQueryChatroomId = {"chatroomId1", "chatroomId2", "chatroomId3"};
        ChatroomQueryResult chatroomQueryResult = rongCloud.chatroom.query(chatroomQueryChatroomId);
        System.out.println("query:  " + chatroomQueryResult.toString());

        // 查询聊天室内用户方法
        ChatroomUserQueryResult chatroomQueryUserResult = rongCloud.chatroom.queryUser("chatroomId1", "500", "2");
        System.out.println("queryUser:  " + chatroomQueryUserResult.toString());

        // 聊天室消息停止分发方法（可实现控制对聊天室中消息是否进行分发，停止分发后聊天室中用户发送的消息，融云服务端不会再将消息发送给聊天室中其他用户。）
        CodeSuccessResult chatroomStopDistributionMessageResult = rongCloud.chatroom.stopDistributionMessage("chatroomId1");
        System.out.println("stopDistributionMessage:  " + chatroomStopDistributionMessageResult.toString());

        // 聊天室消息恢复分发方法
        CodeSuccessResult chatroomResumeDistributionMessageResult = rongCloud.chatroom.resumeDistributionMessage("chatroomId1");
        System.out.println("resumeDistributionMessage:  " + chatroomResumeDistributionMessageResult.toString());

        // 添加禁言聊天室成员方法（在 App 中如果不想让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.）

        Member[] members = {new Member("userId1", ""), new Member("userId2", "")};
        ChatRoom chatRoom = new ChatRoom("chatroomId1", "chatroomName1", members);

        CodeSuccessResult chatroomAddGagUserResult = rongCloud.chatroom.gag.members.add(chatRoom, "1");
        System.out.println("addGagUser:  " + chatroomAddGagUserResult.toString());

        // 查询被禁言聊天室成员方法
        ListGagChatroomUserResult chatroomListGagUserResult = rongCloud.chatroom.gag.members.getList("chatroomId1");
        System.out.println("ListGagUser:  " + chatroomListGagUserResult.toString());

        // 移除禁言聊天室成员方法
        CodeSuccessResult chatroomRollbackGagUserResult = rongCloud.chatroom.gag.members.remove(chatRoom);
        System.out.println("rollbackGagUser:  " + chatroomRollbackGagUserResult.toString());


        // 添加封禁聊天室成员方法
        CodeSuccessResult chatroomAddBlockUserResult = rongCloud.chatroom.block.add(chatRoom, "1");
        System.out.println("addBlockUser:  " + chatroomAddBlockUserResult.toString());

        // 查询被封禁聊天室成员方法
        ListBlockChatroomUserResult chatroomGetListBlockUserResult = rongCloud.chatroom.block.getList("chatroomId1");
        System.out.println("getListBlockUser:  " + chatroomGetListBlockUserResult.toString());

        // 移除封禁聊天室成员方法
        CodeSuccessResult chatroomRollbackBlockUserResult = rongCloud.chatroom.block.remove(chatRoom);
        System.out.println("rollbackBlockUser:  " + chatroomRollbackBlockUserResult.toString());

        // 添加聊天室消息优先级方法
        String[] chatroomAddPriorityObjectName = {"RC:VcMsg", "RC:ImgTextMsg", "RC:ImgMsg"};
        CodeSuccessResult chatroomAddPriorityResult = rongCloud.chatroom.priority.add(chatroomAddPriorityObjectName);
        System.out.println("addPriority:  " + chatroomAddPriorityResult.toString());

        // 销毁聊天室方法
        String[] chatroomDestroyChatroomId = {"chatroomId", "chatroomId1", "chatroomId2"};
        CodeSuccessResult chatroomDestroyResult = rongCloud.chatroom.priority.destroy(chatroomDestroyChatroomId);
        System.out.println("destroy:  " + chatroomDestroyResult.toString());

        // 添加聊天室白名单成员方法
        String[] chatroomAddWhiteListUserUserId = {"userId1", "userId2", "userId3", "userId4", "userId5"};
        CodeSuccessResult chatroomAddWhiteListUserResult = rongCloud.chatroom.whiteList.add("chatroomId", chatroomAddWhiteListUserUserId);
        System.out.println("addWhiteListUser:  " + chatroomAddWhiteListUserResult.toString());

    }

    /**
     * 创建聊天室方法
     */
    public static void createChatRoom(RongCloud rongCloud) throws Exception {

        ChatRoom[] chatroomCreateChatRoomInfo = {new ChatRoom("chatroomId1", "chatroomName1"), new ChatRoom("chatroomId2", "chatroomName2"), new ChatRoom("chatroomId3", "chatroomName3")};

        CodeSuccessResult chatroomCreateResult = rongCloud.chatroom.create(chatroomCreateChatRoomInfo);
        System.out.println("create:  " + chatroomCreateResult.toString());

    }

    /**
     * 查询聊天室信息方法
     */
    public static void queryChatroomInfo(RongCloud rongCloud) throws Exception {
        String[] chatroomQueryChatroomId = {"chatroomId1", "chatroomId2", "chatroomId3"};

        ChatroomQueryResult chatroomQueryResult = rongCloud.chatroom.query(chatroomQueryChatroomId);

        System.out.println("query:  " + chatroomQueryResult.toString());
    }

    /**
     * 聊天室消息停止分发方法（可实现控制对聊天室中消息是否进行分发，停止分发后聊天室中用户发送的消息，
     * 融云服务端不会再将消息发送给聊天室中其他用户。）
     */
    public static void stopDistributionMessage(RongCloud rongCloud) throws Exception {

        CodeSuccessResult chatroomStopDistributionMessageResult = rongCloud.chatroom.stopDistributionMessage("chatroomId1");

        System.out.println("stopDistributionMessage:  " + chatroomStopDistributionMessageResult.toString());

    }

    /**
     * 聊天室消息恢复分发方法（每秒钟限 100 次）
     */
    public static void resumeDistributionMessage(RongCloud rongCloud) throws Exception {

        CodeSuccessResult chatroomResumeDistributionMessageResult = rongCloud.chatroom.resumeDistributionMessage("chatroomId1");
        System.out.println("resumeDistributionMessage:  " + chatroomResumeDistributionMessageResult.toString());

    }

    /**
     * 添加禁言聊天室成员方法（在 App 中如果不想让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，
     * 被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.）获取某用户的黑名单列表方法（每秒钟限 100 次）
     */
    public static void addGag(RongCloud rongCloud) throws Exception {

        Member[] members = {new Member("userId1", ""), new Member("userId2", "")};
        ChatRoom chatRoom = new ChatRoom("chatroomId1", "chatroomName1", members);
        CodeSuccessResult chatroomAddGagUserResult = rongCloud.chatroom.gag.members.add(chatRoom, "1");
        System.out.println("addGagUser:  " + chatroomAddGagUserResult.toString());
    }

    /**
     * 查询被禁言聊天室成员方法
     */
    public static void getGagMembersList(RongCloud rongCloud) throws Exception {

        ListGagChatroomUserResult chatroomListGagUserResult = rongCloud.chatroom.gag.members.getList("chatroomId1");
        System.out.println("ListGagUser:  " + chatroomListGagUserResult.toString());
    }

    /**
     * 移除禁言聊天室成员
     */
    public static void removeMembersGag(RongCloud rongCloud) throws Exception {

        CodeSuccessResult chatroomRollbackGagUserResult = rongCloud.chatroom.gag.members.remove(chatRoom);
        System.out.println("rollbackGagUser:  " + chatroomRollbackGagUserResult.toString());

    }
}
