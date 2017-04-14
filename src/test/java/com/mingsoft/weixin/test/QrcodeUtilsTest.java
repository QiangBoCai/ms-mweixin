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
 */package com.mingsoft.weixin.test;

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
