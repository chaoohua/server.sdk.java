package io.rong.models;

import io.rong.util.GsonUtil;

import java.util.List;

public class BlockUser extends Result {
    // 被封禁用户列表。
    List<BlockUsers> users;

    public BlockUser(Integer code, String errorMessage, List<BlockUsers> users) {
        super(code, errorMessage);
        this.users = users;
    }
    /**
     * 设置users
     *
     */
    public void setUsers(List<BlockUsers> users) {
        this.users = users;
    }

    /**
     * 获取users
     *
     * @return List<BlockUser>
     */
    public List<BlockUsers> getUsers() {
        return users;
    }
    @Override
    public String toString() {
        return GsonUtil.toJson(this, BlockUser.class);
    }

}
