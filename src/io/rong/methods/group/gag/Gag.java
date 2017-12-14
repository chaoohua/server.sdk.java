package io.rong.methods.group.gag;

import io.rong.models.group.GroupModel;
import io.rong.models.group.GroupResponse;
import io.rong.models.ListGagGroupUserResult;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Gag {
    private static final String UTF8 = "UTF-8";
    private String appKey;
    private String appSecret;

    public Gag(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 添加禁言群成员方法（在 App 中如果不想让某一用户在群中发言时，可将此用户在群组中禁言，被禁言用户可以接收查看群组中用户聊天信息，但不能发送消息。）
     *
     * @param group:群组信息。（必传）
     * @param munite :禁言时间
     * @return GroupResponse
     **/
    public GroupResponse add(GroupModel group,String munite) throws Exception {
        if (group.getId() == null) {
            throw new IllegalArgumentException("Paramer 'userId' is required");
        }

        if (group.getMerberIds() == null) {
            throw new IllegalArgumentException("Paramer 'groupId' is required");
        }

        if (group.name == null) {
            throw new IllegalArgumentException("Paramer 'minute' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&userId=").append(URLEncoder.encode(group.getMerberIds().toString(), UTF8));
        sb.append("&groupId=").append(URLEncoder.encode(group.getId().toString(), UTF8));
        sb.append("&minute=").append(URLEncoder.encode(munite, UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/group/user/gag/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (GroupResponse) GsonUtil.fromJson(HttpUtil.returnResult(conn), GroupResponse.class);
    }

    /**
     * 查询被禁言群成员方法
     *
     * @param  groupId:群组Id。（必传）
     *
     * @return ListGagGroupUserResult
     **/
    public ListGagGroupUserResult getList(String groupId) throws Exception {
        if (groupId == null) {
            throw new IllegalArgumentException("Paramer 'groupId' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&groupId=").append(URLEncoder.encode(groupId.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/group/user/gag/list.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (ListGagGroupUserResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), ListGagGroupUserResult.class);
    }

    /**
     * 移除禁言群成员方法
     *
     * @param  group:群组（必传）
     *
     * @return CodeSuccessResult
     **/
    public GroupResponse remove(GroupModel group) throws Exception {
        if (group.merberIds == null) {
            throw new IllegalArgumentException("Paramer 'userId' is required");
        }

        if (group.id == null) {
            throw new IllegalArgumentException("Paramer 'groupId' is required");
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i< group.merberIds.length; i++) {
            String child  = group.merberIds[i];
            sb.append("&userId=").append(URLEncoder.encode(child, UTF8));
        }

        sb.append("&groupId=").append(URLEncoder.encode(group.id.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/group/user/gag/rollback.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (GroupResponse) GsonUtil.fromJson(HttpUtil.returnResult(conn), GroupResponse.class);
    }
}
