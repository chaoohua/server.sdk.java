package io.rong.models.group.gag;

import io.rong.models.group.GroupModel;

public class GagModel extends GroupModel{

    String munite;
    /**
     * 构造方法
     *
     * @param id
     * @param merberIds
     * @param groupId
     * @param name
     * @param munite 禁言时间
     */
    public GagModel(String id, String[] merberIds, String groupId, String name, String munite) {
        super(groupId, merberIds, name);

        this.munite = munite;
    }

    public String getMunite() {
        return this.munite;
    }

    public void setMunite(String munite) {
        this.munite = munite;
    }
}
