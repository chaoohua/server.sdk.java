package io.rong.example.sensitive;

import io.rong.RongCloud;
import io.rong.models.CodeSuccessResult;
import io.rong.models.ListWordfilterResult;

import java.util.ArrayList;
import java.util.List;

public class SensitiveExample {

    public static void main(String[] args) throws Exception {

       /* String appKey = "appkey";//替换成您的appkey

        String appSecret = "secret";//替换成匹配上面key的secret

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);*/
        String appKey = "e0x9wycfx7flq";
        String appSecret = "STCevzDS6Xy18n";
        String api = "http://192.168.155.13:9200";
/*
        String appKey = "8luwapkvucoil";
        String appSecret = "y0icysjl4h3LWz";
        String api = "http://api.cn.ronghub.com";*/

        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret, api);



        System.out.println("************************Sensitiveword********************");
        // 添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。）
        //CodeSuccessResult sensitivewordAddResult = rongCloud.sensitiveword.add("money", "****");

        //System.out.println("add:  " + sensitivewordAddResult.toString());


        /*System.out.println("************************Wordfilter********************");
        //敏感词服务已升级，Wordfilter为原敏感词服务。 仍然可以使用，但是不再继续更新。
        // 添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。）
        CodeSuccessResult wordfilterAddResult = rongCloud.wordfilter.add("money");
        System.out.println("add:  " + wordfilterAddResult.toString());

        // 查询敏感词列表方法
        ListWordfilterResult wordfilterGetListResult = rongCloud.wordfilter.getList();
        System.out.println("getList:  " + wordfilterGetListResult.toString());

        // 移除敏感词方法（从敏感词列表中，移除某一敏感词。）
        //CodeSuccessResult wordfilterDeleteResult = rongCloud.wordfilter.delete("money");
        System.out.println("delete:  " + wordfilterDeleteResult.toString());*/


        //敏感词服务已升级，Sensitiveword新敏感词服务。

        System.out.println("************************Sensitiveword********************");
        // 添加敏感词方法（设置敏感词后，App 中用户不会收到含有敏感词的消息内容，默认最多设置 50 个敏感词。）
        //CodeSuccessResult sensitivewordAddResult = rongCloud.sensitiveword.add("money", "****");
       // System.out.println("add:  " + sensitivewordAddResult.toString());

        // 查询敏感词列表方法
        ListWordfilterResult sensitivewordGetListResult = rongCloud.sensitiveword.getList(1);
        System.out.println("getList:  " + sensitivewordGetListResult.toString());

        // 移除敏感词方法（从敏感词列表中，移除某一敏感词。）
        //CodeSuccessResult sensitivewordDeleteResult = rongCloud.sensitiveword.delete("money");
        //System.out.println("delete:  " + sensitivewordDeleteResult.toString());


        List<String> list =  new ArrayList<String>();
        String[] words = {"money","money1"};
        // 批量移除敏感词方法（从敏感词列表中，批量移除某些敏感词，一次最多移除敏感词不超过 50 个，移除后 2 小时内生效.）
       // CodeSuccessResult wordfilterBatchDeleteResult = rongCloud.sensitiveword.batchDelete(words);
        //System.out.println("delete:  " + wordfilterBatchDeleteResult.toString());



    }
}
