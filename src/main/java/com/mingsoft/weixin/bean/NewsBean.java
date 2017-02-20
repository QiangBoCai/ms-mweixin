package com.mingsoft.weixin.bean;

/**
 * Copyright: Copyright (c) 2014 - 2015
 * Company:景德镇铭飞科技有限公司
 * @author wangtp
 * @version 300-001-001
 * 版权所有 铭飞科技
 * Comments: 微信发送被动响应消息bean
 * Create Date:2013-12-23
 * Modification history:
 */
public class NewsBean extends BaseBean {

	/**
	 * 消息描述
	 */
	private String description;

	/**
	 * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	 */
	private String picurl;

	/**
	 * 消息标题
	 */
	private String title;

	/**
	 * 点击图文消息跳转链接
	 */
	private String url;

	public String getDescription() {
		return description;
	}

	public String getPicurl() {
		return picurl;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
