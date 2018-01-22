package io.rong.methods.broadcast;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.messages.BaseMessage;
import io.rong.models.CodeSuccessResult;
import io.rong.models.CommonConstrants;
import io.rong.models.TokenResult;
import io.rong.models.message.BroadcastMessage;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 *
 * 发送广播消息方法
 * docs: "http://www.rongcloud.cn/docs/server.html#message_broadcast"
 *
 * */
public class Broadcast {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "broadcast";
    private static final String BROADCAST_SEND = "send";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Broadcast(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 发送广播消息方法（发送消息给一个应用下的所有注册用户，如用户未在线会对满足条件（绑定手机终端）的用户发送 Push 信息，单条消息最大 128k，会话类型为 SYSTEM。每小时只能发送 2 次，每天最多发送 3 次。）
     * 该功能开发环境下可免费使用。生产环境下，您需要登录开发者后台，在“应用/IM 服务/高级功能设置”中开通公有云专业版后，在“广播消息和推送”中，开启后才能使用
     *
     *  @param broadcastMessage
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult send(BroadcastMessage broadcastMessage) throws Exception {
        String message = CommonUtil.checkFiled(broadcastMessage,PATH,BROADCAST_SEND);
        if(null != message){
            return (CodeSuccessResult)GsonUtil.fromJson(message,CodeSuccessResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(broadcastMessage.senderUserId.toString(), UTF8));
        sb.append("&objectName=").append(URLEncoder.encode(broadcastMessage.content.getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(broadcastMessage.content.toString(), UTF8));

        if (broadcastMessage.pushContent != null) {
            sb.append("&pushContent=").append(URLEncoder.encode(broadcastMessage.pushContent.toString(), UTF8));
        }

        if (broadcastMessage.pushData != null) {
            sb.append("&pushData=").append(URLEncoder.encode(broadcastMessage.pushData.toString(), UTF8));
        }

        if (broadcastMessage.os != null) {
            sb.append("&os=").append(URLEncoder.encode(broadcastMessage.os.toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/message/broadcast.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,BROADCAST_SEND,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
    }
}
