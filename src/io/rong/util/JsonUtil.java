package io.rong.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.rong.example.Example;
import io.rong.models.CodeSuccessResult;
import io.rong.models.PushMessage;

import java.io.*;

public class JsonUtil {
    private static final String JSONFILE = Example.class.getClassLoader().getResource("jsonsource").getPath()+"/";

    public static <T> T getJsonObject(String method) throws IOException {
        BufferedReader reader = null ;
        try {
            String line="";
            StringBuffer arrs=new StringBuffer();
            reader =new BufferedReader( new InputStreamReader(new FileInputStream(JSONFILE+method+"/api.json")));
            while ((line = reader.readLine()) != null){
                arrs.append(line);
            }
           JSONObject object =  JSON.parseObject(arrs.toString());
            System.out.println(object.getString("fromuserid"));
            System.out.println(object.toJSONString());
            return (T)object;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != reader){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public static void main(String[] args){
        try {
            getJsonObject("group");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
