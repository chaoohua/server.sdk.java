package io.rong.methods.user.block;

import io.rong.models.QueryBlockUserResult;
import io.rong.models.ResponseResult;
import io.rong.models.user.UserConstrants;
import io.rong.models.user.UserResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Block {
    private static final String UTF8 = "UTF-8";
    private String appKey;
    private String appSecret;

    public Block(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 封禁用户方法（每秒钟限 100 次）
     *
     * @param  userId:用户 Id。（必传）
     * @param  minute:封禁时长,单位为分钟，最大值为43200分钟。（必传）
     *
     * @return CodeSuccessResult
     **/
    public ResponseResult add(String userId, Integer minute) throws Exception {
        if (userId == null) {
            return new UserResponseResult(UserConstrants.USER_ID_NULL,
                    "userId 长度超限 最大长度 64字节");		}

        if (minute == null) {
            return new UserResponseResult(UserConstrants.USER_PARAM_ERROR,
                    "Paramer 'minute' is required");
        }

        if(!CommonUtil.validateParams(Integer.valueOf(minute),30*24*60)){
            return new UserResponseResult(UserConstrants.USER_GAG_TIMEOUT,
                    "Paramer 'minute' should be less than 30*24*60 (30days)");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
        sb.append("&minute=").append(URLEncoder.encode(minute.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
                "/user/block.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (UserResponseResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), UserResponseResult.class);
    }

    /**
     * 解除用户封禁方法（每秒钟限 100 次）
     *
     * @param  userId:用户 Id。（必传）
     *
     * @return CodeSuccessResult
     **/
    public ResponseResult remove(String userId) throws Exception {
        if (userId == null) {
            return new UserResponseResult(UserConstrants.USER_ID_NULL,
                    "userId 长度超限 最大长度 64字节");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
                "/user/unblock.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (UserResponseResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), UserResponseResult.class);
    }

    /**
     * 获取被封禁用户方法（每秒钟限 100 次）
     *
     *
     * @return QueryBlockUserResult
     **/
    public QueryBlockUserResult getList() throws Exception {
        StringBuilder sb = new StringBuilder();
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
                "/user/block/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (QueryBlockUserResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), QueryBlockUserResult.class);
    }
}
