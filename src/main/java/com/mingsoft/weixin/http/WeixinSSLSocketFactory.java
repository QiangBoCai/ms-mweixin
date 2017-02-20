package com.mingsoft.weixin.http;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

/**
 * Copyright: Copyright (c) 2014 - 2015
 * Company:景德镇铭飞科技有限公司
 * @author wangtp
 * @version 300-001-001
 * 版权所有 铭飞科技
 * Comments: SSL通用类,单例模式的类，外包不需要修改此类,只提供一个getInstance方法
 * Create Date:2013-12-23
 *  Modification history:
 */
public final class WeixinSSLSocketFactory extends SSLSocketFactory {

	static {
		mySSLSocketFactory = new WeixinSSLSocketFactory(createSContext());
	}

	private static WeixinSSLSocketFactory mySSLSocketFactory = null;

	private WeixinSSLSocketFactory(SSLContext sslContext) {
		super(sslContext);
		this.setHostnameVerifier(ALLOW_ALL_HOSTNAME_VERIFIER);
	}

	/**
	 * 活动ssl公测对象
	 * @return WeixinSSLSocketFactory
	 */
	public static WeixinSSLSocketFactory getInstance() {
		if (mySSLSocketFactory != null) {
			return mySSLSocketFactory;
		} else {
			return mySSLSocketFactory = new WeixinSSLSocketFactory(
					createSContext());
		}
	}

	private static SSLContext createSContext() {
		SSLContext sslcontext = null;
		try {
			sslcontext = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			sslcontext.init(null,
					new TrustManager[] { new TrustAnyTrustManager() }, null);
		} catch (KeyManagementException e) {
			e.printStackTrace();
			return null;
		}
		return sslcontext;
	}

}