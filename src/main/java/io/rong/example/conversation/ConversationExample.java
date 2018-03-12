package io.rong.example.conversation;

import io.rong.RongCloud;
import io.rong.methods.conversation.Conversation;
import io.rong.models.response.ConversationNotificationResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.conversation.ConversationModel;
/**
 *
 * 绘话示例
 * @author RongCloud
 * @date 2017/12/30
 * @version
 */
public class ConversationExample {
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "8luwapkv8s7pl";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "lmkgpHuXezTjV2";
    /**
     * 自定义api地址
     * */
    private static final String api = "http://192.168.155.13:9200";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        Conversation Conversation = rongCloud.conversation;
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);


        ConversationModel conversation = new ConversationModel()
                .setType("1")
                .setUserId("UgYzcDZSisNyYaZ83WXcEk11")
                .setTargetId("2iXiqVWUAWwaKA55FuZvY31");
        /**
         *
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/conversation/conversation.html#mute
         * 设置消息免打扰
         *
         */
        ResponseResult muteConversationResult = Conversation.mute(conversation);

        System.out.println("muteConversationResult:  " + muteConversationResult.toString());

        /**
         *
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/conversation/conversation.html#get
         * 获取免打扰
         *
         */

        ConversationNotificationResult getConversationResult = Conversation.get(conversation);

        System.out.println("getConversationResult:  " + getConversationResult.toString());

        /**
         *
         * API 文档: http://www.rongcloud.cn/docs/server/sdk/conversation/conversation.html#unmute
         * 解除消息免打扰
         *
         * */
        ResponseResult unMuteConversationResult = Conversation.unMute(conversation);

        System.out.println("unMuteConversationResult:  " + unMuteConversationResult.toString());
    }
}
