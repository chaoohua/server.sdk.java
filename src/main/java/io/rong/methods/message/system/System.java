package io.rong.methods.message.system;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.response.ResponseResult;
import io.rong.models.TemplateMessage;
import io.rong.models.message.SystemMessage;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 * 发送系统消息方法
 *
 * docs : http://www.rongcloud.cn/docs/server.html#message_system_publish
 * @author hc
 * @date 2017/12/30
 */
public class System {
    private static final String UTF8 = "UTF-8";
    private String appKey;
    private String appSecret;
    private static final String PATH = "message/system";
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
     * @return ResponseResult
     **/
    public ResponseResult publish(SystemMessage systemMessage) throws Exception {
        String code = CommonUtil.checkFiled(systemMessage,PATH,CheckMethod.PUBLISH);
        if(null != code){
            return (ResponseResult)GsonUtil.fromJson(code,ResponseResult.class);
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

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISH,CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISH,HttpUtil.returnResult(conn))), ResponseResult.class);
    }

    /**
     * 发送系统模板消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM.每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。）
     *
     * @param  templateMessage:系统模版消息。
     *
     * @return ResponseResult
     **/
    public ResponseResult publishTemplate(TemplateMessage templateMessage) throws Exception {
        String code = CommonUtil.checkFiled(templateMessage,PATH,CheckMethod.PUBLISHTEMPLATE);
        if(null != code){
            return (ResponseResult)GsonUtil.fromJson(code,ResponseResult.class);
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/system/publish_template.json", "application/json");
        HttpUtil.setBodyParameter(templateMessage.toString(), conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISHTEMPLATE,HttpUtil.returnResult(conn)), ResponseResult.class);
    }
}
