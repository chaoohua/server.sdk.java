package io.rong.models.chatroom;

import io.rong.models.User;
import io.rong.util.GsonUtil;
import io.rong.util.ParamNotNull;

/**
 * 聊天室。
 */
public class ChatRoom {
	/**
	 * 聊天室 id。
	 */
	String id;
	/**
	 * 聊天室名。
	 */
	String name;
	/**
	 * 聊天室创建时间。
	 */
	String time;
	/**
	 * 聊天室成员。
	 */
	Member[] members;
	/**
	 * 聊天室成员数。
	 */
	Integer count;
	/**
	 * 加入聊天室的先后顺序,1正序，2倒叙。
	 */
	Integer order;
	/**
	 * 聊天室构造函数 聊天室信息
	 * */
	public ChatRoom() {
		super();
	}
	/**
	 * 聊天室构造函数 聊天室信息
	 * */
	public ChatRoom(String id, String name) {
		this.id = id;
		this.name = name;
	}
	/**
	 * 聊天室构造函数 聊天室信息包含时间
	 * */
	public ChatRoom(String id, String name, String time) {
		this.id = id;
		this.name = name;
		this.time = time;
	}
	/**
	 * 聊天室构造函数 聊天室信息和成员
	 * */
	public ChatRoom(String id, String name, Member[] members) {
		this.id = id;
		this.name = name;
		this.time = time;
		this.members = members;
	}
	/**
	 * 聊天室构造函数 用户数量的
	 * */
	public ChatRoom(String id, Integer count, Integer order) {
		this.id = id;
		this.count = count;
		this.order = order;
	}
	/**
	 * 聊天室构造函数 全量
	 * */
	public ChatRoom(String id, String name, String time, Member[] members, Integer count, Integer order) {
		this.id = id;
		this.name = name;
		this.time = time;
		this.members = members;
		this.count = count;
		this.order = order;
	}

	/**
	 * 设置chrmId
	 *
	 */	
	public ChatRoom setId(String id) {
		this.id = id;
		return this;
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
	public ChatRoom setName(String name) {
		this.name = name;
		return this;
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
	public ChatRoom setTime(String time) {
		this.time = time;
		return this;
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

	public ChatRoom setMembers(Member[] members) {
		this.members = members;
		return this;
	}

	public Integer getCount() {
		return this.count;
	}

	public ChatRoom setCount(Integer count) {
		this.count = count;
		return this;
	}

	public Integer getOrder() {
		return this.order;
	}

	public ChatRoom setOrder(Integer order) {
		this.order = order;
		return this;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatRoom.class);
	}
}
