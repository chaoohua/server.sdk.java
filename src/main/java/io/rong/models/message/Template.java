package io.rong.models.message;

import java.util.Map;

public class Template {

    /* {
          type: 'SYSTEM',
          senderId: '__SYSTEM__',
          objectName: 'RC:TxtMsg',
          template: {
              content: '{name}, 语文成绩 {score} 分'
          },
          content: {
              sea9901: {
                  data: {
                      '{name}': '小明',
                      '{score}': '90'
                  },
                  push: '{name} 考试成绩'
              },
              sea9902: {
                  data: {
                      '{name}': '小红',
                      '{score}': '95'
                  },
                  push: '{name} 考试成绩'
              }
          }
      };*/
   private String senderId;
   private String objectName;
   /**
    * 模板
    * */
   private String template;
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

    public String getTemplate() {
        return this.template;
    }

    public void setTemplate(String template) {
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
        private Map<String,String> date;
        private String push;

        public Map<String, String> getDate() {
            return this.date;
        }

        public void setDate(Map<String, String> date) {
            this.date = date;
        }

        public String getPush() {
            return this.push;
        }

        public void setPush(String push) {
            this.push = push;
        }
    }
}
