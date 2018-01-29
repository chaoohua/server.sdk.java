package io.rong.methods.chatroom.gag;

import io.rong.RongCloud;
import io.rong.methods.chatroom.gag.global.Global;
import io.rong.methods.chatroom.gag.member.Members;

public class Gag {
    private static final String UTF8 = "UTF-8";
    private String appKey;
    private String appSecret;
    public Global global;
    public Members members;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
        global.setRongCloud(rongCloud);
        members.setRongCloud(rongCloud);

    }

    public Gag(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.global = new Global(appKey,appSecret);
        this.members = new Members(appKey,appSecret);

    }

}
