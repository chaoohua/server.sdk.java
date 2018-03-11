package io.rong.models.group;

import io.rong.util.GsonUtil;

/**
 * 群组信息。
 * @author RongCloud
 */
public class GroupInfo {
	// 群组Id。
	String id;
	// 群组名称。
	String name;
	
	public GroupInfo(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * 设置id
	 *
	 */	
	public GroupInfo setId(String id) {
		this.id = id;
		return this;
	}
	
	/**
	 * 获取id
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
	public GroupInfo setName(String name) {
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
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, GroupInfo.class);
	}
}
