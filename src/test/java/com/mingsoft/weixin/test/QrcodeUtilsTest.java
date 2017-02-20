package com.mingsoft.weixin.test;

import org.junit.Test;

import com.mingsoft.base.test.BaseTest;
import com.mingsoft.weixin.util.QrcodeUtils;

/**
 * 
 * 生成带参数的二维码的工具类测试
 * @author 成卫雄(qq:330216230)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年8月26日 下午6:55:29<br/>
 * 历史修订：<br/>
 */
public class QrcodeUtilsTest extends BaseTest{

	QrcodeUtils qrcodeUtil = new QrcodeUtils("wx7cce6e06b8270c8a","af260b6712031d3872e4426172c78d78");
	
	@Test
	public void test(){
		qrcodeUtil.createLimitQrcode("C:\\2.jpg", 2);
	}
	
}
