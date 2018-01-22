package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.models.*;
import io.rong.models.chatroom.*;
import io.rong.models.chatroom.ChatRoom;

import java.io.Reader;

public class ChatroomExample {
    public static  Member[] members = {new Member("userId1", ""), new Member("userId2", "")};
    public static ChatRoom chatRoom = new ChatRoom("chatroomId1", "chatroomName1", members);

    public static void main(String[] args) throws Exception {
        //String appKey = "appkey";//替换成您的appkey
        //String appSecret = "secret";//替换成匹配上面key的secret
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        /*String appKey = "6tnym1br6cew7";
        String appSecret = "cxf9v1xazAa";*/
        String appKey = "uwd1c0sxdyvm1";
        String appSecret = "6lPjEsAtg6db3";

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        /*String appKey = "e0x9wycfx7flq";
        String appSecret = "STCevzDS6Xy18n";
        String api = "http://192.168.155.13:9200";*/

       // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);
        Reader reader = null;

        /**
         *
         * 创建聊天室demo
         *
         * */
        //createChatRoom(rongCloud);
        /**
         * 销毁聊天室demo
         * */
        String[] chatroomDestroyChatroomId = {"chatroomId3", "chatroomId2"};
        //CodeSuccessResult chatroomDestroyResult = rongCloud.chatroom.destroy(chatroomDestroyChatroomId);
       //System.out.println("destroy:  " + chatroomDestroyResult.toString());


        /**
         * 查询聊天室信息demo
         * */
        queryChatroomInfo(rongCloud);
        /**
         * 查询聊天室成员demo
         * */
        getChatroomMembers(rongCloud);
        /**
         * 查询聊天室成员是否存在
         * */
        isExist(rongCloud);
        /**
         * 聊天室消息停止分发demo（可实现控制对聊天室中消息是否进行分发，停止分发后聊天室中用户发送的消息，融云服务端不会再将消息发送给聊天室中其他用户。）
         *
         * */
        //stopDistributionMessage(rongCloud);

        /**
         * 聊天室消息恢复分发demo
         *
         * */
        //resumeDistributionMessage(rongCloud);

        /**
        *  添加禁言聊天室成员demo（在 App 中如果不想让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.）Member[] members = {new Member("userId1", ""), new Member("userId2", "")};
        *  */
        //addGag(rongCloud);
        /**
         *
         * 查询被禁言聊天室成员demo
         * */
        getGagMembersList(rongCloud);

        /**
         *移除禁言聊天室成员demo
         *
         * */
        //removeMembersGag(rongCloud);

        /**
         *添加封禁聊天室成员demo
         * */
        //addChatroomBlock(rongCloud);

        /**
         * 查询被封禁聊天室成员demo
         *
         * */
        //getChatroomBlockList(rongCloud);

        /**
         * 移除封禁聊天室成员demo
         *
         * */
        //removeChatroomBlock(rongCloud);

        /**
         *
         * 添加聊天室消息优先级demo
         * */
        //addChatroomPriority(rongCloud);

        // 添加聊天室白名单成员方法

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
        String[] chatroomQueryChatroomId = {"de0b2058751896848b67360332c7260c"};

        ChatroomQueryResult chatroomQueryResult = rongCloud.chatroom.query(chatroomQueryChatroomId);

        System.out.println("query:  " + chatroomQueryResult.toString());
    }
    /**
     * 查询聊天室内用户demo
     * */
    public static void getChatroomMembers(RongCloud rongCloud) throws Exception {

        chatRoom = new ChatRoom("chatroomId1", 500, 1);

        ChatroomUserQueryResult chatroomQueryUserResult = rongCloud.chatroom.getMembers(chatRoom);
        System.out.println("queryUser:  " + chatroomQueryUserResult.toString());
    }

    /**
     * 用户是否存在聊天室
     * */
    public static void isExist(RongCloud rongCloud) throws Exception {

        CheckChatRoomUser checkChatroomUserResult = rongCloud.chatroom.isExist(new Member("userId1", "","chatroomId"));
        System.out.println("checkChatroomUserResult:  " + checkChatroomUserResult.isInChrm);
    }

      /**
     * 聊天室消息停止分发方法（可实现控制对聊天室中消息是否进行分发，停止分发后聊天室中用户发送的消息，
     * 融云服务端不会再将消息发送给聊天室中其他用户。）
     */
    public static void stopDistributionMessage(RongCloud rongCloud) throws Exception {

        CodeSuccessResult chatroomStopDistributionMessageResult = rongCloud.chatroom.stopDistribution("chatroomId1");

        System.out.println("stopDistributionMessage:  " + chatroomStopDistributionMessageResult.toString());

    }

