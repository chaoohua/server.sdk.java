package io.rong.methods.message.chatroom;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.messages.BaseMessage;
import io.rong.models.CodeSuccessResult;
import io.rong.models.message.ChatroomMessage;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Chatroom {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "message";
    private static String method = "";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Chatroom(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }

    /**
     * 发送聊天室消息方法（一个用户向聊天室发送消息，单条消息最大 128k。每秒钟限 100 次。）
     *
     * @param  message:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。融云消息类型在messages下，自定义消息继承BaseMessage即可（必传）
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult publish(ChatroomMessage message) throws Exception {
        //需要校验的字段
        String[] fileds = {"senderUserId","targetIds","content"};

        String code = CommonUtil.checkFiled(fileds,message,"message","message","publish");
        if(null != code){
            return (CodeSuccessResult)GsonUtil.fromJson(code,CodeSuccessResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.senderUserId.toString(), UTF8));

        for (int i = 0 ; i< message.targetIds.length; i++) {
            String child  = message.targetIds[i];
            sb.append("&toChatroomId=").append(URLEncoder.encode(child, UTF8));
        }

        sb.append("&objectName=").append(URLEncoder.encode(message.content.getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(message.content.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/chatroom/publish.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
    }

    /**
     * 发送聊天室消广播消息方法（一个用户向聊天室发送消息，单条消息最大 128k。每秒钟限 100 次。）
     *
     * @param  message:发送消息内容，参考融云消息类型表.示例说明；如果 objectName 为自定义消息类型，该参数可自定义格式。融云消息类型在messages下，自定义消息继承BaseMessage即可（必传）
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult broadcast (ChatroomMessage message) throws Exception {
        //需要校验的字段
        String[] fileds = {"senderUserId"};

        String code = CommonUtil.checkFiled(fileds,message,"message/chatroom","message","broadcast");
        if(null != code){
            return (CodeSuccessResult)GsonUtil.fromJson(code,CodeSuccessResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.senderUserId.toString(), UTF8));


        sb.append("&objectName=").append(URLEncoder.encode(message.content.getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(message.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/chatroom/broadcast.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
    }
}
