package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.demotion.Demotion;
import io.rong.models.response.ChatroomDemotionMsgResult;
import io.rong.models.response.ResponseResult;

public class DemotionExample {
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
        Demotion demotion = rongCloud.chatroom.demotion;

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
