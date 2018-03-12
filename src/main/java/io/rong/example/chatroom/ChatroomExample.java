package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.Chatroom;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.*;
import java.io.Reader;

public class ChatroomExample {
       /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "8luwapkv8s7pl";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "lmkgpHuXezTjV2";

    public static void main(String[] args) throws Exception {
       /* String appKey = "z3v5yqkbvy9f0";
        String appSecret = "plhr2PA386a";*/
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        Chatroom chatroom = rongCloud.chatroom;

        Reader reader = null;
        /**
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/chatroom/chatroom.html#create
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
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/chatroom/chatroom.html#destory
         * 销毁聊天室
         *
         * */
        String[] chatroomId = {"chatroomId3", "chatroomId2"};

        ResponseResult chatroomDestroyResult = chatroom.destroy(chatroomId);
        System.out.println("destroy:  " + chatroomDestroyResult.toString());


        /**
         *
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/chatroom/chatroom.html#get
         * 查询聊天室信息demo
         * */

        String[] queryChatroomId = {"d7ec7a8b8d8546c98b0973417209a548"};
        ChatroomQueryResult chatroomQueryResult = rongCloud.chatroom.get(queryChatroomId);
        System.out.println("query:  " + chatroomQueryResult.toString());

        /**
         *
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/chatroom/chatroom.html#getMembers
         * 查询聊天室成员demo
         *
         * */

        ChatroomModel quertCatroom = new ChatroomModel()
                .setId("d7ec7a8b8d8546c98b0973417209a548")
                .setCount(500)
                .setOrder(1);

        ChatroomUserQueryResult chatroomQueryUserResult = chatroom.getMembers(quertCatroom);
        System.out.println("queryUser:  " + chatroomQueryUserResult.toString());

        /**
         *
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/chatroom/chatroom.html#isExist
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
}
