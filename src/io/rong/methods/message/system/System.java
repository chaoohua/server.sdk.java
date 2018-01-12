package io.rong.methods.message.system;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.messages.BaseMessage;
import io.rong.models.CodeSuccessResult;
import io.rong.models.TemplateMessage;
import io.rong.models.message.Message;
import io.rong.models.message.SystemMessage;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class System {
    private static final String UTF8 = "UTF-8";
    private String appKey;
    private String appSecret;
    private static final String PATH = "message/system";
    private static String method = "";
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public System(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }

    /**
     * 发送系统消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM。每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。）
     *
     * @param
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult publish(SystemMessage systemMessage) throws Exception {
        method = "publish";
        if (systemMessage.getSenderUserId() == null) {
            throw new ParamException("Paramer 'fromUserId' is required");
        }

        if (systemMessage.getTargetIds() == null) {
            throw new ParamException("Paramer 'toUserId' is required");
        }

        if (systemMessage.getContent() == null) {
            throw new ParamException("Paramer 'message' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(systemMessage.getSenderUserId().toString(), UTF8));

        for (int i = 0 ; i< systemMessage.getTargetIds().length; i++) {
            String child  = systemMessage.getTargetIds()[i];
            sb.append("&toUserId=").append(URLEncoder.encode(child, UTF8));
        }

        sb.append("&objectName=").append(URLEncoder.encode(systemMessage.getContent().getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(systemMessage.getContent().toString(), UTF8));

        if (systemMessage.getPushContent() != null) {
            sb.append("&pushContent=").append(URLEncoder.encode(systemMessage.getPushContent().toString(), UTF8));
        }

        if (systemMessage.getPushData() != null) {
            sb.append("&pushData=").append(URLEncoder.encode(systemMessage.getPushData().toString(), UTF8));
        }

        if (systemMessage.getIsPersisted() != null) {
            sb.append("&isPersisted=").append(URLEncoder.encode(systemMessage.getIsPersisted().toString(), UTF8));
        }

        if (systemMessage.getPushData() != null) {
            sb.append("&isCounted=").append(URLEncoder.encode(systemMessage.getIsCounted().toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/system/publish.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn))), CodeSuccessResult.class);
    }

    /**
     * 发送系统模板消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM.每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。）
     *
     * @param  templateMessage:系统模版消息。
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult publishTemplate(TemplateMessage templateMessage) throws Exception {
        if (templateMessage == null) {
            throw new ParamException("Paramer 'templateMessage' is required");
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/system/publish_template.json", "application/json");
        HttpUtil.setBodyParameter(templateMessage.toString(), conn);

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
    }
}
