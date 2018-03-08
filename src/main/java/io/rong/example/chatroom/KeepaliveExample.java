package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.keepalive.Keepalive;
import io.rong.models.CheckMethod;
import io.rong.models.response.ChatroomKeepaliveResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class KeepaliveExample {
    private static final String appKey = "z3v5yqkbvy9f0";
    private static final String appSecret = "plhr2PA386a";
    private static final RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
    private static final Keepalive keepalive = rongCloud.chatroom.keepalive;

    public static void main(String[] args) throws Exception {
        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/keepalive.html#add
         *
         * 添加保活聊天室
         *
         **/
        ResponseResult addResult = keepalive.add("fhjk98uy");

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/keepalive.html#remove
         *
         * 删除保活聊天室
         *
         **/
        ResponseResult removeResult = keepalive.remove("fhjk98uy");

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/chatroom/keepalive.html#getList
         *
         * 获取保活聊天室
         *
         **/
        ChatroomKeepaliveResult result = keepalive.getList();


    }

}
