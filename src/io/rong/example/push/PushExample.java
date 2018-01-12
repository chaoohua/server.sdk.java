package io.rong.example.push;

import io.rong.RongCloud;
import io.rong.example.Example;
import io.rong.models.CodeSuccessResult;
import io.rong.models.PushMessage;
import io.rong.models.UserTag;
import io.rong.util.GsonUtil;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class PushExample {
    private static final String JSONFILE = Example.class.getClassLoader().getResource("jsonsource").getPath()+"/";
    /**
     * 本地调用测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
       /* String appKey = "appkey";//替换成您的appkey
        String appSecret = "secret";//替换成匹配上面key的secret
*/
        Reader reader = null;
        String appKey = "e0x9wycfx7flq";
        String appSecret = "STCevzDS6Xy18n";
        String api = "http://192.168.155.13:9200";
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        System.out.println("************************Push********************");
        // 添加 Push 标签方法
        try {
            reader = new InputStreamReader(new FileInputStream(JSONFILE + "UserTag.json"));
            UserTag setUserPushTagUserTag = (UserTag) GsonUtil.fromJson(reader, UserTag.class);
            CodeSuccessResult pushSetUserPushTagResult = rongCloud.push.setUserTag(setUserPushTagUserTag);
            System.out.println("setUserPushTag:  " + pushSetUserPushTagResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }

        // 广播消息方法（fromuserid 和 message为null即为不落地的push）
        try {
            reader = new InputStreamReader(new FileInputStream(JSONFILE + "PushMessage.json"));
            PushMessage broadcastPushPushMessage = (PushMessage) GsonUtil.fromJson(reader, PushMessage.class);
            CodeSuccessResult pushBroadcastPushResult = rongCloud.push.send(broadcastPushPushMessage);
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
