package io.rong.methods.sms;

import io.rong.RongCloud;
import io.rong.exception.ParamException;
import io.rong.methods.sms.code.Code;
import io.rong.methods.sms.notify.Notify;
import io.rong.models.CommonConstrants;
import io.rong.models.SMSImageCodeResult;
import io.rong.models.SMSSendCodeResult;
import io.rong.models.SMSVerifyCodeResult;
import io.rong.models.sms.SmsModel;
import io.rong.util.GsonUtil;
import io.rong.util.HostType;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class SMS {

	private static final String UTF8 = "UTF-8";
	private String appKey;
	private String appSecret;
	private RongCloud rongCloud;
	public Code code;
	public Notify notify;

	public RongCloud getRongCloud() {
		return rongCloud;
	}
	public void setRongCloud(RongCloud rongCloud) {
		this.rongCloud = rongCloud;
	}
	public SMS(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.code = new Code(appKey,appSecret);
		this.notify = new Notify(appKey,appSecret);

	}
}