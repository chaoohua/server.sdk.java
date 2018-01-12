package io.rong.methods.sms.notify;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.models.CommonConstrants;
import io.rong.models.SMSSendCodeResult;
import io.rong.models.sms.SmsModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Notify {

    private static final String UTF8 = "UTF-8";
    private static final String PATH = "sms/notify";
    private static String method = "";
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }
    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Notify(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;

    }
    /**
     * 发送通知类短信方法。注：发送的所有短信内容（包括短信通知和短信验证码）不能超过 210 个字。
     *
     * @param sms   mobile,templateId,region,(必传)
     * @return SMSSendCodeResult
     **/
    public SMSSendCodeResult send(SmsModel sms, String p1, String p2, String p3) throws Exception {
        if (sms.mobile == null) {
            throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL,"/sendNotify","Paramer 'sms.mobile' is required");
        }

        if (sms.templateId == null) {
            throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL,"/sendNotify","Paramer 'sms.templateId' is required");
        }

        if (sms.region == null) {
            throw new ParamException(CommonConstrants.RCLOUD_PARAM_NULL,"/sendNotify","Paramer 'sms.region' is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("&mobile=").append(URLEncoder.encode(sms.mobile.toString(), UTF8));
        sb.append("&templateId=").append(URLEncoder.encode(sms.templateId.toString(), UTF8));
        sb.append("&region=").append(URLEncoder.encode(sms.region.toString(), UTF8));

        if (p1 != null) {
            sb.append("&p1=").append(URLEncoder.encode(p1, UTF8));
        }

        if (p2 != null) {
            sb.append("&p2=").append(URLEncoder.encode(p2, UTF8));
        }

        if (p2 != null) {
            sb.append("&p3=").append(URLEncoder.encode(p3, UTF8));
        }

        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getSmsHostType(), appKey, appSecret, "/sendNotify.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);

        return (SMSSendCodeResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,method,HttpUtil.returnResult(conn)), SMSSendCodeResult.class);
    }
}
