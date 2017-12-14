package io.rong.methods.user.blacklist;

import io.rong.models.QueryBlacklistUserResult;
import io.rong.models.ResponseResult;
import io.rong.models.user.UserConstrants;
import io.rong.models.user.UserResponseResult;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class BlackList {

    private static final String UTF8 = "UTF-8";
    private String appKey;
    private String appSecret;

    public BlackList(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }

    /**
     * 添加用户到黑名单方法（每秒钟限 100 次）
     *
     * @param  userId:用户 Id。（必传）
     * @param  blackUserId:被加到黑名单的用户Id。（必传）
     *
     * @return CodeSuccessResult
     **/
    public ResponseResult add(String userId, String blackUserId) throws Exception {
        if (userId == null) {
            return new UserResponseResult(UserConstrants.USER_ID_NULL,
                    "userId 长度超限 最大长度 64字节");
        }

        if (blackUserId == null) {
            return new UserResponseResult(UserConstrants.USER_PARAM_ERROR,
                    "Paramer 'blackUserId' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
        sb.append("&blackUserId=").append(URLEncoder.encode(blackUserId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
                "/user/blacklist/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (UserResponseResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), UserResponseResult.class);
    }

    /**
     * 获取某用户的黑名单列表方法（每秒钟限 100 次）
     *
     * @param  userId:用户 Id。（必传）
     *
     * @return QueryBlacklistUserResult
     **/
    public QueryBlacklistUserResult query(String userId) throws Exception {
        if (userId == null) {
            return new QueryBlacklistUserResult(UserConstrants.USER_ID_NULL,null,
                    "userId 长度超限 最大长度 64字节");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
                "/user/blacklist/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (QueryBlacklistUserResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), QueryBlacklistUserResult.class);
    }

    /**
     * 从黑名单中移除用户方法（每秒钟限 100 次）
     *
     * @param  userId:用户 Id。（必传）
     * @param  blackUserId:被移除的用户Id。（必传）
     *
     * @return CodeSuccessResult
     **/
    public ResponseResult remove(String userId, String blackUserId) throws Exception {
        if (userId == null) {
            return new UserResponseResult(UserConstrants.USER_ID_NULL,
                    "Paramer 'userId' is required\"");
        }

        if (blackUserId == null) {
            return new UserResponseResult(UserConstrants.USER_PARAM_ERROR,
                    "Paramer 'blackUserId' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
        sb.append("&blackUserId=").append(URLEncoder.encode(blackUserId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
                "/user/blacklist/remove.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (UserResponseResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), UserResponseResult.class);
    }
}
