package io.rong.models.group.gag;

import io.rong.models.group.GroupModel;

public class GagModel extends GroupModel{

    Integer munite;

    public GagModel(Integer munite) {
        this.munite = munite;
    }
    /**
     * 构造方法
     *
     * @param id
     * @param merberIds
     * @param name
     * @param munite 禁言时间
     */
    public GagModel(String id, String[] merberIds, String name, Integer munite) {
        super(id, merberIds, name);
        this.munite = munite;
    }

    @Override
    public Integer getMunite() {
        return this.munite;
    }

    @Override
    public GagModel setMunite(Integer munite) {
        this.munite = munite;
        return this;
    }
}
