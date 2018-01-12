package io.rong.methods.conversation;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.models.CodeSuccessResult;
import io.rong.models.CommonConstrants;
import io.rong.models.ConversationNotificationResult;
import io.rong.models.TokenResult;
import io.rong.models.conversation.ConversationModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
public class Conversation {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "conversation";
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
    public Conversation(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 设置用户某会话接收新消息时是否进行消息提醒。
     *
     * @param conversation 会话信息 其中type(必传)
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult mute(ConversationModel conversation) throws Exception {
        //需要校验的字段
        String[] fileds = {"type","userId","targetId"};

        String message = CommonUtil.checkFiled(fileds,conversation,"conversation","conversation","mute");
        System.out.println("message:"+message);

        if(null != message){
            return (CodeSuccessResult)GsonUtil.fromJson(message,CodeSuccessResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode(conversation.type.toString(), UTF8));
        sb.append("&requestId=").append(URLEncoder.encode(conversation.userId .toString(), UTF8));
        sb.append("&targetId=").append(URLEncoder.encode(conversation.targetId.toString(), UTF8));
        sb.append("&isMuted=").append(URLEncoder.encode("1", UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/conversation/notification/set.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
    }

    /**
     * 设置用户某会话接收新消息时是否进行消息提醒。
     *
     * @param conversation 会话信息 其中type(必传)
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult unMute(ConversationModel conversation) throws Exception {
        //需要校验的字段
        String[] fileds = {"type","userId","targetId"};
        String message = CommonUtil.checkFiled(fileds,conversation,"conversation","conversation","mute");
        if(null != message){
            return (CodeSuccessResult)GsonUtil.fromJson(message,CodeSuccessResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode(conversation.type.toString(), UTF8));
        sb.append("&requestId=").append(URLEncoder.encode(conversation.userId .toString(), UTF8));
        sb.append("&targetId=").append(URLEncoder.encode(conversation.targetId.toString(), UTF8));
        sb.append("&isMuted=").append(URLEncoder.encode("0", UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/conversation/notification/set.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
    }

    /**
     * 设置用户某会话接收新消息时是否进行消息提醒。
     * @param conversation
     *
     * @return ConversationNotificationResult
     **/
    public ConversationNotificationResult get(ConversationModel conversation) throws Exception {
        String[] fileds = {"type","userId","targetId"};
        String message = CommonUtil.checkFiled(fileds,conversation,"conversation","conversation","mute");
        if(null != message){
            return (ConversationNotificationResult)GsonUtil.fromJson(message,ConversationNotificationResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&conversationType=").append(URLEncoder.encode(conversation.type.toString(), UTF8));
        sb.append("&requestId=").append(URLEncoder.encode(conversation.userId.toString(), UTF8));
        sb.append("&targetId=").append(URLEncoder.encode(conversation.targetId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/conversation/notification/get.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ConversationNotificationResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn)), ConversationNotificationResult.class);
    }
}
