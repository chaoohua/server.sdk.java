package io.rong.methods.user.block;

import io.rong.RongCloud;
import io.rong.models.CheckMethod;
import io.rong.models.QueryBlockUserResult;
import io.rong.models.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Block {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "user/block";
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
     * @return ResponseResult
     **/
    public ResponseResult add(String userId, Integer minute) throws Exception {

        String message = CommonUtil.checkParam("id",userId,"user/block","user", CheckMethod.ADD);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        message = CommonUtil.checkParam("minute",minute,"user/block","user",CheckMethod.ADD);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
        sb.append("&minute=").append(URLEncoder.encode(minute.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret,
                "/user/block.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn)), ResponseResult.class);
    }

    /**
     * 解除用户封禁方法（每秒钟限 100 次）
     *
     * @param  userId:用户 Id。（必传）
     *
     * @return CodeSuccessResult
     **/
    public ResponseResult remove(String userId) throws Exception {
        //参数校验
        String message = CommonUtil.checkParam("id",userId,"user/block","user",CheckMethod.REMOVE);
        if(null != message){
            return (ResponseResult)GsonUtil.fromJson(message,ResponseResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret,
                "/user/unblock.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn)), ResponseResult.class);
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

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret,
                "/user/block/query.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (QueryBlockUserResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn)), QueryBlockUserResult.class);
    }
}
