package io.rong.methods.chatroom.priority;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.models.CheckMethod;
import io.rong.models.CodeSuccessResult;
import io.rong.models.CommonConstrants;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
/**
 *
 * 聊天室优先级服务
 * docs: "http://www.rongcloud.cn/docs/server.html#chatroom_message_priority"
 *
 * */
public class Priority {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "chatroom/priority";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Priority(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 添加聊天室消息优先级方法
     *
     * @param  objectName:低优先级的消息类型，每次最多提交 5 个，设置的消息类型最多不超过 20 个。（必传）
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult add(String[] objectName) throws Exception {
        String message = CommonUtil.checkParam("objectName",objectName,"chatroom/priority","priority", CheckMethod.ADD);
        if(null != message){
            return (CodeSuccessResult)GsonUtil.fromJson(message,CodeSuccessResult.class);
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i< objectName.length; i++) {
            String child  = objectName[i];
            sb.append("&objectName=").append(URLEncoder.encode(child, UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/message/priority/add.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.ADD,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
    }

    /**
     * 删除聊天室优先级啊方法
     *
     * @param  objectName:要销毁的聊天室 Id。（必传）
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult remove(String[] objectName) throws Exception {
        String message = CommonUtil.checkParam("objectName",objectName,"chatroom/keepalive","priority",CheckMethod.REMOVE);
        if(null != message){
            return (CodeSuccessResult)GsonUtil.fromJson(message,CodeSuccessResult.class);
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i< objectName.length; i++) {
            String child  = objectName[i];
            sb.append("&chatroomId=").append(URLEncoder.encode(child, UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "chatroom/message/priority/remove.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.REMOVE,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
    }
    /**
     * 查询聊天室优先级方法
     *
     *
     * @return CodeSuccessResult
     **/
    public CodeSuccessResult get() throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), appKey, appSecret, "/chatroom/message/priority/query.json", "application/x-www-form-urlencoded");

        return (CodeSuccessResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.GET,HttpUtil.returnResult(conn)), CodeSuccessResult.class);
    }
}
