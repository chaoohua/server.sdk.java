package io.rong.example.message;

import io.rong.RongCloud;
import io.rong.example.Example;
import io.rong.messages.TxtMessage;
import io.rong.messages.VoiceMessage;
import io.rong.models.response.ResponseResult;
import io.rong.models.TemplateMessage;
import io.rong.models.message.ChatroomMessage;
import io.rong.models.message.GroupMessage;
import io.rong.models.message.PrivateMessage;
import io.rong.models.message.SystemMessage;
import io.rong.util.GsonUtil;
import org.junit.Before;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.Assert.assertEquals;

public class MessageExample {
    private static final String JSONFILE = Example.class.getClassLoader().getResource("jsonsource").getPath()+"/";
    private RongCloud rongCloud ;
    private static final TxtMessage txtMessage = new TxtMessage("hello", "helloExtra");
    private static final VoiceMessage voiceMessage = new VoiceMessage("hello", "helloExtra", 20L);
    @Before
    public void setUp() throws Exception {
        String appKey = "e0x9wycfx7flq";
        String appSecret = "STCevzDS6Xy18n";
        String api = "http://192.168.155.13:9200";
        rongCloud = RongCloud.getInstance(appKey, appSecret,api);
    }
    /**
     * 创建聊天室方法
     */
    @Test
    public void testPublishSystem() throws Exception {
        String[] userIds = {"userId2","userid3","userId4"};
        //系统消息方法
        SystemMessage  systemMessage = new SystemMessage("userId1", userIds,txtMessage.getType(),txtMessage,"thisisapush",
                "{\"pushData\":\"hello\"}", 0, 0,0);
        ResponseResult result = rongCloud.message.system.publish(systemMessage);
        System.out.println("publishSystem:  " + result.toString());

        assertEquals("200",result.getCode().toString());
    }
    /**
     * 创建聊天室方法
     */
    @Test
    public void testPublishSystemTemplate() throws Exception {
        Reader reader = null ;
        // 发送系统模板消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM.每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。）
        try {
            reader = new InputStreamReader(new FileInputStream(JSONFILE+"TemplateMessage.json"));
            TemplateMessage publishSystemTemplateTemplateMessage  =  (TemplateMessage)GsonUtil.fromJson(reader, TemplateMessage.class);
            ResponseResult result = rongCloud.message.system.publishTemplate(publishSystemTemplateTemplateMessage);
            System.out.println("publishSystemTemplate:  " + result.toString());

            assertEquals("200",result.getCode().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != reader){
                reader.close();
            }
        }
    }
    /**
     * 测试单聊模板消息
     */
    @Test
    public void testPublishPrivateTemplate() throws Exception {
        Reader reader = null ;
        // 发送单聊模板消息方法（一个用户向多个用户发送不同消息内容，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人。）
        try {
            reader = new InputStreamReader(new FileInputStream(JSONFILE+"TemplateMessage.json"));
            TemplateMessage publishTemplateTemplateMessage  =  (TemplateMessage) GsonUtil.fromJson(reader, TemplateMessage.class);
            ResponseResult messagePublishTemplateResult = rongCloud.message.aPrivate.publishTemplate(publishTemplateTemplateMessage);
            System.out.println("publishPrivateTemplate:  " + messagePublishTemplateResult.toString());

            assertEquals("200",messagePublishTemplateResult.getCode().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != reader){
                reader.close();
            }
        }
    }
    /**
     * 测试单聊
     * */
    @Test
    public void testPublishPrivate() throws Exception {
        Reader reader = null ;
        String[] targetIds = {"userId2","userid3","userId4"};
        PrivateMessage privateMessage = new PrivateMessage("userId1", targetIds, voiceMessage.getType(),voiceMessage, "thisisapush",
                "{\"pushData\":\"hello\"}", "4", 0, 0, 0, 0);
        //发送单聊方法
        ResponseResult publishPrivateResult = rongCloud.message.aPrivate.publish(privateMessage);
        System.out.println("publishPrivate:  " + publishPrivateResult.toString());

        assertEquals("200",publishPrivateResult.getCode().toString());
    }
    /**
     * 测试单聊
     * */
    @Test
    public void testPublishGroup() throws Exception {
        //群组消息
        String[] groupIds = {"groupId2","groupId3","groupId4"};
        GroupMessage groupMessage = new GroupMessage("userId1", groupIds,txtMessage.getType(), txtMessage,"thisisapush",
                "{\"pushData\":\"hello\"}", 0, 0,0,0,0);
        ResponseResult result = rongCloud.message.group.publish(groupMessage);
        System.out.println("publishGroup:  " + result.toString());

        assertEquals("200",result.getCode().toString());
    }

    /**
     * 测试单聊
     * */
    @Test
    public void testPublishChatroomPrivate() throws Exception {
        //聊天室消息
        String[] targetIds2 = {"chatroomId2","chatroomId3","chatroomId4"};
        ChatroomMessage message = new ChatroomMessage("userId1",targetIds2,txtMessage.getType(),txtMessage);
        ResponseResult result = rongCloud.message.chatroom.publish(message);
        System.out.println("publishChatroomPrivate:  " + result.toString());

        assertEquals("200",result.getCode().toString());
    }

   /* public static void main(String[] args) throws Exception {
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

    }*/
}
