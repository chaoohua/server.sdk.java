package io.rong.example.user;

import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.*;
import io.rong.models.response.*;
import io.rong.models.user.UserModel;

/**
 * Demo class
 *
 * @author hc
 * @date 2017/12/30
 */
public class UserExample {
    /*private static final String appKey = "e0x9wycfx7flq";
    private static final String appSecret = "STCevzDS6Xy18n";
    */
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "8luwapkv8s7pl";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "lmkgpHuXezTjV2";

    private static final String api = "http://192.168.155.13:9200";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);
        User User = rongCloud.user;

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/user/user.html#register
         *
         * 注册用户，生成用户在融云的唯一身份标识 Token
         */
        String appKey = "e0x9wycfx7flq";
        String appSecret = "STCevzDS6Xy18n";
        UserModel user = new UserModel()
                .setId("userId1")
                .setName("username")
                .setPortrait("http://www.rongcloud.cn/images/logo.png");
        TokenResult result = User.register(user);
        System.out.println("getToken:  " + result.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/user/user.html#refresh
         *
         * 刷新用户信息方法
         */
        Result refreshResult = (ResponseResult)User.refresh(user);
        System.out.println("refresh:  " + refreshResult.toString());

    }
}
