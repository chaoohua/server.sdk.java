package io.rong.methods.chatroom.keepalive;

import io.rong.models.CodeSuccessResult;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Keepalive {
    private static final String UTF8 = "UTF-8";
    private String appKey;
    private String appSecret;

    public Keepalive(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 添加聊天室消息优先级方法
     *
     * @param  chatroomId:低优先级的消息类型，每次最多提交 5 个，设置的消息类型最多不超过 20 个。（必传）
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult add(String chatroomId) throws Exception {
        if (chatroomId == null) {
            throw new IllegalArgumentException("Paramer 'chatroomId' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId, UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/keepalive/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
    }
    /**
     * 添加聊天室消息优先级方法
     *
     * @param  chatroomId:低优先级的消息类型，每次最多提交 5 个，设置的消息类型最多不超过 20 个。（必传）
     *remove
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult remove(String chatroomId) throws Exception {
        if (chatroomId == null) {
            throw new IllegalArgumentException("Paramer 'chatroomId' is required");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId, UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/keepalive/remove.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
    }
    /**
     * 获取聊天室保活
     *
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult get() throws Exception {
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/keepalive/query.json", "application/x-www-form-urlencoded");
        //HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
    }
}
