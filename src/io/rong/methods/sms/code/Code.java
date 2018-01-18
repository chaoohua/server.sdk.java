package io.rong.methods.sms.code;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.models.*;
import io.rong.models.sms.SmsModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Code {

	private static final String UTF8 = "UTF-8";
	private static final String PATH = "sms/code";
	private String appKey;
	private String appSecret;
	private RongCloud rongCloud;

	public RongCloud getRongCloud() {
		return rongCloud;
	}
	public void setRongCloud(RongCloud rongCloud) {
		this.rongCloud = rongCloud;
	}
	public Code(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;

	}

	/**
	 * 获取图片验证码方法 
	 *
	 * @param  appKey:应用Id
	 *
	 * @return SMSImageCodeResult
	 **/
	public SMSImageCodeResult getImage(String appKey) throws Exception {
		if (appKey == null) {
			throw new ParamException("Paramer 'appKey' is required");
		}

		StringBuilder sb = new StringBuilder(rongCloud.getSmsHostType().getStrType()+"/getImgCode.json");
		sb.append("?appKey=").append(URLEncoder.encode(appKey, UTF8));

		HttpURLConnection conn = HttpUtil.CreateGetHttpConnection(sb.toString());

		return (SMSImageCodeResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH, CheckMethod.GET_IMAGE,HttpUtil.returnResult(conn)), SMSImageCodeResult.class);
	}

	/**
	 * 发送短信验证码方法。
	 *
	 * @param sms   mobile,templateId,region,(必传)
	 * @return SMSSendCodeResult
	 **/
	public SMSSendCodeResult send(SmsModel sms) throws Exception {
		//需要校验的字段
		String[] fileds = {"mobile","templateId","region"};
		String message = CommonUtil.checkFiled(fileds,sms,PATH,"sms",CheckMethod.SEND);
		if(null != message){
			return (SMSSendCodeResult)GsonUtil.fromJson(message,SMSSendCodeResult.class);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("&mobile=").append(URLEncoder.encode(sms.mobile.toString(), UTF8));
		sb.append("&templateId=").append(URLEncoder.encode(sms.templateId.toString(), UTF8));
		sb.append("&region=").append(URLEncoder.encode(sms.region.toString(), UTF8));

		if (sms.verifyId != null) {
			sb.append("&verifyId=").append(URLEncoder.encode(sms.verifyId.toString(), UTF8));
		}

		if (sms.verifyCode != null) {
			sb.append("&verifyCode=").append(URLEncoder.encode(sms.verifyCode.toString(), UTF8));
		}
		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getSmsHostType(), appKey, appSecret, "/sendCode.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);

		return (SMSSendCodeResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.SEND,HttpUtil.returnResult(conn)), SMSSendCodeResult.class);
	}

	/**
	 * 验证码验证方法
	 *
	 * @param  sessionId:短信验证码唯一标识，在发送短信验证码方法，返回值中获取。（必传）
	 * @param  code:短信验证码内容。（必传）
	 *
	 * @return SMSVerifyCodeResult
	 **/
	public SMSVerifyCodeResult verify(String sessionId, String code) throws Exception {
		String message = CommonUtil.checkParam("sessionId",sessionId,PATH,"sms",CheckMethod.VERIFY);
		if(null != message){
			return (SMSVerifyCodeResult)GsonUtil.fromJson(message,SMSVerifyCodeResult.class);
		}
		message = CommonUtil.checkParam("code",code,PATH,"sms",CheckMethod.VERIFY);
		if(null != message){
			return (SMSVerifyCodeResult)GsonUtil.fromJson(message,SMSVerifyCodeResult.class);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("&sessionId=").append(URLEncoder.encode(sessionId.toString(), UTF8));
		sb.append("&code=").append(URLEncoder.encode(code.toString(), UTF8));
		String body = sb.toString();
		if (body.indexOf("&") == 0) {
			body = body.substring(1, body.length());
		}

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getSmsHostType(), appKey, appSecret, "/verifyCode.json", "application/x-www-form-urlencoded");
		HttpUtil.setBodyParameter(body, conn);

		return (SMSVerifyCodeResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.VERIFY,HttpUtil.returnResult(conn)), SMSVerifyCodeResult.class);
	}

	 
}