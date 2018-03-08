package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.Distribute;
import io.rong.models.response.ResponseResult;

public class DistributeExample {
    private static final String appKey = "z3v5yqkbvy9f0";
    private static final String appSecret = "plhr2PA386a";
    private static final RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
    private static final Distribute distribute = rongCloud.chatroom.distribute;

    public static void main(String[] args) throws Exception {
        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/distribute.html#stop
         *
         * 聊天室消息停止分发
         *
         */
        ResponseResult result = distribute.stop("chatroomId1");

        System.out.println("stopDistributionMessage:  " + result.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/distribute.html#resume
         *
         * 聊天室消息恢复分发方法（每秒钟限 100 次）
         */
        ResponseResult resumeResult = distribute.resume("chatroomId1");
        System.out.println("resumeDistributionMessage:  " + resumeResult.toString());

    }
}
