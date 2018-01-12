package io.rong.example.message;

import io.rong.RongCloud;
import io.rong.example.Example;
import io.rong.messages.TxtMessage;
import io.rong.messages.VoiceMessage;
import io.rong.models.CodeSuccessResult;
import io.rong.models.TemplateMessage;
import io.rong.models.message.ChatroomMessage;
import io.rong.models.message.GroupMessage;
import io.rong.models.message.PrivateMessage;
import io.rong.models.message.SystemMessage;
import io.rong.util.GsonUtil;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class MessageExample {
    private static final String JSONFILE = Example.class.getClassLoader().getResource("jsonsource").getPath()+"/";
    public static void main(String[] args) throws Exception {
        Reader reader = null ;
        String appKey = "e0x9wycfx7flq";
        String appSecret = "STCevzDS6Xy18n";
        String api = "http://192.168.155.13:9200";

        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        System.out.println("************************Message********************");
        String[] targetIds = {"userId2","userid3","userId4"};
        VoiceMessage voiceMessage = new VoiceMessage("hello", "helloExtra", 20L);
        TxtMessage txtMessage = new TxtMessage("hello", "helloExtra");
        PrivateMessage privateMessage = new PrivateMessage("userId1", targetIds, voiceMessage, "thisisapush",
                "{\"pushData\":\"hello\"}", "4", 0, 0, 0, 0);
        //发送单聊方法
        CodeSuccessResult publishPrivateResult = rongCloud.message.aPrivate.publish(privateMessage);
        System.out.println("publishPrivate:  " + publishPrivateResult.toString());

        // 发送单聊模板消息方法（一个用户向多个用户发送不同消息内容，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人。）
        try {
            reader = new InputStreamReader(new FileInputStream(JSONFILE+"TemplateMessage.json"));
            TemplateMessage publishTemplateTemplateMessage  =  (TemplateMessage) GsonUtil.fromJson(reader, TemplateMessage.class);
            CodeSuccessResult messagePublishTemplateResult = rongCloud.message.aPrivate.publishTemplate(publishTemplateTemplateMessage);
            System.out.println("publishPrivateTemplate:  " + messagePublishTemplateResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != reader){
                reader.close();
            }
        }

        //系统消息方法
        SystemMessage  systemMessage = new SystemMessage("userId1", targetIds, txtMessage,"thisisapush",
                "{\"pushData\":\"hello\"}", 0, 0,0);
        CodeSuccessResult messagePublishSystemResult = rongCloud.message.system.publish(systemMessage);
        System.out.println("publishSystem:  " + messagePublishSystemResult.toString());


        // 发送系统模板消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM.每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。）
        try {
            reader = new InputStreamReader(new FileInputStream(JSONFILE+"TemplateMessage.json"));
            TemplateMessage publishSystemTemplateTemplateMessage  =  (TemplateMessage)GsonUtil.fromJson(reader, TemplateMessage.class);
            CodeSuccessResult messagePublishSystemTemplateResult = rongCloud.message.system.publishTemplate(publishSystemTemplateTemplateMessage);
            System.out.println("publishSystemTemplate:  " + messagePublishSystemTemplateResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != reader){
                reader.close();
            }
        }

        //群组消息
        String[] targetIds1 = {"groupId2","groupId3","groupId4"};
        GroupMessage groupMessage = new GroupMessage("userId1", targetIds, txtMessage,"thisisapush",
                "{\"pushData\":\"hello\"}", 0, 0,0,0,0);
        CodeSuccessResult publishGroupResult = rongCloud.message.group.publish(groupMessage);
        System.out.println("publishGroup:  " + publishGroupResult.toString());

        //聊天室消息
        String[] targetIds2 = {"chatroomId2","chatroomId3","chatroomId4"};
        ChatroomMessage message = new ChatroomMessage("userId1",targetIds2,txtMessage.getType(),txtMessage);
        CodeSuccessResult messagePublishChatroomResult = rongCloud.message.chatroom.publish(message);
        System.out.println("publishChatroomPrivate:  " + messagePublishChatroomResult.toString());


    }
}
