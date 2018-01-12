package io.rong.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import io.rong.example.Example;
import io.rong.exception.ParamException;
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
            reader =new BufferedReader( new InputStreamReader(new FileInputStream(JSONFILE+method+"/verify.json")));
            while ((line = reader.readLine()) != null){
                arrs.append(line);
            }
            JSONObject object =  JSON.parseObject(arrs.toString());
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

    public static <T> T getJsonObject2(String path) throws IOException {
        BufferedReader reader = null ;
        try {
            String line="";
            StringBuffer arrs=new StringBuffer();
            reader =new BufferedReader( new InputStreamReader(new FileInputStream(JSONFILE+path+"/api.json")));
            while ((line = reader.readLine()) != null){
                arrs.append(line);
            }
            JSONObject object =  JSON.parseObject(arrs.toString());
            return (T)object;
        } catch (Exception e) {

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
           System.out.println((String)getJsonObject("group"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
