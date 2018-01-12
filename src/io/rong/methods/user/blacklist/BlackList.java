package io.rong.methods.user.blacklist;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.QueryBlacklistUserResult;
import io.rong.models.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class BlackList {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "user/black";
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
    public BlackList(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }

    /**
     * 添加用户到黑名单方法（每秒钟限 100 次）
     *
     * @param  userId:用户 Id。（必传）
     * @param  blackIds:被加到黑名单的用户Id。（必传）
     *
     * @return CodeSuccessResult
     **/
    public ResponseResult add(String userId, String blackIds) throws Exception {

        //参数校验
        String message = CommonUtil.checkParam("id",userId,PATH,"user",CheckMethod.ADD);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        message = CommonUtil.checkParam("blackIds",blackIds,PATH,"user",CheckMethod.ADD);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
        sb.append("&blackUserId=").append(URLEncoder.encode(blackIds.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret,
                "/user/blacklist/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn)), ResponseResult.class);
    }

    /**
     * 获取某用户的黑名单列表方法（每秒钟限 100 次）
     *
     * @param  userId:用户 Id。（必传）
     *
     * @return QueryBlacklistUserResult
     **/
    public QueryBlacklistUserResult query(String userId) throws Exception {
        //参数校验
        String message = CommonUtil.checkParam("id",userId,PATH,"user","query");
        if(null != message){
            return (QueryBlacklistUserResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret,
                "/user/blacklist/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (QueryBlacklistUserResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn)), QueryBlacklistUserResult.class);
    }

    /**
     * 从黑名单中移除用户方法（每秒钟限 100 次）
     *
     * @param  userId:用户 Id。（必传）
     * @param  blackIds:被移除的用户Id。（必传）
     *
     * @return CodeSuccessResult
     **/
    public ResponseResult remove(String userId, String blackIds) throws Exception {
        //参数校验
        String message = CommonUtil.checkParam("id",userId,PATH,"user", CheckMethod.REMOVE);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }
        message = CommonUtil.checkParam("blackIds",blackIds,PATH,"user",CheckMethod.REMOVE);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
        sb.append("&blackUserId=").append(URLEncoder.encode(blackIds.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret,
                "/user/blacklist/remove.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn)), ResponseResult.class);
    }
}
