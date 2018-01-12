package io.rong.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.rong.models.group.GroupResponse;
import io.rong.models.group.GroupModel;
import io.rong.models.user.UserInfo;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class CommonUtil {
    public static final String METHOD = "send";
    public static boolean validateParams(Object params, int length){
        try {
            if(null == params){
                return false;
            }
            if(params instanceof String[]){
                String[] param = (String[]) params;
                int len = param.length;
                if(len <= length){
                    return true;
                }
            }else if(params instanceof String){
                String param = (String) params;
                int len = param.getBytes("UTF-8").length;
                if(len <= length){
                    return true;
                }
            }else if(params instanceof Integer){
                Integer param = (Integer) params;
                if(param <= length){
                    return true;
                }
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("长度校验错误"+e);
        }
        return false;
    }
    /**
     * 参数校验
     *
     * @param fileds 需要校验的字段
     * @param model  传入实体
     * @param path   路径
     * @param checkRulesName 校验规则名 （根据此规则名获取json里面的具体校验规则）
     * @param method 需要校验方法
     *
     * @return String
     **/
    public static String checkFiled(String[] fileds,Object model,String path,String checkRulesName,String method){
        try {
            String code = "200";
            Integer max = 64;
            String apiPath = path;
            String type = "";
            if(path.contains("/")){
                path = path.substring(0,path.indexOf("/"));
            }
            JSONObject verify =  JsonUtil.getJsonObject(path);//获取校验文件
            Set<String> keys = verify.getJSONObject(checkRulesName).keySet();//获取校验key
            JSONObject entity = verify.getJSONObject(checkRulesName);//获取具体校验规则
            for(String name : fileds){
                for (String key : keys) {
                    if(name.equals(key)){
                        String nameTemp = name;
                        name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
                        //String type = field.getGenericType().toString();    //获取属性的类型
                        Method m = model.getClass().getMethod("get"+name);
                        JSONObject object =  entity.getJSONObject(nameTemp);//获取字段的具体校验规则
                        if(object.containsKey("require")){
                            Boolean must = (Boolean)object.getJSONObject("require").get("must");
                            if(m.invoke(model)  instanceof String){
                                String value = (String) m.invoke(model);
                                if(StringUtils.isBlank(value)){
                                    code = (String)object.getJSONObject("require").get("invalid");
                                }
                            }else{
                                Object value = (Object) m.invoke(model);
                                if(null == value){
                                    code = (String)object.getJSONObject("require").get("invalid");
                                }
                            }
                        }
                        if(object.containsKey("length")){
                             max = (Integer)object.getJSONObject("length").get("max");
                            //String value = (String) m.invoke(model);
                            if(m.invoke(model)  instanceof String){
                                String value = (String) m.invoke(model);
                                if("200".equals(code) && StringUtils.isBlank(value)){
                                    code = (String)object.getJSONObject("length").get("invalid");
                                }
                                if("200".equals(code) && value.getBytes("UTF-8").length > max){
                                    code = (String)object.getJSONObject("length").get("invalid");
                                }
                            }else if(m.invoke(model)  instanceof String[]){
                                String[] value = (String[]) m.invoke(model);
                                if("200".equals(code) && value.length > max){
                                    code = (String)object.getJSONObject("length").get("invalid");
                                }
                            }else{
                                Object value = (Object) m.invoke(model);
                                if("200".equals(code) && null == value){
                                    code = (String)object.getJSONObject("length").get("invalid");
                                }

                            }

                        }
                        if(object.containsKey("size")){
                            max = (Integer)object.getJSONObject("size").get("max");
                            type = (String)object.getJSONObject("typeof").get("type");
                            if(type.contains("array")){
                                String[] value = (String[]) m.invoke(model);
                                if("200".equals(code) && null == value){
                                    code = (String)object.getJSONObject("size").get("invalid");
                                }
                                if("200".equals(code) && value.length > max){
                                    code = (String)object.getJSONObject("size").get("invalid");
                                }

                            }else if(type.contains("int")){
                                Integer value = 0;
                                try{
                                    value = (Integer) m.invoke(model);
                                }catch (Exception e){
                                    code = (String)object.getJSONObject("typeof").get("invalid");
                                }

                                if("200".equals(code) && null == value){
                                    code = (String)object.getJSONObject("size").get("invalid");
                                }
                                if("200".equals(code) && value > max){
                                    code = (String)object.getJSONObject("size").get("invalid");
                                }

                            }
                        }
                        String message = (String)CommonUtil.getErrorMessage(apiPath,method,code,name,String.valueOf(max),"1",type);
                        message = StringUtils.replace(message,"msg","errorMessage");
                        return message;

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 参数校验
     *
     * @param checkFiled 需要校验的字段
     * @param value  传入参数值
     * @param path   路径 （获取校验文件路径）
     * @param checkObject 校验对象 （根据此规则名获取json里面的具体校验规则）
     * @param method 需要校验方法
     *
     * @return String
     **/
    public static String checkParam(String checkFiled,Object value,String path,String checkObject,String method){
        try {
            String code = "200";
            boolean flag = false;
            Integer max = 64;
            String type = "";
            String  apiPath = path;
            if(path.contains("/")){
                path = path.substring(0,path.indexOf("/"));
            }
            JSONObject verify =  JsonUtil.getJsonObject(path);
            Set<String> keys = verify.getJSONObject(checkObject).keySet();
            JSONObject entity = verify.getJSONObject(checkObject);
                for (String key : keys) {
                    if(checkFiled.equals(key)){
                        JSONObject object =  entity.getJSONObject(checkFiled);
                        if(object.containsKey("require")){
                            Boolean must = (Boolean)object.getJSONObject("require").get("must");
                            if(value  instanceof String){
                                if(StringUtils.isBlank(String.valueOf(value))){
                                    code = (String)object.getJSONObject("require").get("invalid");
                                }

                            }else{
                                if(null == value){
                                    code = (String)object.getJSONObject("require").get("invalid");
                                }
                            }
                        }
                        if(object.containsKey("length")){
                            max = (Integer)object.getJSONObject("length").get("max");
                            if(value  instanceof String){
                                if("200".equals(code) && StringUtils.isBlank(String.valueOf(value))){
                                    code = (String)object.getJSONObject("length").get("invalid");
                                }
                                if("200".equals(code) && String.valueOf(value).getBytes("UTF-8").length > max){
                                    code = (String)object.getJSONObject("length").get("invalid");
                                }
                            }else if(value  instanceof String[]){
                                String[] valueTemp = {};
                                try{
                                    valueTemp = (String[]) value;
                                }catch (Exception e){
                                    code = (String)object.getJSONObject("typeof").get("invalid");
                                }
                                if("200".equals(code) && valueTemp.length > max){
                                    code = (String)object.getJSONObject("length").get("invalid");
                                }
                            }else{
                                if("200".equals(code) && null == value){
                                    code = (String)object.getJSONObject("length").get("invalid");
                                }

                            }

                        }
                        if(object.containsKey("size")){
                            max = (Integer)object.getJSONObject("size").get("max");
                            type = (String)object.getJSONObject("typeof").get("type");
                            if(type.contains("array")){
                                String[] valueTemp = null;
                                if("200".equals(code) && null == value){
                                    code = (String)object.getJSONObject("size").get("invalid");
                                }
                                try{
                                    valueTemp = (String[]) value;
                                }catch (Exception e){
                                    code = (String)object.getJSONObject("typeof").get("invalid");
                                }

                                if("200".equals(code) && valueTemp.length > max){
                                    code = (String)object.getJSONObject("size").get("invalid");
                                }

                            }else if(type.contains("int")){
                                Integer valueTemp = 64;
                                try{
                                    valueTemp = (Integer) value;
                                }catch (Exception e){
                                    code = (String)object.getJSONObject("typeof").get("invalid");
                                }
                                if("200".equals(code) && null == value){
                                    code = (String)object.getJSONObject("size").get("invalid");
                                }
                                if("200".equals(code) && valueTemp > max){
                                    code = (String)object.getJSONObject("size").get("invalid");
                                }

                            }


                        }
                        String message = (String)CommonUtil.getErrorMessage(apiPath,method,code,checkFiled,String.valueOf(max),"1",type);
                        message = StringUtils.replace(message,"msg","errorMessage");
                        return message;

                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getErrorMessage(String path,String method,String errorCode,String name,String max ,String min,String type){
        JSONObject api = null;
        try {
            api = JsonUtil.getJsonObject2(path);
            Set<Map.Entry<String,Object>> keys = api.getJSONObject(method).getJSONObject("response").getJSONObject("fail").entrySet();
            String[] serchList = {"{{name}}","{{min}}","{{name}}","{{max}}"};
            String[] replaceList = {name,max,name,min};
            for (Map.Entry<String,Object> entry : keys) {
                if(errorCode.equals(entry.getKey())){
                    String text = entry.getValue().toString();
                    StringUtils.replaceEach(text,serchList,replaceList);
                    return StringUtils.replaceEach(text,serchList,replaceList);
                }
            }
        } catch (IOException e) {

        }
        return null;
    }
    public static String getResponseByCode(String path,String method,String errorMessage){
        JSONObject api = null;
        try {
            JSONObject object =  JSON.parseObject(errorMessage);
            String code = String.valueOf(object.get("code"));
            api = JsonUtil.getJsonObject2(path);
            Set<Map.Entry<String,Object>> keys = api.getJSONObject(method).getJSONObject("response").getJSONObject("fail").entrySet();
            String[] serchList = {"{{name}}","{{min}}","{{name}}","{{max}}"};


            for (Map.Entry<String,Object> entry : keys) {
                if(code.equals(entry.getKey())){
                    String text = entry.getValue().toString();
                    text = StringUtils.replace(text,"msg","errorMessage");
                    return text;
                }
            }
        } catch (Exception e) {

        }
        return errorMessage;
    }

}
