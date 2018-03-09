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
    private RongCloud rongCloud ;

    @Before
    public void setUp() throws Exception {
       /* String appKey = "e0x9wycfx7flq";
        String appSecret = "STCevzDS6Xy18n";
        String api = "http://192.168.155.13:9200";
        rongCloud = RongCloud.getInstance(appKey, appSecret,api);
*/

        String appKey = "z3v5yqkbvy9f0";
        String appSecret = "plhr2PA386a";

        rongCloud = RongCloud.getInstance(appKey, appSecret);

    }
    /**
     * 为用户打标签测试
     */
    @Test
    public void test_setUserTag() throws Exception {
        Reader reader = null;
        // 添加 Push 标签方法
        try {
            reader = new InputStreamReader(new FileInputStream(JSONFILE + "UserTag.json"));
            UserTag setUserPushTagUserTag = (UserTag) GsonUtil.fromJson(reader, UserTag.class);
            ResponseResult pushSetUserPushTagResult = rongCloud.push.setUserTag(setUserPushTagUserTag);

            assertEquals("200",pushSetUserPushTagResult.getCode().toString());
            System.out.println("setUserPushTag:  " + pushSetUserPushTagResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }

    }

    /**
     * 广播push测试
     */
    @Test
    public void test_send() throws Exception {
        Reader reader = null;
        // 广播消息方法（fromuserid 和 message为null即为不落地的push）
        try {
            reader = new InputStreamReader(new FileInputStream(JSONFILE + "PushMessage.json"));
            PushMessage broadcastPushPushMessage = (PushMessage) GsonUtil.fromJson(reader, PushMessage.class);
            ResponseResult pushBroadcastPushResult = rongCloud.push.send(broadcastPushPushMessage);

            System.out.println("broadcastPush:  " + pushBroadcastPushResult.toString());

            assertEquals("200",pushBroadcastPushResult.getCode().toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }
    }

    /**
     * 本地调用测试
     *
     * @param args
     * @throws Exception
     */
    /*public static void main(String[] args) throws Exception {
       *//* String appKey = "appkey";//替换成您的appkey
        String appSecret = "secret";//替换成匹配上面key的secret
*//*
        String appKey = "e0x9wycfx7flq";
        String appSecret = "STCevzDS6Xy18n";
        String api = "http://192.168.155.13:9200";
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        test_send(rongCloud);

        test_setUserTag(rongCloud);


    }*/

}
