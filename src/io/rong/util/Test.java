package io.rong.util;

import org.apache.commons.lang3.StringUtils;

public class Test {
    public static void main(String... args){
        String ss = "{{name}} 长度超限, {{name}} >= {{min}} 且 {{name}} <= dd 单位: 字节";
        String[] replace = {"{{name}}","{{min}}","{{name}}","{{max}}"};
        String[] replaceList = {"ss","1","ss","64"};
        System.out.println(StringUtils.replaceEach(ss,replace,replaceList));
    }
}
