/**
 * 
 */
package com.sfteam.wxzysh.rsp;

import java.util.List;

import com.sfteam.wxzysh.dto.Brand;

/**
 * @ClassName: RspBrandInfo.java
 * @Description: 响应信息
 * @author: knight
 * @date: 2016年7月15日 下午4:10:51
 * @company: sfteam
 */
public class RspBrandInfo {

	public String result;

	public String errorInfo;

	public List<Brand> infoList;

	public String count;

	public Brand rspInfo;

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
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
	 * @param errorInfo
	 *            the errorInfo to set
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	/**
	 * @return the infoList
	 */
	public List<Brand> getInfoList() {
		return infoList;
	}

	/**
	 * @param infoList the infoList to set
	 */
	public void setInfoList(List<Brand> infoList) {
		this.infoList = infoList;
	}

	/**
	 * @return the count
	 */
	public String getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * @return the rspInfo
	 */
	public Brand getRspInfo() {
		return rspInfo;
	}

	/**
	 * @param rspInfo the rspInfo to set
	 */
	public void setRspInfo(Brand rspInfo) {
		this.rspInfo = rspInfo;
	}

}
