package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.block.Block;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ListBlockChatroomUserResult;
import io.rong.models.response.ResponseResult;

public class BlockExample {

    private static final String appKey = "z3v5yqkbvy9f0";
    private static final String appSecret = "plhr2PA386a";
    private static final RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
    private static final Block block = rongCloud.chatroom.block;

    public static void main(String[] args) throws Exception {

        String[] memberIds = {"fhgko9i4","qawr34h"};
        /**
         *API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/block.html#add
         *
         * 添加封禁聊天室成员方法
         */
        ChatroomModel chatroom = new ChatroomModel()
                .setMemberIds(memberIds)
                .setMinute(5);
        ResponseResult result = block.add(chatroom);
        System.out.println("addBlockUser:  " + result.toString());


        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/block.html#remove
         *
         * 移除封禁聊天室成员方法
         */
        chatroom = new ChatroomModel().setMemberIds(memberIds);
        ResponseResult removeResult = block.remove(chatroom);
        System.out.println("removeResult:  " + removeResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/block.html#getList
         *
         * 查询被封禁聊天室成员方法
         */
        ListBlockChatroomUserResult getResult = block.getList("chatroomId1");
        System.out.println("getListBlockUser:  " + getResult.toString());
    }

}
