package io.rong.methods.chatroom.priority;

import io.rong.models.CodeSuccessResult;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Priority {
    private static final String UTF8 = "UTF-8";
    private String appKey;
    private String appSecret;

    public Priority(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 添加聊天室消息优先级方法
     *
     * @param  objectName:低优先级的消息类型，每次最多提交 5 个，设置的消息类型最多不超过 20 个。（必传）
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult add(String[] objectName) throws Exception {
        if (objectName == null) {
            throw new IllegalArgumentException("Paramer 'objectName' is required");
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i< objectName.length; i++) {
            String child  = objectName[i];
            sb.append("&objectName=").append(URLEncoder.encode(child, UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/message/priority/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
    }

    /**
     * 销毁聊天室方法
     *
     * @param  chatroomId:要销毁的聊天室 Id。（必传）
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult destroy(String[] chatroomId) throws Exception {
        if (chatroomId == null) {
            throw new IllegalArgumentException("Paramer 'chatroomId' is required");
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i< chatroomId.length; i++) {
            String child  = chatroomId[i];
            sb.append("&chatroomId=").append(URLEncoder.encode(child, UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/destroy.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
    }
}
