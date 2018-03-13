package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.ban.Ban;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ListGagChatroomUserResult;
import io.rong.models.response.ResponseResult;

/**
 * 聊天室全局
 * @author RongCloud
 */
public class BanExample {
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
        Ban ban = rongCloud.chatroom.ban;

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/ban.html#add
         * 添加聊天室全局禁言
         * */
        ChatroomMember[] members = {
                new ChatroomMember().setId("qawr34h"),new ChatroomMember().setId("qawr35h")
        };
        ChatroomModel chatroom = new ChatroomModel()
                .setMembers(members)
                .setMinute(5);
        ResponseResult result = ban.add(chatroom);
        System.out.println("addGagUser:  " + result.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/ban.html#getList
         * 获取聊天时全局禁言列表
         */

        ListGagChatroomUserResult chatroomListGagUserResult = ban.getList();
        System.out.println("ListGagUser:  " + chatroomListGagUserResult.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/ban.html#remove
         * 删除聊天时全局禁言
         */
        chatroom = new ChatroomModel()
                .setMembers(members);
        ResponseResult removeResult = ban.remove(chatroom);
        System.out.println("removeBanUser:  " + removeResult.toString());
    }
}
