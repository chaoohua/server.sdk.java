package io.rong.example.User;

import io.rong.RongCloud;
import io.rong.models.*;
import io.rong.models.user.UserInfo;
import io.rong.models.user.UserResponseResult;

public class UserExample {

    public static void main(String[] args) throws Exception {

        String appKey = "appkey";//替换成您的appkey

        String appSecret = "secret";//替换成匹配上面key的secret

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        UserInfo user = new UserInfo("userId1", "username", "http://www.rongcloud.cn/images/logo.png");

        System.out.println("************************user getToken********************");
        getToken(rongCloud);

        System.out.println("************************user userRefresh********************");
        userRefresh(rongCloud);

        System.out.println("************************user checkOnline********************");
        checkOnline(rongCloud);

        System.out.println("************************user userUnBlock********************");
        userUnBlock(rongCloud);

        System.out.println("************************user userQueryBlock*******************");
        userQueryBlock(rongCloud);

        System.out.println("************************user addBlacklist*******************");
        addBlacklist(rongCloud);

    }

    /**
     *
     * 检查用户在线状态 方法
     */
    public static void checkOnline(RongCloud rongCloud) throws Exception {
        CheckOnlineResult userCheckOnlineResult = rongCloud.user.checkOnline(null);
        System.out.println("checkOnline:  " + userCheckOnlineResult.toString());
    }


    /**
     *
     * 获取 Token 方法
     */
    public static void getToken(RongCloud rongCloud) throws Exception {
        UserInfo user = new UserInfo("userId1", "username", "http://www.rongcloud.cn/images/logo.png");
        TokenResult userGetTokenResult = rongCloud.user.getToken(user);
        System.out.println("getToken:  " + userGetTokenResult.toString());
    }

    /**
     *  刷新用户信息方法
     */
    public static void userRefresh(RongCloud rongCloud) throws Exception {
        UserInfo user = new UserInfo("userId1", "username", "http://www.rongcloud.cn/images/logo.png");
        UserResponseResult userRefreshResult = (UserResponseResult)rongCloud.user.refresh(user);
        System.out.println("refresh:  " + userRefreshResult.toString());

    }


    /**
     *解除用户封禁方法（每秒钟限 100 次）
     */
    public static void userUnBlock(RongCloud rongCloud) throws Exception {
        UserResponseResult userUnBlockResult = (UserResponseResult)rongCloud.user.unBlock("userId2");
        System.out.println("unBlock:  " + userUnBlockResult.toString());
    }
    /**
     *  获取被封禁用户方法（每秒钟限 100 次）
     */
    public static void userQueryBlock(RongCloud rongCloud) throws Exception {

        QueryBlockUserResult userQueryBlockResult = rongCloud.user.queryBlock();
        System.out.println("queryBlock:  " + userQueryBlockResult.toString());

    }


    /**
     * 添加用户到黑名单方法（每秒钟限 100 次）
     */
    public static void addBlacklist(RongCloud rongCloud) throws Exception {

        String appKey = "e0x9wycfx7flq";
        String appSecret = "SECRETTEST";
//        String uri = "http://192.168.155.13:9200" + uriStr;
//        String appKey = "appkey";//替换成您的appkey
//
//        String appSecret = "secret";//替换成匹配上面key的secret

        RongCloud rongCloud1 = RongCloud.getInstance(appKey, appSecret);
        UserResponseResult userAddBlacklistResult = (UserResponseResult)rongCloud1.user.addBlacklist("userId1", "userId2");
        System.out.println("addBlacklist:  " + userAddBlacklistResult.toString());
    }
    /**
     *  获取某用户的黑名单列表方法（每秒钟限 100 次）
     */
    public static void queryBlacklist(RongCloud rongCloud) throws Exception {

        QueryBlacklistUserResult userQueryBlacklistResult = rongCloud.user.queryBlacklist("userId1");
        System.out.println("queryBlacklist:  " + userQueryBlacklistResult.toString());

    }


    /**
     *
     *  从黑名单中移除用户方法（每秒钟限 100 次）
     */
    public static void removeBlacklist(RongCloud rongCloud) throws Exception {
        UserResponseResult userRemoveBlacklistResult = (UserResponseResult)rongCloud.user.removeBlacklist("userId1", "userId2");
        System.out.println("removeBlacklist:  " + userRemoveBlacklistResult.toString());
    }
}
