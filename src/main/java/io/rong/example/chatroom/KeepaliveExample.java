package io.rong.example.chatroom;

import io.rong.RongCloud;
import io.rong.methods.chatroom.keepalive.Keepalive;
import io.rong.models.response.ChatroomKeepaliveResult;
import io.rong.models.response.ResponseResult;

/**
 *
 * 聊天时保活示例
 * @author RongCloud
 * @date 2017/12/30
 * @version
 */
public class KeepaliveExample {
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
        Keepalive keepalive = rongCloud.chatroom.keepalive;

        /**
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/chatroom/keepalive.html#add
         *
         * 添加保活聊天室
         *
         **/
        ResponseResult addResult = keepalive.add("fhjk98uy");
        System.out.println("add keepalive result"+addResult.toString());

        /**
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/chatroom/keepalive.html#remove
         *
         * 删除保活聊天室
         *
         **/
        ResponseResult removeResult = keepalive.remove("fhjk98uy");
        System.out.println("keepalive remove"+addResult.toString());

        /**
         *
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/chatroom/keepalive.html#getList
         *
         * 获取保活聊天室
         *
         **/
        ChatroomKeepaliveResult result = keepalive.getList();

        System.out.println("keepalive getList"+addResult.toString());



    }

}
