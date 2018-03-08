package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;

public class BlackListResult extends Result {
    // 黑名单用户列表。
    String[] users;

    public BlackListResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }
    public BlackListResult(Integer code, String[] users, String errorMessage) {
        super(code, errorMessage);
        this.code = code;
        this.users = users;
        this.msg = errorMessage;
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
        return GsonUtil.toJson(this, BlackListResult.class);
    }

}
