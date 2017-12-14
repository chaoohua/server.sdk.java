package io.rong.methods.chatroom.whitelist;

import io.rong.models.CodeSuccessResult;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class WhiteList {
    private static final String UTF8 = "UTF-8";
    private String appKey;
    private String appSecret;

    public WhiteList(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 添加聊天室白名单成员方法
     *
     * @param  chatroomId:聊天室中用户 Id，可提交多个，聊天室中白名单用户最多不超过 5 个。（必传）
     * @param  userId:聊天室 Id。（必传）
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult add(String chatroomId, String[] userId) throws Exception {
        if (chatroomId == null) {
            throw new IllegalArgumentException("Paramer 'chatroomId' is required");
        }

        if (userId == null) {
            throw new IllegalArgumentException("Paramer 'userId' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));

        for (int i = 0 ; i< userId.length; i++) {
            String child  = userId[i];
            sb.append("&userId=").append(URLEncoder.encode(child, UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/user/whitelist/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
    }
}
