//package com.mingsoft.weixin.test;
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
