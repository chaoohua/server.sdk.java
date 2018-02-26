package io.rong.example.conversation;

import io.rong.RongCloud;
import io.rong.models.response.ConversationNotificationResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.conversation.ConversationModel;

public class ConversationExample {

    public static void main(String[] args) throws Exception {

        String appKey = "tdrvipkst7v85";
        String appSecret = "NmzK6kfrpC4";
        String api = "http://192.168.155.13:9200";
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        ConversationModel conversation = new ConversationModel().setType("1")
                .setUserId("UgYzcDZSisNyYaZ83WXcEk11")
                .setTargetId("2iXiqVWUAWwaKA55FuZvY31");

        /**
         * 获取免打扰
         */
        testGetConversation(rongCloud,conversation);

        /**
         * 设置消息免打扰
         */
        testMute(rongCloud,conversation);

        /**
         * 解除消息免打扰
         * */
        testUnMute(rongCloud,conversation);



    }

    /**
     * 获取免打扰
     */
    public static void testGetConversation(RongCloud rongCloud,ConversationModel conversation) throws Exception {

        ConversationNotificationResult getConversationResult = rongCloud.conversation.get(conversation);

        System.out.println("getConversationResult:  " + getConversationResult.toString());

    }

    /**
     * 设置消息免打扰
     */
    public static void testMute(RongCloud rongCloud,ConversationModel conversation) throws Exception {

        ResponseResult muteConversationResult = rongCloud.conversation.mute(conversation);

        System.out.println("muteConversationResult:  " + muteConversationResult.toString());

    }
    /**
     * 解除消息免打扰
     * */
    public static void testUnMute(RongCloud rongCloud,ConversationModel conversation) throws Exception {

        ResponseResult unMuteConversationResult = rongCloud.conversation.unMute(conversation);

        System.out.println("unMuteConversationResult:  " + unMuteConversationResult.toString());
    }
}
