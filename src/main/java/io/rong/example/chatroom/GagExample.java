package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.gag.Gag;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ListGagChatroomUserResult;
import io.rong.models.response.ResponseResult;

public class GagExample {
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "8luwapkv8s7pl";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "lmkgpHuXezTjV2";

    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        Gag gag = rongCloud.chatroom.gag;

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/gag.html#add
         * 添加禁言聊天室成员方法想（在 App 中如果不让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，
         * 被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.）获取某用户的黑名单列表方法（每秒钟限 100 次）
         */

        String[] memberIds = {"fhgko9i4","qawr34h"};
        ChatroomModel chatroom = new ChatroomModel()
                .setId("hjhf07kk")
                .setMemberIds(memberIds)
                .setMinute(5);
        ResponseResult result = gag.members.add(chatroom);
        System.out.println("addGagUser:  " + result.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/gag.html#remove
         * 查询被禁言聊天室成员方法
         */

        ListGagChatroomUserResult chatroomListGagUserResult = gag.members.getList("d7ec7a8b8d8546c98b0973417209a548");
        System.out.println("ListGagUser:  " + chatroomListGagUserResult.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/gag.html#getList
         *
         * 移除禁言聊天室成员
         */
        chatroom = new ChatroomModel()
                .setId("chatroomId1")
                .setMemberIds(memberIds);
        ResponseResult removeResult = gag.members.remove(chatroom);
        System.out.println("rollbackGagUser:  " + result.toString());






    }
}
