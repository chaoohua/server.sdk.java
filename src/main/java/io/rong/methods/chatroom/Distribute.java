package io.rong.methods.chatroom;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Distribute {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "chatroom/whitelist";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Distribute(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }

    /**
     * 停止聊天室消息分发（可实现控制对聊天室中消息是否进行分发，停止分发后聊天室中用户发送的消息，融云服务端不会再将消息发送给聊天室中其他用户。）
     *
     * @param  chatroomId:聊天室 Id。（必传）
     *
     * @return ResponseResult
     **/
    public ResponseResult stop(String chatroomId) throws Exception {
        String message = CommonUtil.checkParam("id",chatroomId,PATH, CheckMethod.STOP_DISTRIBUTION);
        if(null != message){
            return (ResponseResult) GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/message/stopDistribution.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.STOP_DISTRIBUTION,HttpUtil.returnResult(conn)), ResponseResult.class);
    }

    /**
     * 恢复聊天室消息分发
     *
     * @param  chatroomId:聊天室 Id。（必传）
     *
     * @return ResponseResult
     **/
    public ResponseResult resume(String chatroomId) throws Exception {
        String message = CommonUtil.checkParam("id",chatroomId,PATH,CheckMethod.RESUME_DISTRIBUTION);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/message/resumeDistribution.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.RESUME_DISTRIBUTION,HttpUtil.returnResult(conn)), ResponseResult.class);
    }
}
