package io.rong.models.group;

/**
 * 群组数据模型
 **/
public class GroupModel {
    /**
     * 群组id
     **/
    public String id;
    /**
     * 群组成员
     **/
    public String[] merberIds;
    /**
     * 群组名
     **/
    public String name;

    public GroupModel() {
    }

    /**
     * 构造方法
     *
     * @param id 群组id
     * @param merberIds 群组成员
     * @param name 群名
     */
    public GroupModel(String id, String[] merberIds, String name) {
        this.merberIds = merberIds;
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public GroupModel setId(String id) {
        this.id = id;
        return this;
    }

    public String[] getMerberIds() {
        return this.merberIds;
    }

    public GroupModel setMerberIds(String[] merberIds) {
        this.merberIds = merberIds;
        return this;
    }


    public String getName() {
        return this.name;
    }

    public GroupModel setName(String ame) {
        this.name = name;
        return this;
    }
}
