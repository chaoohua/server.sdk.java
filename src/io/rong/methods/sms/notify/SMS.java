package io.rong.methods.sms.notify;

import io.rong.models.SMSSendCodeResult;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class SMS {

	private static final String UTF8 = "UTF-8";
	private String appKey;
	private String appSecret;
	
	public SMS(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}
	/**
	 * 发送短信验证码方法。 
	 * 
	 * @param  mobile:接收短信验证码的目标手机号，每分钟同一手机号只能发送一次短信验证码，同一手机号 1 小时内最多发送 3 次。（必传）
	 * @param  templateId:短信模板 Id，在开发者后台->短信服务->服务设置->短信模版中获取。（必传）
	 * @param  region:手机号码所属国家区号，目前只支持中图区号 86）
	 * @param  p1:短信模板中，自定义变量值，如果在通知短信模板中定义了 {p1} 则在发送通知短信时必须传入此参数，替换模板中的 {p1}，否则此参数可以不传
	 * @param  p2:短信模板中，自定义变量值，如果在通知短信模板中定义了 {p2} 则在发送通知短信时必须传入此参数，替换模板中的 {p2}，否则此参数可以不传。
	 *
	 * @return SMSSendCodeResult
	 **/
	public SMSSendCodeResult send(String mobile, String templateId, String region, String p1, String p2) throws Exception {
		if (mobile == null) {
			throw new IllegalArgumentException("Paramer 'mobile' is required");
		}
		
		if (templateId == null) {
			throw new IllegalArgumentException("Paramer 'templateId' is required");
		}
		
		if (region == null) {
			throw new IllegalArgumentException("Paramer 'region' is required");
		}
		
	    StringBuilder sb = new StringBuilder();
	    sb.append("&mobile=").append(URLEncoder.encode(mobile.toString(), UTF8));
	    sb.append("&templateId=").append(URLEncoder.encode(templateId.toString(), UTF8));
	    sb.append("&region=").append(URLEncoder.encode(region.toString(), UTF8));
		String body = sb.toString();
	   	if (body.indexOf("&") == 0) {
	   		body = body.substring(1, body.length());
	   	}
	   	
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.SMS, appKey, appSecret, "/sendNotify.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);
	    
	    return (SMSSendCodeResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), SMSSendCodeResult.class);
	}
}