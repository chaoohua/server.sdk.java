package io.rong.example.sms;

import io.rong.RongCloud;
import io.rong.models.response.SMSImageCodeResult;
import io.rong.models.response.SMSSendCodeResult;
import io.rong.models.response.SMSVerifyCodeResult;
import io.rong.models.sms.SmsModel;
import io.rong.models.user.UserModel;

import java.io.Reader;

public class SmsExample {
    /**
     * 本地调用测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String appKey = "appkey";//替换成您的appkey
        String appSecret = "secret";//替换成匹配上面key的secret

        Reader reader = null;
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);


        System.out.println("************************User********************");
        UserModel user = new UserModel("userId1", "username", "http://www.rongcloud.cn/images/logo.png");


        SmsModel sms = new SmsModel("13500000000", "dsfdsfd", "86", "1408706337", "1408706337");

        System.out.println("************************SMS********************");
        // 获取图片验证码方法
        SMSImageCodeResult sMSGetImageCodeResult = rongCloud.sms.code.getImage("app-key");
        System.out.println("getImageCode:  " + sMSGetImageCodeResult.toString());

        // 发送短信验证码方法。
        SMSSendCodeResult sMSSendCodeResult = rongCloud.sms.code.send(sms);
        System.out.println("sendCode:  " + sMSSendCodeResult.toString());

        // 验证码验证方法
        SMSVerifyCodeResult sMSVerifyCodeResult = rongCloud.sms.code.verify("2312312", "2312312");
        System.out.println("verifyCode:  " + sMSVerifyCodeResult.toString());


        // 验证码验证方法
        SMSSendCodeResult smsNotifyResult = rongCloud.sms.notify.send(sms,"aa","bb","cc");
        System.out.println("notify:  " + sMSVerifyCodeResult.toString());
    }
}
