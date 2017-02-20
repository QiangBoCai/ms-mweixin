package com.mingsoft.weixin.test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import com.mingsoft.base.test.BaseTest;
import com.mingsoft.weixin.bean.UploadNewsBean;
import com.mingsoft.weixin.util.MessageMassUtils;
import com.mingsoft.weixin.util.UploadDownUtils;
import com.mingsoft.weixin.util.XmlUtils;

/** 
 * 群发消息测试类
 * @author  付琛  QQ:1658879747 
 * @version 1.0 
 * 创建时间：2015年11月19日 上午11:45:50  
 * 版本号：100-000-000<br/>
 * 历史修订<br/>
 */
public class MessageMassUtilsTest extends BaseTest{
	
	/**
	 * 上传图片
	 * @return 返回图片url
	 */
	public String uploadPic(String localUrl){
		UploadDownUtils uploadownUitls = new UploadDownUtils("wx7e5c5824684a7005","f521c14a77f1179e846303ff33c86089");
//		String tmp =  uploadownUitls.uploadMedia(UploadDownUtils.TYPE_IMAGE, localUrl);
//		LOG.debug("上传图片后的返回值============"+tmp);
//		return tmp;
		return null;
	}
	
	/**
	 * 上传素材
	 * @return 素材的media_id
	 */
	public String uploadNews(){
		UploadDownUtils uploadownUitls = new UploadDownUtils("wx7e5c5824684a7005","f521c14a77f1179e846303ff33c86089");
		String thumb_media_id_1 = this.uploadPic("G:/apache-tomcat-7.0.65/webapps/ms-mweixin//upload/article/1/1450496801304.png");
		String thumb_media_id_2 = this.uploadPic("G:/1442807062142.png");
		UploadNewsBean newsBean = new UploadNewsBean("群发图文测试", "群发图文内容");
		newsBean.setThumb_media_id(thumb_media_id_1);
		UploadNewsBean newsBean2 = new UploadNewsBean("分组群发测试", "群发内容内容");
		newsBean2.setThumb_media_id(thumb_media_id_2);
		List<UploadNewsBean> list = new ArrayList<UploadNewsBean>();
		list.add(newsBean);
		list.add(newsBean2);
		String newsJsonUpload = XmlUtils.uploadJsonNews(list);
		String tmp = uploadownUitls.uploadNews(newsJsonUpload);
		LOG.debug("上传图文素材后的返回值============================"+tmp);
		return tmp;
	}
	
	/**
	 * 预览图文消息
	 */
	@Test
	public void previewMessageNews(){
		MessageMassUtils messageMassUtils = new MessageMassUtils("wx7e5c5824684a7005","f521c14a77f1179e846303ff33c86089");
		String mediaId = this.uploadNews();
		LOG.debug("调用后的mediaID，即图文ID========================="+mediaId);
		String toUser = "o8pZtuKO9Vpb3ghsOQD5uBp7Go5g";
		Boolean tmp =messageMassUtils.preivewMessageMass(toUser, mediaId);
		LOG.debug("预览成功后的返回值============================"+tmp);
	}
	
	/**
	 * 分组群发图文消息
	 */
	public void sendMessageNews(){
		MessageMassUtils messageMassUtils = new MessageMassUtils("wx7e5c5824684a7005","f521c14a77f1179e846303ff33c86089");
		String mediaId = this.uploadNews();
		Boolean tmp = messageMassUtils.sendAllMessageMass(mediaId, true, null);
		LOG.debug("分组群发图文消息后的返回结果：msgId的值为============"+tmp);
	}
	
//	/**
//	 * 查询群发后的状态
//	 */
//	@Test
//	public void getMessageMass(){
//		MessageMassUtils messageMassUtils = new MessageMassUtils("wx7e5c5824684a7005","f521c14a77f1179e846303ff33c86089");
//		String msgId = String.valueOf(this.sendMessageNews());
//		String tmp =  (String) messageMassUtils.getMessageMass(msgId).get("msg_status");
//		LOG.debug("群发后的结果：=============="+tmp);
//		
//	}
}
