package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.chatroom.Member;
import io.rong.util.GsonUtil;

import java.util.List;

/**
 *  chatroomUserQuery 返回结果
 */
public class ChatroomUserQueryResult extends Result{
	// 聊天室中用户数。
	Integer total;
	// 聊天室成员列表。
	List<Member> users;

	public ChatroomUserQueryResult(Integer code, Integer total, List<Member> users, String errorMessage) {
		super(code,errorMessage);
		this.code = code;
		this.total = total;
		this.users = users;
		this.errorMessage = errorMessage;
	}
	/**
	 * 设置total
	 *
	 */	
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	/**
	 * 获取total
	 *
	 * @return Integer
	 */
	public Integer getTotal() {
		return total;
	}
	
	/**
	 * 设置users
	 *
	 */	
	public void setUsers(List<Member> users) {
		this.users = users;
	}
	
	/**
	 * 获取users
	 *
	 * @return List<Member>
	 */
	public List<Member> getUsers() {
		return users;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatroomUserQueryResult.class);
	}
}