    /**
     * 聊天室消息恢复分发方法（每秒钟限 100 次）
     */
    public static void resumeDistributionMessage(RongCloud rongCloud) throws Exception {

        CodeSuccessResult chatroomResumeDistributionMessageResult = rongCloud.chatroom.resumeDistribution("chatroomId1");
        System.out.println("resumeDistributionMessage:  " + chatroomResumeDistributionMessageResult.toString());

    }

    /**
     * 添加禁言聊天室成员方法（在 App 中如果不想让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，
     * 被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.）获取某用户的黑名单列表方法（每秒钟限 100 次）
     */
    public static void addGag(RongCloud rongCloud) throws Exception {

        Member[] members = {new Member("userId1", ""), new Member("userId2", "")};
        ChatRoom chatRoom = new ChatRoom("chatroomId1", "chatroomName1", members);
        CodeSuccessResult chatroomAddGagUserResult = rongCloud.chatroom.gag.members.add(chatRoom, 1);
        System.out.println("addGagUser:  " + chatroomAddGagUserResult.toString());
    }

    /**
     * 查询被禁言聊天室成员方法
     */
    public static void getGagMembersList(RongCloud rongCloud) throws Exception {

        ListGagChatroomUserResult chatroomListGagUserResult = rongCloud.chatroom.gag.members.getList("de0b2058751896848b67360332c7260c");
        System.out.println("ListGagUser:  " + chatroomListGagUserResult.toString());
    }

    /**
     * 移除禁言聊天室成员
     */
    public static void removeMembersGag(RongCloud rongCloud) throws Exception {
        Member[] members = {new Member("userId1", ""), new Member("userId2", "")};
        ChatRoom chatRoom = new ChatRoom("chatroomId1", "chatroomName1", members);
        CodeSuccessResult chatroomRollbackGagUserResult = rongCloud.chatroom.gag.members.remove(chatRoom);
        System.out.println("rollbackGagUser:  " + chatroomRollbackGagUserResult.toString());

    }

    /**
     * 添加封禁聊天室成员方法
     */
    public static void addChatroomBlock(RongCloud rongCloud) throws Exception {
        CodeSuccessResult chatroomAddBlockUserResult = rongCloud.chatroom.block.add(chatRoom, 1);
        System.out.println("addBlockUser:  " + chatroomAddBlockUserResult.toString());

    }

    /**
     * 移除封禁聊天室成员方法
     */
    public static void removeChatroomBlock(RongCloud rongCloud) throws Exception {
        CodeSuccessResult chatroomRollbackBlockUserResult = rongCloud.chatroom.block.remove(chatRoom);
        System.out.println("rollbackBlockUser:  " + chatroomRollbackBlockUserResult.toString());

    }
    /**
     * 查询被封禁聊天室成员方法
     */
    public static void getChatroomBlockList(RongCloud rongCloud) throws Exception {
        //
        ListBlockChatroomUserResult chatroomGetListBlockUserResult = rongCloud.chatroom.block.getList("chatroomId1");
        System.out.println("getListBlockUser:  " + chatroomGetListBlockUserResult.toString());

    }

    /**
     * 添加聊天室消息优先级方法
     */
    public static void addChatroomPriority(RongCloud rongCloud) throws Exception {


        String[] chatroomAddPriorityObjectName = {"RC:VcMsg", "RC:ImgTextMsg", "RC:ImgMsg"};
        CodeSuccessResult chatroomAddPriorityResult = rongCloud.chatroom.priority.add(chatroomAddPriorityObjectName);
        System.out.println("addPriority:  " + chatroomAddPriorityResult.toString());


    }
    /**
     * 添加聊天室白名单成员方法
     */
    public static void addChatroomWhiteList(RongCloud rongCloud) throws Exception {

        String[] chatroomAddWhiteListUserUserId = {"userId1", "userId2", "userId3", "userId4", "userId5"};
        CodeSuccessResult chatroomAddWhiteListUserResult = rongCloud.chatroom.whiteList.add("chatroomId", chatroomAddWhiteListUserUserId);
        System.out.println("addWhiteListUser:  " + chatroomAddWhiteListUserResult.toString());
    }
}
