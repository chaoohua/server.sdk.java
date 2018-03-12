package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.block.Block;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ListBlockChatroomUserResult;
import io.rong.models.response.ResponseResult;

public class BlockExample {
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
        Block block = rongCloud.chatroom.block;

        String[] memberIds = {"fhgko9i4","qawr34h"};
        /**
         *API 文档: http://www.rongcloud.cn/docs/server/sdk/chatroom/block.html#add
         *
         * 添加封禁聊天室成员方法
         */
        ChatroomModel chatroom = new ChatroomModel()
                .setId("IXQhMs3ny")
                .setMemberIds(memberIds)
                .setMinute(5);
        ResponseResult result = block.add(chatroom);
        System.out.println("addBlockUser:  " + result.toString());


        /**
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/chatroom/block.html#remove
         *
         * 移除封禁聊天室成员方法
         */
        chatroom = new ChatroomModel()
                .setId("IXQhMs3ny")
                .setMemberIds(memberIds);
        ResponseResult removeResult = block.remove(chatroom);
        System.out.println("removeResult:  " + removeResult.toString());

        /**
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/chatroom/block.html#getList
         *
         * 查询被封禁聊天室成员方法
         */
        ListBlockChatroomUserResult getResult = block.getList("chatroomId1");
        System.out.println("getListBlockUser:  " + getResult.toString());
    }

}
