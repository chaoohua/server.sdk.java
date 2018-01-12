package io.rong.methods.message;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.models.CodeSuccessResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import io.rong.models.message.Message;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class MessageT {
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
    public MessageT(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }


    /**
     * 发送单聊消息方法（一个用户向另外一个用户发送消息，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人，如：一次发送 1000 人时，示为 1000 条消息。）
     *
     * @param type
     * @param message
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult send(Integer type,Message message) throws Exception {
        String url = "";
        switch (type) {
            case 1:
                url = "/message/private/publish";
                break;
            case 2:
                url = "/message/system/publish";
                break;
            case 3:
                url = "/message/group/publish";
                break;
            case 4:
                url = "/message/discussion/publish";
                break;
            case 5:
                url = "/message/chatroom/publish";
                break;
            case 6:
                url = "/message/broadcast";
                break;
            default:
                break;
        }
       /* if (fromUserId == null) {
            throw new ParamException("Paramer 'fromUserId' is required");
        }

        if (toUserId == null) {
            throw new ParamException("Paramer 'toUserId' is required");
        }

        if (message == null) {
            throw new ParamException("Paramer 'message' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(fromUserId.toString(), UTF8));

        for (int i = 0 ; i< toUserId.length; i++) {
            String child  = toUserId[i];
            sb.append("&toUserId=").append(URLEncoder.encode(child, UTF8));
        }

        sb.append("&objectName=").append(URLEncoder.encode(message.getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(message.toString(), UTF8));

        if (pushContent != null) {
            sb.append("&pushContent=").append(URLEncoder.encode(pushContent.toString(), UTF8));
        }

        if (pushData != null) {
            sb.append("&pushData=").append(URLEncoder.encode(pushData.toString(), UTF8));
        }

        if (count != null) {
            sb.append("&count=").append(URLEncoder.encode(count.toString(), UTF8));
        }

        if (verifyBlacklist != null) {
            sb.append("&verifyBlacklist=").append(URLEncoder.encode(verifyBlacklist.toString(), UTF8));
        }

        if (isPersisted != null) {
            sb.append("&isPersisted=").append(URLEncoder.encode(isPersisted.toString(), UTF8));
        }

        if (isCounted != null) {
            sb.append("&isCounted=").append(URLEncoder.encode(isCounted.toString(), UTF8));
        }

        if (isIncludeSender != null) {
            sb.append("&isIncludeSender=").append(URLEncoder.encode(isIncludeSender.toString(), UTF8));
        }*/
        StringBuilder sb = new StringBuilder();
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, url, "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
    }
}
