package io.rong.example.user;

import io.rong.RongCloud;
import io.rong.methods.user.blacklist.Blacklist;
import io.rong.models.Result;
import io.rong.models.response.BlackListResult;

/**
 * @author RongCloud
 */
public class BlackListExample {
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "8luwapkv8s7pl";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "lmkgpHuXezTjV2";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        Blacklist BlackList = rongCloud.user.blackList;

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/user/black.html#add
         * 添加用户到黑名单方法
         */
        Result userAddBlacklistResult = (Result)BlackList.add("userId1", "userId2");
        System.out.println("addBlacklist:  " + userAddBlacklistResult.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/user/black.html#getList
         * 获取某用户的黑名单列表方法
         */
        BlackListResult result = BlackList.getList("userId1");
        System.out.println("query blacklist:  " + result.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/user/black.html#remove
         * 从黑名单中移除用户方法
         */
        Result removeResult = (Result)BlackList.remove("userId1", "userId2");
        System.out.println("remove blacklist:  " + removeResult.toString());

    }

}
