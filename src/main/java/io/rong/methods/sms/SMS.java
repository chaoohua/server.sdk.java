package io.rong.methods.sms;

import io.rong.RongCloud;
import io.rong.methods.sms.code.Code;
import io.rong.methods.sms.notify.Notify;

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
		code.setRongCloud(rongCloud);
		notify.setRongCloud(rongCloud);
	}
	public SMS(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		code = new Code(appKey,appSecret);
		notify = new Notify(appKey,appSecret);

	}
}