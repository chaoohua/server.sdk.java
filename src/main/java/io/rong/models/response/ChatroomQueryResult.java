package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.chatroom.ChatRoom;
import io.rong.util.GsonUtil;

import java.util.List;

/**
 *  chatroomQuery 返回结果
 */
public class ChatroomQueryResult extends Result {
	List<ChatRoom> chatRooms;
	public ChatroomQueryResult(Integer code, String errorMessage, List<ChatRoom> chatRooms) {
		super(code, errorMessage);
		this.chatRooms = chatRooms;
	}
	/**
	 * 设置chatRooms
	 *
	 */
	public void setChatRooms(List<ChatRoom> chatRooms) {
		this.chatRooms = chatRooms;
	}
	
	/**
	 * 获取chatRooms
	 *
	 * @return chatRooms
	 */
	public List<ChatRoom> getChatRooms() {
		return chatRooms;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatroomQueryResult.class);
	}
}
