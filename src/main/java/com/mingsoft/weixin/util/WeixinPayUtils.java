package com.mingsoft.weixin.util;

import java.util.Map;

import com.mingsoft.weixin.http.HttpClientConnectionManager;

/**
 * 铭飞科技
 * Copyright: Copyright (c) 2014 - 2015
 * @author 王天培 QQ:78750478
 * 
 * Comments:微支付处理方法,微信支付流程概述：</br>
 * 公众号支付流程：</br>
 * 1.生成微信预支付订单(调用微信下单接口);接口地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1</br>
 * 2.获取返回的预支付订单信息(prepay_id),生成JSAPI调用支付的必须参数</br>
 * 3.jsapi起调微信支付(H5起调支付API),用户支付;接口地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_7&index=6</br>
 * 4.支付结果接受;接口地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7</br>
 * 
 * Create Date:2015-1-20
 * Modification history:
 */
public class WeixinPayUtils extends BaseUtils {

	/**
	 * 构造方法
	 * @param appid
	 * @param secret
	 */
	public WeixinPayUtils(String appid, String secret) {
		super(appid, secret);
	}

	/**
	 * 发起统一下单请求
	 * @param xml 请求支付的xml格式
	 * @returnn null支付失败
	 */
	public Map<String,Object> unifiedorder(String xml) {
		String re = HttpClientConnectionManager.request("https://api.mch.weixin.qq.com/pay/unifiedorder", xml, HttpClientConnectionManager.POST);
		if (re!=null) {
			return XmlUtils.getXmlPayUnifiedOrder(re);
		}
		return  null;
	}
	
	/**
	 * 该接口提供所有微信支付订单的查询</br>
	 * 商户可以通过该接口主动查询订单状态</br>
	 * @param xml
	 * @return 查询失败
	 */
	public Map<String,Object> orderquery(String xml){
		String re = HttpClientConnectionManager.request("https://api.mch.weixin.qq.com/pay/orderquery", xml, HttpClientConnectionManager.POST);
		if (re!=null) {
			return XmlUtils.getXmlPayOrderquery(re);
		}
		return  null;
	}
	
}
