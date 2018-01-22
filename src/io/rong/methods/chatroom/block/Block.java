package io.rong.methods.chatroom.block;

import io.rong.RongCloud;
import io.rong.models.*;
import io.rong.models.chatroom.ChatRoom;
import io.rong.models.chatroom.ListBlockChatroomUserResult;
import io.rong.models.chatroom.Member;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 *
 * 聊天室封禁服务
 * docs: "http://www.rongcloud.cn/docs/server.html#chatroom_user_block"
 *
 * */
public class Block {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "chatroom/block";
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
     * 添加封禁聊天室成员方法
     *
     * @param  chatroom:封禁的聊天室信息，其中聊天室 Id。（必传）,用户 Id。（必传支持多个最多20个）
     * @param  minute:封禁时长，以分钟为单位，最大值为43200分钟。（必传）
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult add(ChatRoom chatroom, Integer minute) throws Exception {
        String message = CommonUtil.checkFiled(chatroom,PATH, CheckMethod.ADD);
        if(null != message){
            return (CodeSuccessResult)GsonUtil.fromJson(message,CodeSuccessResult.class);
        }
        message = CommonUtil.checkParam("minute",minute,PATH,CheckMethod.ADD);
        if(null != message){
            return (CodeSuccessResult)GsonUtil.fromJson(message,CodeSuccessResult.class);
        }

        StringBuilder sb = new StringBuilder();
        Member[] users = chatroom.getMembers();
        for(Member user : users){
            sb.append("&userId=").append(URLEncoder.encode(user.getId().toString(), UTF8));
        }

        sb.append("&chatroomId=").append(URLEncoder.encode(chatroom.getId().toString(), UTF8));
        sb.append("&minute=").append(URLEncoder.encode(minute.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/user/block/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
    }

    /**
     * 查询被封禁聊天室成员方法
     *
     * @param  chatroomId:聊天室 Id。（必传）
     *
     * @return ListBlockChatroomUserResult
     **/
    public ListBlockChatroomUserResult getList(String chatroomId) throws Exception {
        String message = CommonUtil.checkParam("id",chatroomId,PATH,CheckMethod.GETLIST);
        if(null != message){
            return (ListBlockChatroomUserResult)GsonUtil.fromJson(message,ListBlockChatroomUserResult.class);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/user/block/list.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ListBlockChatroomUserResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GETLIST,HttpUtil.returnResult(conn)), ListBlockChatroomUserResult.class);
    }

    /**
     * 移除封禁聊天室成员方法
     *
     * @param  chatroom: 封禁的聊天室信息 其中聊天室 Id。（必传）,用户 Id。（必传）
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult remove(ChatRoom chatroom) throws Exception {
        String message = CommonUtil.checkFiled(chatroom,PATH,CheckMethod.REMOVE);
        if(null != message){
            return (CodeSuccessResult)GsonUtil.fromJson(message,CodeSuccessResult.class);
        }

        StringBuilder sb = new StringBuilder();
        Member[] users = chatroom.getMembers();
        for(Member user : users){
            sb.append("&userId=").append(URLEncoder.encode(user.getId().toString(), UTF8));
        }
        sb.append("&chatroomId=").append(URLEncoder.encode(chatroom.getId().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/user/block/rollback.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
    }
}
