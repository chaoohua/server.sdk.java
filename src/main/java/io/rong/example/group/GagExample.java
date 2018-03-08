package io.rong.example.group;

import io.rong.RongCloud;
import io.rong.methods.group.gag.Gag;
import io.rong.models.Result;
import io.rong.models.group.GroupModel;
import io.rong.models.response.ListGagGroupUserResult;

public class GagExample {
    private static final String JSONFILE = GroupExample.class.getClassLoader().getResource("jsonsource").getPath()+"/";
    private static final String appKey = "z3v5yqkbvy9f0";
    private static final String appSecret = "plhr2PA386a";
    private static final RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
    private static final Gag Gag = rongCloud.group.gag;

    /**
     * 本地调用测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/group/gag.html#add
         * 添加禁言群成员方法
         */
        String[] memberIds = {"userId1","userid2","userId3"};
        GroupModel group = new GroupModel()
                .setMerberIds(memberIds)
                .setMunite(5);
        Result result = (Result)rongCloud.group.gag.add(group);
        System.out.println("group.gag.add:  " + result.toString());



        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/group/gag.html#getList
         * 查询被禁言群成员
         */
        ListGagGroupUserResult groupLisGagUserResult = rongCloud.group.gag.getList("25");
        System.out.println("group.gag.getList:  " + groupLisGagUserResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/group/gag.html#remove
         * 移除禁言群成员
         */
        group = new GroupModel().setMerberIds(memberIds);

        Result groupRollBackGagUserResult = (Result)rongCloud.group.gag.remove(group);
        System.out.println("group.gag.remove:  " + groupRollBackGagUserResult.toString());

    }
}
