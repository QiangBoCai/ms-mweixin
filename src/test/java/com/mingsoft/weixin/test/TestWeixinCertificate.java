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
 *///package com.mingsoft.weixin.test;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//
//import com.mingsoft.weixin.biz.IWeixinCertificateBiz;
//import com.mingsoft.weixin.constant.e.WeixinCertificateEnum;
//import com.mingsoft.weixin.entity.WeixinCertificateEntity;
//
///**
// * 
// * 微信证书测试类
// * @author 史爱华(qq:924690193)
// * @version 
// * 版本号：100-000-000<br/>
// * 创建日期：2015年9月09日 上午16:05:46<br/>
// * 历史修订：<br/>
// */
//public class TestWeixinCertificate extends com.mingsoft.base.test.BaseTest{
//	
//	/**
//	 * 微信证书实现类
//	 */
//	private IWeixinCertificateBiz weixinCertificateBiz;
//	
//	/**
//	 * 获取证书业务层
//	 * @return
//	 */
//	public IWeixinCertificateBiz getWeixinCertificateBiz() {
//		return weixinCertificateBiz;
//	}
//	
//	/**
//	 * 注入证书业务层
//	 * @param weixinCertificateBiz
//	 */
//	@Resource(name = "weixinCertificateBiz")
//	public void setWeixinCertificateBiz(IWeixinCertificateBiz weixinCertificateBiz) {
//		this.weixinCertificateBiz = weixinCertificateBiz;
//	}
//	
//	/**
//	 * 测试根据id获取证书实体
//	 */
//	@Test
//	public void getEntity(){
//		WeixinCertificateEntity weixinCretificate = (WeixinCertificateEntity) weixinCertificateBiz.getEntity(1);
//		LOG.debug(weixinCretificate);
//	}
//	
//	/**
//	 * 测试根据微信id 和应用id查询证书实体
//	 */
//	@Test
//	public void geteixinCertificate(){
//		WeixinCertificateEntity weixinCretificate =weixinCertificateBiz.getWeixinCertificate(1548, 12, WeixinCertificateEnum.ENTITYSHOP);
//		LOG.debug(weixinCretificate);
//	}
//}
