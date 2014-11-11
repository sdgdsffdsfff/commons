/**
 * 
 */
package com.vanstone.webframework.dwz;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.vanstone.business.lang.BaseEnum;
import com.vanstone.business.serialize.GsonCreator;

/**
 * @author shipeng
 *
 */
public class DWZObject {
	
	/** 操作标志位*/
	private String statusCode;
	/** 显示信息*/
	private String message;
	/** 关联显示ID*/
	private String rel;
	/** 回调类型*/
	private String callbackType;
	/** 转向的URL*/
	private String forwardUrl;
	/** 操作中间过程的确认信息*/
	private String confirmMsg;
	/** 是否为对话框*/
	private boolean dialog = false;
	/** DWZ参数信息*/
	private Map<String, Object> params = new LinkedHashMap<String, Object>();
	
	protected DWZObject(String  statusCode) {
		this.statusCode = statusCode;
	}
	
	/**
	 * 创建对象
	 * @param dwzStatusCode
	 * @return
	 */
	public static DWZObject create(StatusCode statusCode) {
		return new DWZObject(statusCode.getCode());
	}
	
	/**
	 * 创建对象
	 * @param dwzStatusCode
	 * @param message
	 * @return
	 */
	public static DWZObject create(StatusCode statusCode, String message) {
		DWZObject object = new DWZObject(statusCode.getCode());
		object.setMessage(message);
		return object;
	}
	
	public static DWZObject createSuccessObject(String message) {
		return create(StatusCode.Success, message);
	}
	
	public static DWZObject createSuccessObject() {
		return create(StatusCode.Success);
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String getConfirmMsg() {
		return confirmMsg;
	}

	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}
	
	public String getStatusCode() {
		return statusCode;
	}

	public Map<String, Object> getParams() {
		return params;
	}
	
	public void addParam(String name, Object value) {
		this.params.put(name, value);
	}
	
	public void addParams(Map<String, Object> params) {
		this.params.putAll(params);
	}
	
	public void clearParams() {
		this.params.clear();
	}
	
	public static DWZObject parse(String json) {
		Gson gson = GsonCreator.create();
		return gson.fromJson(json, DWZObject.class);
	}
	
	public boolean isDialog() {
		return dialog;
	}

	public void setDialog(boolean dialog) {
		this.dialog = dialog;
	}
	
	
	
	
	
	public static enum StatusCode implements BaseEnum<String> {
		Success("操作成功", "200"), Error("操作失败", "300"), Timeout("操作超时", "301");
		;

		private String desc;
		private String code;

		private StatusCode(String desc, String code) {
			this.desc = desc;
			this.code = code;
		}
		
		@Override
		public String getCode() {
			return this.code;
		}

		@Override
		public String getDesc() {
			return this.desc;
		}
	}
	
	
}
