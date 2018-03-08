package io.rong.methods.message;

import io.rong.RongCloud;
import io.rong.methods.message._private.Private;
import io.rong.methods.message.chatroom.Chatroom;
import io.rong.methods.message.discussion.Discussion;
import io.rong.methods.message.group.Group;
import io.rong.methods.message.history.History;
import io.rong.methods.message.system.System;
import io.rong.models.*;
import io.rong.models.message.RecallMessage;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;


public class Message {

	private static final String UTF8 = "UTF-8";
	private static final String PATH = "message";
	private static String method = "";
	private String appKey;
	private String appSecret;
	public  Private aPrivate;
	public  Chatroom chatroom;
	public  Discussion discussion;
	public  Group group;
	public  History history;
	public  System system;
	private RongCloud rongCloud;

	public RongCloud getRongCloud() {
		return rongCloud;
	}

	public void setRongCloud(RongCloud rongCloud) {
		this.rongCloud = rongCloud;
		aPrivate.setRongCloud(this.getRongCloud());
		chatroom.setRongCloud(this.getRongCloud());
		discussion.setRongCloud(this.getRongCloud());
		group.setRongCloud(this.getRongCloud());
		history.setRongCloud(this.getRongCloud());
		system.setRongCloud(this.getRongCloud());

	}
	public Message(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.aPrivate = new Private(appKey,appSecret);
		this.chatroom = new Chatroom(appKey,appSecret);
		this.discussion = new Discussion(appKey,appSecret);
		this.group = new Group(appKey,appSecret);
		this.history = new History(appKey,appSecret);
		this.system = new System(appKey,appSecret);

	}
	 
}