package io.rong.models.chatroom;

import io.rong.models.User;
import io.rong.util.GsonUtil;
import io.rong.util.ParamNotNull;

/**
 * 聊天室信息。
 */
public class ChatRoom {
	/**
	 * 聊天室 id。
	 */
	@ParamNotNull
	String id;
	/**
	 * 聊天室名。
	 */
	@ParamNotNull
	String name;
	/**
	 * 聊天室创建时间。
	 */
	String time;
	/**
	 * 聊天室成员。
	 */
	Member[] members;

	public ChatRoom(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public ChatRoom(String id, String name, String time) {
		this.id = id;
		this.name = name;
		this.time = time;
	}

	public ChatRoom(String id, String name, Member[] members) {
		this.id = id;
		this.name = name;
		this.time = time;
		this.members = members;
	}
	
	/**
	 * 设置chrmId
	 *
	 */	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获取chrmId
	 *
	 * @return String
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 设置name
	 *
	 */	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取name
	 *
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置time
	 *
	 */	
	public void setTime(String time) {
		this.time = time;
	}
	
	/**
	 * 获取time
	 *
	 * @return String
	 */
	public String getTime() {
		return time;
	}

	public Member[] getMembers() {
		return this.members;
	}

	public void setMembers(Member[] members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatRoom.class);
	}
}
