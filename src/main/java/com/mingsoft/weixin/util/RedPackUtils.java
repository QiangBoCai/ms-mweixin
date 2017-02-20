package com.mingsoft.weixin.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.mingsoft.util.StringUtil;
import com.mingsoft.weixin.bean.WeixinRedPackBean;


/**
 * 发送红包证书类型</br>
 * 微信商户平台约定java开发调用的证书类型为:PKCS12</br>
 * @author 史爱华(qq:924690193)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年9月08日 上午11:24:46<br/>
 * 历史修订：<br/>
 */
public class RedPackUtils extends BaseUtils {
	
	/**
	 * 红包发送请求
	 */
	private final static String SEND_RED_PACK="https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	
	/**
	 * 发送红包证书名
	 */
	private final static String SEND_RED_CRETIFICATEKEY="PKCS12";
	
	/**
	 * 发送红包需要的key值
	 */
	protected String key;
	
	/**
	 * 发送红包成功后返回的状态
	 */
	private final static String SEND_STATIC_SUCCESS = "SUCCESS";
	
	/**
	 * 构造函数
	 * @param appid 应用id
	 * @param secret 
	 * @param key 发送红包需要的key值
	 */
	public RedPackUtils(String appid, String secret,String key) {
		super(appid, secret);
		this.key=key;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 发送红包接口
	 * @param weixinRedPack  发送红包所需的参数bean类
	 * @return 是否发送成功 false:发送失败 true:发送成功
	 * @throws Exception
	 */
	public boolean sendRedPack(WeixinRedPackBean weixinRedPack) throws Exception {
		
		KeyStore keyStore  = KeyStore.getInstance(SEND_RED_CRETIFICATEKEY);
		//读取证书
        FileInputStream instream = new FileInputStream(new File(weixinRedPack.getCertificateLocation()));
        try {
            keyStore.load(instream, weixinRedPack.getCertificateKey().toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, weixinRedPack.getCertificateKey().toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        
        
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(30000).build();
        
        
        HttpPost httpPost = new HttpPost(SEND_RED_PACK);
        
        //获得红包发送时所需的xml
        String xml = XmlUtils.buildXmlRedPack(weixinRedPack);
        
	      StringEntity postEntity = new StringEntity(xml, "UTF-8");
	      httpPost.addHeader("Content-Type", "text/xml");
	      httpPost.setEntity(postEntity);

	      httpPost.setConfig(requestConfig);       

      
	      logger.debug("executing request" + httpPost.getRequestLine());
	      
	      try {
	    	  HttpResponse response = httpclient.execute(httpPost);
	    	  HttpEntity entity = response.getEntity();
	    	  String result = EntityUtils.toString(entity, "UTF-8");
	    	  logger.debug(result);
	    	  //获取返回xml中的错误码result_code，判断红包是否领取成功
	    	  String resultCode = XmlUtils.getString(result,"result_code");
	    	  if(!StringUtil.isBlank(resultCode) && resultCode.equals(SEND_STATIC_SUCCESS)){//用户领取红包成功
	    		  return true;
	    	  }else{//用户领取红包失败
	    		  return false;
	    	  }
	      }catch(Exception e){
	    	  return false;
	      }
	}

}
