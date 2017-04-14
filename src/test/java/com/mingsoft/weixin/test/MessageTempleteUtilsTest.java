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
import com.mingsoft.weixin.util.MessageTemplateUtils;

/** 
 * 微信模板消息测试类
 * @author  付琛  QQ:1658879747 
 * @version 1.0 
 * 创建时间：2015年11月20日 下午8:31:59  
 * 版本号：100-000-000<br/>
 * 历史修订<br/>
 */
public class MessageTempleteUtilsTest extends BaseTest{
	
	/**
	 * 获取模板ID
	 * @return
	 */
	public String getTepleteId(){
		MessageTemplateUtils messageTempeteUtils = new MessageTemplateUtils("wx7e5c5824684a7005","f521c14a77f1179e846303ff33c86089");
		String templeteId =  messageTempeteUtils.getTempletId("TM00001");
		LOG.debug("模板ID的返回值========"+templeteId);
		return templeteId;
	}
	
	/**
	 * 发送模板消息
	 */
	@Test
	public void sendTempleteMessage(){
		MessageTemplateUtils messageTempeteUtils = new MessageTemplateUtils("wx7e5c5824684a7005","f521c14a77f1179e846303ff33c86089");
		String tempeleteId = this.getTepleteId();
		Boolean bool =  messageTempeteUtils.sendToUser("o8pZtuKO9Vpb3ghsOQD5uBp7Go5g", tempeleteId, "http://www.baidu.com", "blue", "模板消息测试", "black", "模板备注", "green", "关键字");
		LOG.debug(bool);
	}
}
