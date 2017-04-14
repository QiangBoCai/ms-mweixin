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
 * 铭飞科技
 * Copyright: Copyright (c) 2014 - 2015
 * @author 王天培   QQ:78750478
 * Comments:微信支付,统一下单请求参数
 * Create Date:2015-1-19
 * Modification history:
 */
public class WeixinPayOrderBean extends BaseBean{
	 /**
	  * 微信分配的公众账号 ID(必须)
	  */
	 private String appId;
	 
	 /**
	  * 微信支付分配的商户号(必须)
	  */
	 private String mchId;
	 
	 /**
	  * 微信支付分配的终端设备号(非必须)
	  */
	 private String deviceInfo;	 
	 
	 
	 /**
	  *  随机字符串，不长于 32 位(必须)</br>
	  *  当外部未传入随机数时，自动生成长度为32位的随机数
	  */
	 private String nonce;	 
	 
	 /**
	  * 签名(必须)
	  */
	 private String sign;	
	 
	 /**
	  * 商品描述(必须)
	  */
	 private String body;	 
	 
	 /**
	  * 商品详情(非必须)
	  */
	 private String detail;
	 
	 /**
	  * 附加数据，原样返回(非必须)<br>
	  * 主要携带自定义数据会原样返还给商户
	  */
	 private String attach;	 
	 
	 /**
	  * 商户订单编号(必须)
	  */
	 private String outTradeNo;
	 
	 /**
	  * 货币类型(非必须)
	  */
	 private String feeType;
 
	 /**
	  * 订单总金额，单位为分，不能带小数点(必须)
	  */
	 private int totalFee;	 
	 
	 /**
	  * 终端IP(必须)</br>
	  * 订单生成的机器 IP</br>
	  */
	 private String spbillCreateIp;	 
	 
	 /**
	  * 交易开始时间(非必须)</br>
	  * 时间格式：yyyyMMddHHmmss
	  */
	 private String timeStart;
	 
	 /**
	  * 交易结束时间(非必须)</br>
	  * 时间格式：yyyyMMddHHmmss
	  */
	 private String timeExpire;
	 
	 /**
	  * 商品标记(非必须)</br>
	  * 调用代金券</br>
	  */
	 private String goodsTag;
	 
	 /**
	  * 接收微信支付成功通知(必须)
	  */
	 private String notifyUrl;
	 
	 /**
	  * 交易类型(必须)</br>
	  * JSAPI、NATIVE、APP</br>
	  */
	 private String tradeType;
	 
	 /**
	  * 商品Id(非必须)</br>
	  * trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。</br>
	  */
	 private String productId;
	 
	 /**
	  * 指定支付方式(非必须)</br>
	  * no_credit--指定不能使用信用卡支付
	  */
	 private String limitPay;
	 
	 /**
	  * openid微信用户的唯一标识(非必须)
	  */
	 private String openId;
	 
	 /**
	  * 微信订单号
	  */
	 private String transactionId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	

}
