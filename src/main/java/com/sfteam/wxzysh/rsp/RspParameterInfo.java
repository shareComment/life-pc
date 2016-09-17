package com.sfteam.wxzysh.rsp;

import java.util.List;

import com.sfteam.wxzysh.dto.Parameter;

/**
 * @ClassName: RspParameterInfo.java
 * @Description: 系统参数
 * @author: knight
 * @date: 2016年7月15日 下午7:44:35
 * @company: sfteam
 */
public class RspParameterInfo {

	private String result;

	private String errorInfo;

	private Parameter rspInfo;

	private List<Parameter> infoList;

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the errorInfo
	 */
	public String getErrorInfo() {
		return errorInfo;
	}

	/**
	 * @param errorInfo the errorInfo to set
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	/**
	 * @return the rspInfo
	 */
	public Parameter getRspInfo() {
		return rspInfo;
	}

	/**
	 * @param rspInfo the rspInfo to set
	 */
	public void setRspInfo(Parameter rspInfo) {
		this.rspInfo = rspInfo;
	}

	/**
	 * @return the infoList
	 */
	public List<Parameter> getInfoList() {
		return infoList;
	}

	/**
	 * @param infoList the infoList to set
	 */
	public void setInfoList(List<Parameter> infoList) {
		this.infoList = infoList;
	}

	
}
