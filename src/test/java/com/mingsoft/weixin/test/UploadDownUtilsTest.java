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
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.Test;
//import com.mingsoft.base.test.BaseTest;
//import com.mingsoft.weixin.bean.UploadNewsBean;
//import com.mingsoft.weixin.util.UploadDownUtils;
//import com.mingsoft.weixin.util.XmlUtils;
//
///** 
// * 上传素材测试类
// * @author  付琛  QQ:1658879747 
// * @version 1.0 
// * 创建时间：2015年11月19日 上午12:11:15  
// * 版本号：100-000-000<br/>
// * 历史修订<br/>
// */
//public class UploadDownUtilsTest extends BaseTest{
//	
//	/**
//	 * 上传图片获取thumb_media_id
//	 * @return 图片返回的thumb_media_id
//	 */
//	public String upload(){
//		UploadDownUtils uploadownUitls = new UploadDownUtils("wx7e5c5824684a7005","f521c14a77f1179e846303ff33c86089");
//		String tmp =  uploadownUitls.uploadMedia(UploadDownUtils.TYPE_IMAGE, "G:/QQ图片20151118164433.png");
//		LOG.debug("上传图片后的返回值============"+tmp);
//		return tmp;
//	}
//	
//	/**
//	 * 上传素材获取media_id
//	 */
//	@Test
//	public void uploadNews(){
//		UploadDownUtils uploadownUitls = new UploadDownUtils("wx7e5c5824684a7005","f521c14a77f1179e846303ff33c86089");
//		String thumb_media_id = this.upload();
//		UploadNewsBean newsBean = new UploadNewsBean(thumb_media_id, "群发图文测试", "群发图文内容");
//		UploadNewsBean newsBean2 = new UploadNewsBean(thumb_media_id, "分组群发测试", "群发内容内容");
//		List<UploadNewsBean> list = new ArrayList<UploadNewsBean>();
//		list.add(newsBean);
//		list.add(newsBean2);
//		String newsJsonUpload = XmlUtils.uploadJsonNews(list);
//		uploadownUitls.uploadNews(newsJsonUpload);
//	}
//}
