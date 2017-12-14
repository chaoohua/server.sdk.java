package io.rong.util;

import io.rong.models.group.GroupConstrants;
import io.rong.models.group.GroupResponse;
import io.rong.models.group.GroupModel;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

public class CommonUtil {

    public static boolean validateParams(Object params,int length){
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
    public static <E> E checkParamter(Object object,String className){
        String message = "";
        int code = 200;
        //GroupResponseResult  groupResponseResult = new GroupResponseResult();
        if(object instanceof GroupModel){
            if(null == object){
               message = "params is null";
            }else{
                GroupModel group = (GroupModel) object;
                if(null == group.merberIds){
                    code = GroupConstrants.GROUP_PARAM_ERROR;
                    message = "Paramer 'userId' is required";
                }else if(null == group.id){
                    code = GroupConstrants.GROUP_PARAM_ERROR;
                    message = "Paramer 'groupId' is required";
                }else if(null == group.name){
                    code = GroupConstrants.GROUP_PARAM_ERROR;
                    message = "Paramer 'groupName' is required";
                }
            }
        }
        StringUtils.replace("","","");
//        try {
//            Class attrObjClass = Class.forName(className);
//            if(attrObjClass.isInstance(GroupResponseResult.class)){
//
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        if(!"".equals(message)){
            return (E)new GroupResponse(code,message);
            //throw new IllegalArgumentException(message);
        }
        return null;
    }
}
