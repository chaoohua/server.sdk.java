package io.rong.models;

import io.rong.util.GsonUtil;

public class BlackList extends BaseModel{
    // 黑名单用户列表。
    String[] users;

    public BlackList(Integer code, String errorMessage) {
        super(code, errorMessage);
    }
    public BlackList(Integer code, String[] users, String errorMessage) {
        super(code, errorMessage);
        this.code = code;
        this.users = users;
        this.errorMessage = errorMessage;
    }
    /**
     * 设置users
     *
     */
    public void setUsers(String[] users) {
        this.users = users;
    }

    /**
     * 获取users
     *
     * @return String[]
     */
    public String[] getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, BlackList.class);
    }

}
