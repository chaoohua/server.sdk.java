package io.rong.example.push;

import io.rong.RongCloud;
import io.rong.models.push.PushMessage;
import io.rong.models.push.UserTag;
import io.rong.models.response.ResponseResult;
import io.rong.util.GsonUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.Assert.assertEquals;

public class PushExample {
    private static final String JSONFILE = PushExample.class.getClassLoader().getResource("jsonsource").getPath()+"/";
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "8luwapkv8s7pl";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "lmkgpHuXezTjV2";

    /**
     * 本地调用测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);


        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/push/push.html#setUserTag
         *
         * 为用户打标签测试
         **/
         Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(JSONFILE + "UserTag.json"));
            UserTag setUserPushTagUserTag = (UserTag) GsonUtil.fromJson(reader, UserTag.class);
            ResponseResult pushSetUserPushTagResult = rongCloud.push.setUserTag(setUserPushTagUserTag);

            System.out.println("setUserPushTag:  " + pushSetUserPushTagResult.toString());
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }


        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/push/push.html#push
         *
         * 广播消息方法（fromuserid 和 message为null即为不落地的push）
         */
        try {
            reader = new InputStreamReader(new FileInputStream(JSONFILE + "PushMessage.json"));
            PushMessage broadcastPushPushMessage = (PushMessage) GsonUtil.fromJson(reader, PushMessage.class);
            ResponseResult pushBroadcastPushResult = rongCloud.push.send(broadcastPushPushMessage);

            System.out.println("broadcastPush:  " + pushBroadcastPushResult.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }


    }

}
