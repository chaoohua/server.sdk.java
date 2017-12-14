package io.rong.models.user;

public class UserConstrants {
    public static final int USER_TESTUSER_NUM_OUT= 10001;//测试人数超限, 请前往开发者后台 -> 应用 -> App Key -> 测试用户数,增加测试人数, https://developer.rongcloud.cn
    public static final int USER_NAME_LEN_OUT = 10002;//用户名长度超限, 最大长度 128 字节
    public static final int USER_ID_LEN_OUT = 10003;//userId 长度超限 最大长度 64字节
    public static final int USER_PORTRAIT_OUT = 10004;//头像长度超限， 最大长度 1024 字节
    public static final int USER_URL_ERROR = 10005;//头像 url 地址不是合法
    public static final int USER_GAG_TIMEOUT= 10006;//封禁时间超限，最大禁言时长为 43200 分钟。
    public static final int USER_PARAM_ERROR = 10007;//参数不正确，userId 为空
    public static final int USER_SIGN_NULL = 10008;//签名错误，请检查签名生成是否正确，签名生成规则："http://www.rongcloud.cn/docs/server.html#signature"
    public static final int USER_ID_NULL = 10009;//userId 为空
    public static final int USER_PARAM_NULL = 10010;//参数为 null，请检查参数。
    public static final int USER_FREQUENCY_OUT = 10010;//参数为 null，请检查参数
    public static final int USER_ADD_BLACKLIST_OUT = 10011;//获取某用户的黑名单频率超限
    public static final int USER_REMOVE_BLACKLIST_OUT = 10012;//黑名单中移除用户方频率超限
//10013=添加用户到黑名单频率超限
/*
**
10001=测试人数超限， 请前往开发者后台 -> 应用 -> App Key -> 测试用户数，增加测试人数，"https://developer.rongcloud.cn"
10002=用户名长度超限， 最大长度 128 字节
10003=userId 长度超限
10004=头像长度超限， 最大长度 1024 字节
10005=头像 url 地址不是合法
10006=封禁时间超限，最大禁言时长为 43200 分钟。
10007=参数不正确，userId 为空
10008=签名错误，请检查签名生成是否正确，签名生成规则："http://www.rongcloud.cn/docs/server.html#signature"
10009=userId 为空
10010=参数为 null，请检查参数。
**
*/

}
