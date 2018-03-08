package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.demotion.Demotion;
import io.rong.models.response.ChatroomDemotionMsgResult;
import io.rong.models.response.ResponseResult;

public class DemotionExample {

    private static final String appKey = "z3v5yqkbvy9f0";
    private static final String appSecret = "plhr2PA386a";
    private static final RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
    private static final Demotion demotion = rongCloud.chatroom.demotion;

    public static void main(String[] args) throws Exception {

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/demotion.html#add
         * 添加应用内聊天室降级消息
         *
         * */
        String[] messageType = {"RC:VcMsg", "RC:ImgTextMsg", "RC:ImgMsg"};
        ResponseResult addResult = demotion.add(messageType);
        System.out.println("add demotion:  " + addResult.toString());

        /**
         *
         *API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/demotion.html#remove
         * 移除应用内聊天室降级消息
         *
         * */
        ResponseResult removeResult = demotion.remove(messageType);
        System.out.println("remove demotion:  " + removeResult.toString());


        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/demotion.html#getList
         * 添加聊天室消息优先级demo
         *
         * */
        ChatroomDemotionMsgResult demotionMsgResult = demotion.getList();
        System.out.println("get demotion:  " + demotionMsgResult.toString());

    }
}
