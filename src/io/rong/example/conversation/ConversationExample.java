package io.rong.example.conversation;

import io.rong.RongCloud;
import io.rong.messages.VoiceMessage;
import io.rong.methods.conversation.Conversation;
import io.rong.models.CodeSuccessResult;
import io.rong.models.ConversationNotificationResult;
import io.rong.models.conversation.ConversationModel;
import io.rong.models.message.PrivateMessage;

public class ConversationExample {
    public static void main(String[] args) throws Exception {

        /*String appKey = "tdrvipkst7v85";
        String appSecret = "NmzK6kfrpC4";
        "UgYzcDZSisNyYaZ83WXcEk
        */

        String appKey = "e0x9wycfx7flq";
        String appSecret = "STCevzDS6Xy18n";
        String api = "http://192.168.155.13:9200";

        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);
        System.out.println("************************conversation********************");

        ConversationModel conversation = new ConversationModel("1","UgYzcDZSisNyYaZ83WXcEk11","2iXiqVWUAWwaKA55FuZvY31");
        ConversationNotificationResult getConversationResult = rongCloud.conversation.get(conversation);
        System.out.println("getConversationResult:  " + getConversationResult.toString());

        CodeSuccessResult muteConversationResult = rongCloud.conversation.mute(conversation);
        System.out.println("muteConversationResult:  " + muteConversationResult.toString());

        CodeSuccessResult unMuteConversationResult = rongCloud.conversation.unMute(conversation);
        System.out.println("unMuteConversationResult:  " + unMuteConversationResult.toString());

    }
}
