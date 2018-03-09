package io.rong.example.message;

import io.rong.RongCloud;
import io.rong.messages.TxtMessage;
import io.rong.messages.VoiceMessage;
import io.rong.methods.message.Message;
import io.rong.models.message.*;
import io.rong.models.response.HistoryMessageResult;
import io.rong.models.response.ResponseResult;
import io.rong.util.GsonUtil;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;


/**
 * 消息发送示例
 * @date
 * @author hc
 * @version
 */
public class MessageExample {
    private static final String JSONFILE = MessageExample.class.getClassLoader().getResource("jsonsource").getPath()+"/";
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "8luwapkv8s7pl";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "lmkgpHuXezTjV2";

    private static final TxtMessage txtMessage = new TxtMessage("hello", "helloExtra");
    private static final VoiceMessage voiceMessage = new VoiceMessage("hello", "helloExtra", 20L);

    private static final String api = "http://192.168.155.13:9200";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        Message Message = rongCloud.message;

        /**
         *
         * 发送系统消息
         *
         */
        String[] targetIds = {"userId2","userid3","userId4"};
        SystemMessage systemMessage = new SystemMessage()
                .setSenderUserId("usetId")
                .setTargetIds(targetIds)
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setPushContent("this is a push")
                .setPushData("{'pushData':'hello'}")
                .setIsPersisted(0)
                .setIsCounted(0)
                .setContentAvailable(0);

        ResponseResult result = Message.system.send(systemMessage);
        System.out.println("send system message:  " + result.toString());

        /**
         *
         * 发送系统模板消息方法
         *
         */
        Reader reader = null ;
        try {
            reader = new InputStreamReader(new FileInputStream(JSONFILE+"/message/"+"Template.json"));
            Template template = (Template)GsonUtil.fromJson(reader, Template.class);
            ResponseResult systemTemplateResult = Message.system.sendTemplate(template);
            System.out.println("send system template message:  " + systemTemplateResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != reader){
                reader.close();
            }
        }

        /**
         * 发送单聊消息
         * */
        PrivateMessage  privateMessage = new PrivateMessage()
                .setSenderUserId("userId")
                .setTargetIds(targetIds)
                .setObjectName(voiceMessage.getType())
                .setContent(voiceMessage)
                .setPushContent("")
                .setPushData("{\"pushData\":\"hello\"}")
                .setCount("4")
                .setVerifyBlacklist(0)
                .setIsPersisted(0)
                .setIsCounted(0)
                .setIsIncludeSender(0);
        ResponseResult publishPrivateResult = Message.aPrivate.send(privateMessage);
        System.out.println("sendPrivate:  " + publishPrivateResult.toString());

