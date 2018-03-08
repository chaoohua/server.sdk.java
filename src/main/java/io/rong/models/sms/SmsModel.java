package io.rong.models.sms;

import io.rong.messages.BaseMessage;

public class SmsModel {

    //接收短信验证码的目标手机号，每分钟同一手机号只能发送一次短信验证码，同一手机号 1 小时内最多发送 3 次。（必传）
    public String mobile;
    //短信模板 Id，在开发者后台->短信服务->服务设置->短信模版中获取。（必传）
    public String templateId;
    //手机号码所属国家区号，目前只支持中图区号 86）
    public String region;
    //图片验证标识 Id ，开启图片验证功能后此参数必传，否则可以不传。在获取图片验证码方法返回值中获取。
    public String verifyId;
    //图片验证码，开启图片验证功能后此参数必传，否则可以不传。
    public String verifyCode;

    public SmsModel() {
    }

    public SmsModel(String mobile, String templateId, String region, String verifyId, String verifyCode) {
        this.mobile = mobile;
        this.templateId = templateId;
        this.region = region;
        this.verifyId = verifyId;
        this.verifyCode = verifyCode;
    }

    public String getMobile() {
        return this.mobile;
    }

    public SmsModel setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getTemplateId() {
        return this.templateId;
    }

    public SmsModel setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public String getRegion() {
        return this.region;
    }

    public SmsModel setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getVerifyId() {
        return this.verifyId;
    }

    public SmsModel setVerifyId(String verifyId) {
        this.verifyId = verifyId;
        return this;
    }

    public String getVerifyCode() {
        return this.verifyCode;
    }

    public SmsModel setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
        return this;
    }
}
