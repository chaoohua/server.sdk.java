package io.rong.models.message;

import java.util.Map;

/**
 * @author hc
 */
public class Template {
   private String senderId;
   private String objectName;
   /**
    * 模板
    * */
   private Object template;
   /**
    * key 用户Id ,value 模板赋值
    *
    * */
   private Map<String, Template.Data> content;

   private String[] pushData;

   private Integer verifyBlacklist;

   private Integer contentAvailable;

    public String getSenderId() {
        return this.senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Object getTemplate() {
        return this.template;
    }

    public void setTemplate(Object template) {
        this.template = template;
    }

    public Map<String, Template.Data> getContent() {
        return content;
    }

    public void setContent(Map<String, Template.Data> content) {
        this.content = content;
    }

    public String[] getPushData() {
        return this.pushData;
    }

    public void setPushData(String[] pushData) {
        this.pushData = pushData;
    }

    public Integer getVerifyBlacklist() {
        return this.verifyBlacklist;
    }

    public void setVerifyBlacklist(Integer verifyBlacklist) {
        this.verifyBlacklist = verifyBlacklist;
    }

    public Integer getContentAvailable() {
        return this.contentAvailable;
    }

    public void setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
    }

    public class Data{
        private Map<String,String> data;
        private String push;

        public Map<String, String> getData() {
            return this.data;
        }

        public void setData(Map<String, String> data) {
            this.data = data;
        }

        public String getPush() {
            return this.push;
        }

        public void setPush(String push) {
            this.push = push;
        }
    }
}
