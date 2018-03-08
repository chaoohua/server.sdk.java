package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.Chatroom;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.*;
import java.io.Reader;

public class ChatroomExample {

    private static final String appKey = "z3v5yqkbvy9f0";
    private static final String appSecret = "plhr2PA386a";
    private static final RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
    private static final Chatroom chatroom = rongCloud.chatroom;

    public static void main(String[] args) throws Exception {
        //String appKey = "appkey";//替换成您的appkey
        //String appSecret = "secret";//替换成匹配上面key的secret
        String appKey = "z3v5yqkbvy9f0";
        String appSecret = "plhr2PA386a";
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        Chatroom chatroom = rongCloud.chatroom;

        Reader reader = null;
        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/chatroom.html#create
         *
         * 创建聊天室
         *
         * */
        ChatroomModel[] chatrooms = {
                new ChatroomModel().setId("chatroomId1").setName("chatroomName1"),
                new ChatroomModel().setId("chatroomId2").setName("chatroomName2")
        };
        ResponseResult result = chatroom.create(chatrooms);

        System.out.println("create:  " + result.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/chatroom.html#destory
         * 销毁聊天室
         *
         * */
        String[] chatroomId = {"chatroomId3", "chatroomId2"};

        ResponseResult chatroomDestroyResult = chatroom.destroy(chatroomId);
        System.out.println("destroy:  " + chatroomDestroyResult.toString());


        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/chatroom.html#get
         * 查询聊天室信息demo
         * */

        String[] queryChatroomId = {"d7ec7a8b8d8546c98b0973417209a548"};
        ChatroomQueryResult chatroomQueryResult = rongCloud.chatroom.get(queryChatroomId);
        System.out.println("query:  " + chatroomQueryResult.toString());


        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/chatroom.html#getMembers
         * 查询聊天室成员demo
         *
         * */
        getChatroomMembers();


        ChatroomModel quertCatroom = new ChatroomModel()
                .setId("d7ec7a8b8d8546c98b0973417209a548")
                .setCount(500)
                .setOrder(1);

        ChatroomUserQueryResult chatroomQueryUserResult = chatroom.getMembers(quertCatroom);
        System.out.println("queryUser:  " + chatroomQueryUserResult.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/chatroom.html#isExist
         * 查询聊天室成员是否存在
         *
         * */
        ChatroomMember member = new ChatroomMember()
                .setId("137385")
                .setChatroomId("d7ec7a8b8d8546c98b0973417209a548")
                .setTime("8888");

        CheckChatRoomUserResult checkMemberResult = chatroom.isExist(member);
        System.out.println("checkChatroomUserResult:  " + checkMemberResult.isInChrm);


    }

    /**
     * 创建聊天室方法
     */
    public static void testCreateChatRoom() throws Exception {

        ChatroomModel[] chatroomCreateChatRoomInfo = {
                new ChatroomModel().setId("chatroomId1").setName("chatroomName1"),
                new ChatroomModel().setId("chatroomId2").setName("chatroomName2")
        };

        ResponseResult result = rongCloud.chatroom.create(chatroomCreateChatRoomInfo);
        System.out.println("create:  " + result.toString());

    }

    /**
     *
     * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/chatroom.html#get
     * 查询聊天室信息demo
     *
     */
    public static void testQueryChatroomInfo() throws Exception {
        String[] chatroomQueryChatroomId = {"d7ec7a8b8d8546c98b0973417209a548"};

        ChatroomQueryResult chatroomQueryResult = rongCloud.chatroom.get(chatroomQueryChatroomId);

        System.out.println("query:  " + chatroomQueryResult.toString());
    }
    /**
     * 查询聊天室内用户demo
     * */
    public static void getChatroomMembers() throws Exception {

        ChatroomModel chatroom = new ChatroomModel()
                .setId("d7ec7a8b8d8546c98b0973417209a548")
                .setCount(500)
                .setOrder(1);

        ChatroomUserQueryResult chatroomQueryUserResult = rongCloud.chatroom.getMembers(chatroom);
        System.out.println("queryUser:  " + chatroomQueryUserResult.toString());
    }

    /**
     * 用户是否存在聊天室
     * */
    public static void testIsExist() throws Exception {

       ChatroomMember member = new ChatroomMember().setId("137385")
               .setChatroomId("d7ec7a8b8d8546c98b0973417209a548")
               .setTime("8888");

        CheckChatRoomUserResult result = rongCloud.chatroom.isExist(member);
        System.out.println("checkChatroomUserResult:  " + result.isInChrm);
    }


    /**
     * 查询被禁言聊天室成员方法
     */
    public static void testGetGagMembersList(RongCloud rongCloud) throws Exception {

        ListGagChatroomUserResult chatroomListGagUserResult = rongCloud.chatroom.gag.members.getList("d7ec7a8b8d8546c98b0973417209a548");
        System.out.println("ListGagUser:  " + chatroomListGagUserResult.toString());
    }

    /**
     * 移除禁言聊天室成员
     */
    public static void testRemoveMembersGag(RongCloud rongCloud) throws Exception {
        String[] memberIds = {"fhgko9i4","qawr34h"};
        ChatroomModel chatroom = new ChatroomModel().setId("chatroomId1")
                .setName("chatroomName1")
                .setMemberIds(memberIds);
        ResponseResult result = rongCloud.chatroom.gag.members.remove(chatroom);
        System.out.println("rollbackGagUser:  " + result.toString());

    }
    /**
     * 查询被封禁聊天室成员方法
     */
    public static void testGetChatroomBlockList(RongCloud rongCloud) throws Exception {
        //
        ListBlockChatroomUserResult result = rongCloud.chatroom.block.getList("chatroomId1");
        System.out.println("getListBlockUser:  " + result.toString());

    }
    /**
     * 添加聊天室白名单成员方法
     */
    public static void testAddChatroomWhiteList(RongCloud rongCloud) throws Exception {

        String[] chatroomAddWhiteListUserUserId = {"userId1", "userId2", "userId3", "userId4", "userId5"};
        ResponseResult result = rongCloud.chatroom.whiteList.add("chatroomId", chatroomAddWhiteListUserUserId);
        System.out.println("addWhiteListUser:  " + result.toString());
    }
}
