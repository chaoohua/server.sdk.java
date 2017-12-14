package io.rong.messages;

import io.rong.Example;
import java.io.*;
import java.util.HashMap;

public class ErrorCodeMessage {
    public static HashMap<String, String> errorCodeMaps = new HashMap<>();
    private static final String JSONFILE = Example.class.getClassLoader().getResource("jsonsource").getPath()+"/";
    static {
        BufferedReader reader = null ;
        try {
            String line="";
            String[] arrs=null;
            reader =new BufferedReader( new InputStreamReader(new FileInputStream(JSONFILE+"ErrorCode.conf")));
           while ((line = reader.readLine()) != null){
               if(line.indexOf("=")!= -1){
                   arrs=line.split("=");
                   errorCodeMaps.put(arrs[0], arrs[1]);
               }
           }
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

    }


}
