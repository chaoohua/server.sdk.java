package io.rong.util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.Consts;

import java.io.IOException;
import java.util.Date;

public class HttpClientUtil {
    public static void createHttpConnection() throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://vcloud.163.com/app/channel/create";
        HttpPost httpPost = new HttpPost(url);

        String appKey = "94kid09c9ig9k1loimjg012345123456";
        String appSecret = "123456789012";
        String nonce =  "1";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        StringBuilder toSign = new StringBuilder(appSecret).append(nonce).append(curTime);
        String checkSum = CodeUtil.hexSHA1(toSign.toString());//参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");

        // 设置请求的参数
        StringEntity params = new StringEntity("{\"name\":\"netease_vcloud\", \"type\":0}",Consts.UTF_8);
        httpPost.setEntity(params);

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);

        // 打印执行结果
        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
    }
}
