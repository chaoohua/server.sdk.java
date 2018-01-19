package io.rong.example.user;

import io.rong.RongCloud;
import io.rong.models.*;
import io.rong.models.user.UserModel;

/**
 * Demo class
 *
 * @author hc
 * @date 2017/12/30
 */
public class UserExample {

    public static void main(String[] args) throws Exception {

       /* String appKey = "appkey";//替换成您的appkey

        String appSecret = "secret";//替换成匹配上面key的secret

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);*/
        String appKey = "e0x9wycfx7flq";
        String appSecret = "STCevzDS6Xy18n";
        String api = "http://192.168.155.13:9200";

        /*String appKey = "8luwapkvucoil";
        String appSecret = "y0icysjl4h3LWz";
        String api = "http://api.cn.ronghub.com";*/

        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        UserModel user = new UserModel("userId1", "username", "http://www.rongcloud.cn/images/logo.png");

        //System.out.println("************************user getToken********************");
        getToken(rongCloud);

        //System.out.println("************************user userRefresh********************");
        //userRefresh(rongCloud);
        //GTzsllarY0ybahv8n57Z8/8d3dyrkUjx04znTXDsPzyuKtQw+URw2sGGaiTPk1Y+xLtzdS48UrgFgRC9PY5fVILPgPfFBJHe"

        ///System.out.println("************************user checkOnline********************");
        //checkOnline(rongCloud);

       // userAddBlock(rongCloud);

        //System.out.println("************************user userUnBlock********************");
        //userUnBlock(rongCloud);

        //System.out.println("************************user userQueryBlock*******************");
        //userQueryBlock(rongCloud);

        //System.out.println("************************user addBlacklist*******************");
        //addBlacklist(rongCloud);
        //removeBlacklist(rongCloud);
        //queryBlacklist(rongCloud);


    }

    /**
     *
     * 检查用户在线状态 方法
     */
    public static void checkOnline(RongCloud rongCloud) throws Exception {
        UserModel user = new UserModel("userId",null,null);
        CheckOnlineResult userCheckOnlineResult = rongCloud.user.onlineStatus.check(user);
        System.out.println("checkOnline:  " + userCheckOnlineResult.toString());
    }


    /**
     *
     * 获取 Token 方法
     */
    public static void getToken(RongCloud rongCloud) throws Exception {
        UserModel user = new UserModel("userId1华发商都发货速度符合实际发货就是房价是否就是房价是否就是分数就会飞机是否就是方法就是发生", "username", "http://www.rongcloud.cn/images/logo.png");
        TokenResult userGetTokenResult = rongCloud.user.getToken(user);
        System.out.println("getToken:  " + userGetTokenResult.toString());
    }

    /**
     *  刷新用户信息方法
     */
    public static void userRefresh(RongCloud rongCloud) throws Exception {
        UserModel user = new UserModel("userId1", "username", "http://www.rongcloud.cn/images/logo.png");
        ResponseResult userRefreshResult = (ResponseResult)rongCloud.user.refresh(user);
        System.out.println("refresh:  " + userRefreshResult.toString());

    }

    /**
     *解除用户封禁方法（每秒钟限 100 次）
     */
    public static void userAddBlock(RongCloud rongCloud) throws Exception {
        ResponseResult userUnBlockResult = (ResponseResult)rongCloud.user.block.add("userId2",10000000);
        System.out.println("userAddBlock:  " + userUnBlockResult.toString());
    }
    /**
     *解除用户封禁方法（每秒钟限 100 次）
     */
    public static void userUnBlock(RongCloud rongCloud) throws Exception {
        ResponseResult userUnBlockResult = (ResponseResult)rongCloud.user.block.remove("userId2");
        System.out.println("unBlock:  " + userUnBlockResult.toString());
    }
    /**
     *  获取被封禁用户方法（每秒钟限 100 次）
     */
    public static void userQueryBlock(RongCloud rongCloud) throws Exception {

        QueryBlockUserResult userQueryBlockResult = rongCloud.user.block.getList();
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
        ResponseResult userAddBlacklistResult = (ResponseResult)rongCloud1.user.blackList.add("userId1", "userId2");
        System.out.println("addBlacklist:  " + userAddBlacklistResult.toString());
    }
    /**
     *  获取某用户的黑名单列表方法（每秒钟限 100 次）
     */
    public static void queryBlacklist(RongCloud rongCloud) throws Exception {

        QueryBlacklistUserResult userQueryBlacklistResult = rongCloud.user.blackList.query("userId1");
        System.out.println("queryBlacklist:  " + userQueryBlacklistResult.toString());

    }


    /**
     *
     *  从黑名单中移除用户方法（每秒钟限 100 次）
     */
    public static void removeBlacklist(RongCloud rongCloud) throws Exception {
        ResponseResult userRemoveBlacklistResult = (ResponseResult)rongCloud.user.blackList.remove("userId1", "userId2");
        System.out.println("removeBlacklist:  " + userRemoveBlacklistResult.toString());
    }
}
