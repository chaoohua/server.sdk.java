package io.rong.methods.chatroom.gag.global;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.models.CheckMethod;
import io.rong.models.CodeSuccessResult;
import io.rong.models.CommonConstrants;
import io.rong.models.ListGagChatroomUserResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;


public class Global {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "chatroom/global-gag";
    private static final String ADD = "add";
    private static final String GETLIST = "getList";
    private static final String REMOVE = "remove";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Global(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }

    /**
     * 添加聊天室全局禁言方法
     *
     * @param  userId:用户 Id。（必传）
     * @param  minute:禁言时长，以分钟为单位，最大值为43200分钟。（必传）
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult add(String[] userId, String minute) throws Exception {

        String message = CommonUtil.checkParam("members",userId,PATH,"chatroom",ADD);
        if(null != message){
            return (CodeSuccessResult)GsonUtil.fromJson(message,CodeSuccessResult.class);
        }
        message = CommonUtil.checkParam("minute",minute,PATH,"chatroom", CheckMethod.ADD);
        if(null != message){
            return (CodeSuccessResult)GsonUtil.fromJson(message,CodeSuccessResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
        sb.append("&minute=").append(URLEncoder.encode(minute.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/user/ban/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,ADD,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
    }

    /**
     * 查询聊天室全局禁言用户方法
     *
     * @return ListGagChatroomUserResult
     **/
    public ListGagChatroomUserResult getList() throws Exception {
        StringBuilder sb = new StringBuilder();

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/user/ban/query.json", "application/x-www-form-urlencoded");

        return (ListGagChatroomUserResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,GETLIST,HttpUtil.returnResult(conn)), ListGagChatroomUserResult.class);
    }

    /**
     * 移除聊天室全局禁言方法
     *
     * @param  userId:用户 Id。（必传）
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult remove(String userId) throws Exception {
        if (userId == null) {
            throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL,"/chatroom/user/ban/remove","Paramer 'userId' is required");
        }
        String message = CommonUtil.checkParam("userId",userId,PATH,"chatroom",REMOVE);
        if(null != message){
            return (CodeSuccessResult)GsonUtil.fromJson(message,CodeSuccessResult.class);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(userId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/user/ban/remove.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,REMOVE,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
    }
}


