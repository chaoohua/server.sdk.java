package io.rong.models.group;

import io.rong.models.BaseParams;

/**
 * 创建群组方法参数体（创建群组，并将用户加入该群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人，App 内的群组数量没有限制.注：其实本方法是加入群组方法 /group/join 的别名。）
 **/
public class GroupModel extends BaseParams{

    //群组id
    public String id;
    //userId:要加入群的用户 Id，可提交多个，最多不超过 1000 个。
    public String[] merberIds;
    //群组 Id 对应的名称.
    public String name;
    /**
     * 构造方法
     *
     * @param id
     * @param merberIds
     * @param name
     */
    public GroupModel(String id, String[] merberIds, String name) {
        this.merberIds = merberIds;
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getMerberIds() {
        return this.merberIds;
    }

    public void setMerberIds(String[] merberIds) {
        this.merberIds = merberIds;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String ame) {
        this.name = name;
    }
}
