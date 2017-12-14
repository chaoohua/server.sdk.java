package io.rong.methods.chatroom.gag.member;

import io.rong.models.CodeSuccessResult;
import io.rong.models.ListGagChatroomUserResult;
import io.rong.models.chatroom.ChatRoom;
import io.rong.models.chatroom.Member;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Members {
    private static final String UTF8 = "UTF-8";
    private String appKey;
    private String appSecret;

    public Members(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }

    /**
     * 添加禁言聊天室成员方法（在 App 中如果不想让某一用户在聊天室中发言时，可将此用户在聊天室中禁言，被禁言用户可以接收查看聊天室中用户聊天信息，但不能发送消息.）
     * @param  chatRoom:封禁的聊天室信息，其中聊天室 Id。（必传）,用户 Id。（必传支持多个最多20个）
     * @param  minute:禁言时长，以分钟为单位，最大值为43200分钟。（必传）
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult add(ChatRoom chatRoom, String minute) throws Exception {

        if (chatRoom.getId() == null) {
            throw new IllegalArgumentException("Paramer 'userId' is required");
        }

        if (chatRoom.getMembers() == null) {
            throw new IllegalArgumentException("Paramer 'chatroomId' is required");
        }

        if (minute == null) {
            throw new IllegalArgumentException("Paramer 'minute' is required");
        }

        StringBuilder sb = new StringBuilder();
        Member[] users = chatRoom.getMembers();
        for(Member user : users){
            sb.append("&userId=").append(URLEncoder.encode(user.getId().toString(), UTF8));
        }
        sb.append("&chatroomId=").append(URLEncoder.encode(chatRoom.getId().toString(), UTF8));
        sb.append("&minute=").append(URLEncoder.encode(minute.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/user/gag/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
    }

    /**
     * 查询被禁言聊天室成员方法
     *
     * @param  chatroomId:聊天室 Id。（必传）
     *
     * @return ListGagChatroomUserResult
     **/
    public ListGagChatroomUserResult getList(String chatroomId) throws Exception {
        if (chatroomId == null) {
            throw new IllegalArgumentException("Paramer 'chatroomId' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&chatroomId=").append(URLEncoder.encode(chatroomId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/user/gag/list.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ListGagChatroomUserResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), ListGagChatroomUserResult.class);
    }

    /**
     * 移除禁言聊天室成员方法
     *
     * @param  chatRoom:封禁的聊天室信息，其中聊天室 Id。（必传）,用户 Id。（必传支持多个最多20个）
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult remove(ChatRoom chatRoom) throws Exception {
        if (chatRoom.getMembers() == null) {
            throw new IllegalArgumentException("Paramer 'userId' is required");
        }

        if (chatRoom.getId() == null) {
            throw new IllegalArgumentException("Paramer 'chatroomId' is required");
        }

        StringBuilder sb = new StringBuilder();
        Member[] users = chatRoom.getMembers();
        for(Member user : users){
            sb.append("&userId=").append(URLEncoder.encode(user.getId().toString(), UTF8));
        }
        sb.append("&chatroomId=").append(URLEncoder.encode(chatRoom.getId().toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/chatroom/user/gag/rollback.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), CodeSuccessResult.class);
    }
}
