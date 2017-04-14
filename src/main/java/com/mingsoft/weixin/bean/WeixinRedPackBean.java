/**
The MIT License (MIT) * Copyright (c) 2017 铭飞科技

 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */package com.mingsoft.weixin.bean;
/**
 * 微信红包数据
 * @author 成卫雄(qq:330216230)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年9月9日 下午11:49:05<br/>
 * 历史修订：<br/>
 */
public class WeixinRedPackBean extends BaseBean{
	
	/**
	 * 证书物理路径
	 */
	private String certificateLocation;
	
	/**
	 * 证书密码
	 */
	private String certificateKey;
	
	/**
	 * 用户openId
	 */
	private String openId;
	
	/**
	 *订单号
	 */
	private String orderNo;
	
	/**
	 * 红包活动名称
	 */
	private String redPackTitle;
	
	/**
	 * 红包随机码
	 */
	private String randString;
	
	/**
	 * 红包活动备注
	 */
	private String redPackRemark;
	
	/**
	 * 红包发送者
	 */
	private String redPackSendName;
	
	/**
	 * 红包祝福语
	 */
	private String redPackBlessing;
	
	/**
	 * 红包发放数量
	 */
	private int redPackNum;
	
	/**
	 * 红包金额
	 */
	private int redPackMoney; 
	
	/**
	 * 商户号
	 */
	private String businessNo;
	
	/**
	 * 主机ip地址
	 */
	private String hostIp;
	
	/**
	 * 发送红包所需的key
	 */
	private String key;
	
	/**
	 * 微信的微信应用id
	 */
	private String appId;

	public String getCertificateLocation() {
		return certificateLocation;
	}

	public void setCertificateLocation(String certificateLocation) {
		this.certificateLocation = certificateLocation;
	}

	public String getCertificateKey() {
		return certificateKey;
	}

	public void setCertificateKey(String certificateKey) {
		this.certificateKey = certificateKey;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRedPackTitle() {
		return redPackTitle;
	}

	public void setRedPackTitle(String redPackTitle) {
		this.redPackTitle = redPackTitle;
	}

	public String getRandString() {
		return randString;
	}

	public void setRandString(String randString) {
		this.randString = randString;
	}

	public String getRedPackRemark() {
		return redPackRemark;
	}

	public void setRedPackRemark(String redPackRemark) {
		this.redPackRemark = redPackRemark;
	}

	public String getRedPackSendName() {
		return redPackSendName;
	}

	public void setRedPackSendName(String redPackSendName) {
		this.redPackSendName = redPackSendName;
	}

	public String getRedPackBlessing() {
		return redPackBlessing;
	}

	public void setRedPackBlessing(String redPackBlessing) {
		this.redPackBlessing = redPackBlessing;
	}

	public int getRedPackNum() {
		return redPackNum;
	}

	public void setRedPackNum(int redPackNum) {
		this.redPackNum = redPackNum;
	}

	public int getRedPackMoney() {
		return redPackMoney;
	}

	public void setRedPackPrice(int redPackMoney) {
		this.redPackMoney = redPackMoney;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	
}
