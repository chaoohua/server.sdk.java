package io.rong.models.chatroom;

import io.rong.util.GsonUtil;

public class Member {
    // 聊天室用户Id。
    public String id;
    // 加入聊天室时间。
    public String time;
    /**
     * 聊天室ID
     * */
    public String chatroomId;
    public Member() {
        super();
    }
    public Member(String id, String time) {
        this.id = id;
        this.time = time;
    }

    public Member(String id, String chatroomId, String time) {
        this.id = id;
        this.chatroomId = chatroomId;
        this.time = time;
    }

    /**
     * 设置id
     *
     */
    public Member setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 获取id
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * 设置time
     *
     */
    public Member setTime(String time) {
        this.time = time;
        return this;
    }

    /**
     * 获取time
     *
     * @return String
     */
    public String getTime() {
        return time;
    }

    public String getChatroomId() {
        return this.chatroomId;
    }

    public Member setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, Member.class);
    }
}
