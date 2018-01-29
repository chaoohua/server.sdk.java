package io.rong.models.response;

import io.rong.models.Result;
import io.rong.models.sensitiveword.Sensitivewords;
import io.rong.util.GsonUtil;

import java.util.List;

/**
 * listWordfilter返回结果
 */
public class ListWordfilterResult extends Result {
	// 敏感词内容。
	List<Sensitivewords> words;

	public ListWordfilterResult(Integer code, List<Sensitivewords> words, String errorMessage) {
		super(code, errorMessage);
		this.code = code;
		this.words = words;
		this.errorMessage = errorMessage;
	}
	
	/**
	 * 设置code
	 *
	 */	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	/**
	 * 获取code
	 *
	 * @return Integer
	 */
	public Integer getCode() {
		return code;
	}
	
	/**
	 * 设置words
	 *
	 */	
	public void setWords(List<Sensitivewords> words) {
		this.words = words;
	}
	
	/**
	 * 获取words
	 *
	 * @return List<Sensitivewords>
	 */
	public List<Sensitivewords> getWords() {
		return words;
	}
	
	/**
	 * 设置errorMessage
	 *
	 */	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * 获取errorMessage
	 *
	 * @return String
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, ListWordfilterResult.class);
	}
}
