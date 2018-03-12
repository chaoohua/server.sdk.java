package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.distribute.Distribute;
import io.rong.models.response.ResponseResult;

public class DistributeExample {
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
        Distribute distribute = rongCloud.chatroom.distribute;

        /**
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/chatroom/distribute.html#stop
         *
         * 聊天室消息停止分发
         *
         */
        ResponseResult result = distribute.stop("chatroomId1");

        System.out.println("stopDistributionMessage:  " + result.toString());

        /**
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/chatroom/distribute.html#resume
         *
         * 聊天室消息恢复分发方法（每秒钟限 100 次）
         */
        ResponseResult resumeResult = distribute.resume("chatroomId1");
        System.out.println("resumeDistributionMessage:  " + resumeResult.toString());

    }
}
