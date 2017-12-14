package io.rong.methods.user.onlinestatus;

import io.rong.models.CheckOnlineResult;
import io.rong.models.user.UserConstrants;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class OnlineStatus {
    private static final String UTF8 = "UTF-8";
    private String appKey;
    private String appSecret;

    public OnlineStatus(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 检查用户在线状态 方法（每秒钟限100次）
     * 请不要频繁循环调用此接口，而是选择合适的频率和时机调用，此接口设置了一定的频率限制。
     *
     * @param  userId:用户 Id，最大长度 64 字节。是用户在 App 中的唯一标识码，必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。（必传）
     *
     * @return CheckOnlineResult
     **/
    public CheckOnlineResult checkOnline(String userId) throws Exception {
        if (userId == null) {
            return new CheckOnlineResult(UserConstrants.USER_ID_NULL,null,
                    "userId 长度超限 最大长度 64字节");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret,
                "/user/checkOnline.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CheckOnlineResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CheckOnlineResult.class);
    }
}