        /**
         * 发送单聊模板消息方法
         */
        try {
            reader = new InputStreamReader(new FileInputStream(JSONFILE+"/message/"+"Template.json"));
            Template template  =  (Template) GsonUtil.fromJson(reader, Template.class);
            ResponseResult messagePublishTemplateResult = Message.aPrivate.sendTemplate(template);

            System.out.println("send privateTemplate message:  " + messagePublishTemplateResult.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != reader){
                reader.close();
            }
        }
        /**
         * 撤回单聊消息
         * */
        RecallMessage message = new RecallMessage()
                .setSenderUserId("sea9901")
                .setTargetId("IXQhMs3ny")
                .setuId("5GSB-RPM1-KP8H-9JHF")
                .setSentTime("1519444243981");
        ResponseResult recallPrivateResult = (ResponseResult)Message.aPrivate.recall(message);
        System.out.println("recall private:  " + recallPrivateResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/message/group.html#send
         *
         * 群组消息
         * */
        GroupMessage groupMessage = new GroupMessage()
                .setSenderUserId("userId")
                .setTargetIds(targetIds)
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setPushContent("this is a push")
                .setPushData("{\"pushData\":\"hello\"}")
                .setIsPersisted(0)
                .setIsCounted(0)
                .setIsIncludeSender(0)
                .setContentAvailable(0);
        ResponseResult groupResult = Message.group.send(groupMessage);

        System.out.println("send Group message:  " + groupResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/message/private.html#recall
         *
         * 群组撤回消息
         * */
         message = new RecallMessage()
                .setSenderUserId("sea9901")
                .setTargetId("markoiwm")
                .setuId("5GSB-RPM1-KP8H-9JHF")
                .setSentTime("1519444243981");
        ResponseResult recallMessageResult = (ResponseResult)Message.group.recall(message);

        System.out.println("send recall message:  " + recallMessageResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/message/private.html#recall
         *
         * 群组@消息
         * */
        //要@的人
        String[] mentionIds = {"Hji8yh76","sea9901"};

        MentionedInfo mentionedInfo = new MentionedInfo(1,mentionIds,"");
        //@内容
        GroupMentionContent content = new GroupMentionContent(txtMessage,mentionedInfo);

        MentionMessage mentionMessage = new MentionMessage()
                .setSenderUserId("userId")
                .setMentionIds(targetIds)
                .setObjectName(txtMessage.getType())
                .setContent(content)
                .setPushContent("this is a push")
                .setPushData("{\"pushData\":\"hello\"}")
                .setIsPersisted(0)
                .setIsCounted(0)
                .setIsIncludeSender(0)
                .setIsMentioned(0)
                .setContentAvailable(0);
        ResponseResult mentionResult = rongCloud.message.group.sendMention(mentionMessage);

        System.out.println("group mention result:  " + mentionResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/message/discussion.html#send
         *
         * 发送讨论组消息
         * */
        String[] discussionIds = {"lijhGk87","lijhGk88"};
        DiscussionMessage discussionMessage = new DiscussionMessage()
                .setSenderUserId("JuikH78ko")
                .setTargetIds(discussionIds)
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setPushContent("this is a push")
                .setPushData("{\"pushData\":\"hello\"}")
                .setIsPersisted(0)
                .setIsCounted(0)
                .setIsIncludeSender(0)
                .setContentAvailable(0);

        ResponseResult discussionResult = Message.discussion.send(discussionMessage);

        System.out.println("send Group message:  " + groupResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/message/discussion.html#recall
         *
         * 撤回讨论组消息
         * */
        message = new RecallMessage()
                .setSenderUserId("sea9901")
                .setTargetId("IXQhMs3ny")
                .setuId("5GSB-RPM1-KP8H-9JHF")
                .setSentTime("1519444243981");
        ResponseResult recallDiscussionResult = (ResponseResult) Message.discussion.recall(message);

        System.out.println("send Group message:  " + recallDiscussionResult.toString());


        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/message/chatroom.html#send
         *
         * 聊天室消息
         * */

        String[] chatroomIds = {"IXQhMs3ny","IXQlMs3oy","IXQlMs3olp"};


        ChatroomMessage chatroomMessage = new ChatroomMessage()
                .setSenderUserId("bN6oQi8T5")
                .setTargetIds(chatroomIds)
                .setContent(txtMessage)
                .setObjectName(txtMessage.getType());

        ResponseResult chatroomResult = Message.chatroom.send(chatroomMessage);
        System.out.println("send chatroom message:  " + chatroomResult.toString());
        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/message/chatroom.html#broadcast
         *
         * 聊天室广播消息
         *
         * 此功能需开通专有服务: http://www.rongcloud.cn/deployment#overseas-cloud
         *
         * */
        chatroomMessage = new ChatroomMessage()
                .setSenderUserId("bN6oQi8T5")
                .setContent(txtMessage)
                .setObjectName(txtMessage.getType());

        ResponseResult chatroomBroadcastresult = Message.chatroom.broadcast(chatroomMessage);
        System.out.println("send chatroom broadcast message:  " + chatroomBroadcastresult.toString());


        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/message/history.html#get
         *
         * 获取历史消息日志文件
         *
         * */

        HistoryMessageResult historyMessageResult = (HistoryMessageResult) Message.history.get("2018030210");
        System.out.println("get history  message:  " + historyMessageResult.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/message/history.html#get
         *
         * 删除历史消息日志文件
         *
         * */

        ResponseResult removeHistoryMessageResult = (ResponseResult)Message.history.remove("2018030210");
        System.out.println("remove history  message:  " + removeHistoryMessageResult.toString());


    }
}
