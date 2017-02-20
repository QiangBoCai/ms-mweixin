package com.mingsoft.weixin.entity;

import java.util.Date;

import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.weixin.constant.e.QrcodeTypeEnum;

/** 
 * 微信二维码实体
 * @author  付琛  QQ:1658879747 
 * @version 1.0 
 * 创建时间：2015年11月13日 下午3:58:30  
 * 版本号：100-000-000<br/>
 * 历史修订<br/>
 */
public class WeixinQrcodeEntity extends BaseEntity{
	/**
	 * 二维码自增长ID
	 */
	private int qrcodeId;
	
	/**
	 * 二维码名称
	 */
	private String qrcodeTitle;
	
	/**
	 * 二维码场景值
	 */
	private int qrcodeValue;
	
	/**
	 * 二维码类型：1.永久二维码2.临时二维码
	 */
	private int qrcodeType;
	
	/**
	 * 二维码对应应用ID
	 */
	private int qrcodeAppId;
	
	/**
	 * 二维码描述
	 */
	private String qrcodeDescription;
	
	/**
	 * 二维码生成时间
	 */
	private Date qrcodeTime;
	
	/**
	 * 二维码到期时间，最大为1800，以秒为单位(只有临时二维码才有该值)
	 */
	private int qrcodeExpireTime;
	
	/**
	 * 二维码对应微信ID，一个公众号下只对应一个二维码
	 */
	private int qrcodeWeixinId;

	public int getQrcodeId() {
		return qrcodeId;
	}

	public void setQrcodeId(int qrcodeId) {
		this.qrcodeId = qrcodeId;
	}

	public String getQrcodeTitle() {
		return qrcodeTitle;
	}

	public void setQrcodeTitle(String qrcodeTitle) {
		this.qrcodeTitle = qrcodeTitle;
	}

	public int getQrcodeValue() {
		return qrcodeValue;
	}

	public void setQrcodeValue(int qrcodeValue) {
		this.qrcodeValue = qrcodeValue;
	}

	public int getQrcodeType() {
		return qrcodeType;
	}
	
	@Deprecated
	public void setQrcodeType(int qrcodeType) {
		this.qrcodeType = qrcodeType;
	}
	
	public void setQrcodeType(QrcodeTypeEnum qrcodeType) {
		this.qrcodeType = qrcodeType.toInt();
	}
	
	public int getQrcodeAppId() {
		return qrcodeAppId;
	}

	public void setQrcodeAppId(int qrcodeAppId) {
		this.qrcodeAppId = qrcodeAppId;
	}

	public String getQrcodeDescription() {
		return qrcodeDescription;
	}

	public void setQrcodeDescription(String qrcodeDescription) {
		this.qrcodeDescription = qrcodeDescription;
	}

	public Date getQrcodeTime() {
		return qrcodeTime;
	}

	public void setQrcodeTime(Date qrcodeTime) {
		this.qrcodeTime = qrcodeTime;
	}

	public int getQrcodeExpireTime() {
		return qrcodeExpireTime;
	}

	public void setQrcodeExpireTime(int qrcodeExpireTime) {
		this.qrcodeExpireTime = qrcodeExpireTime;
	}

	public int getQrcodeWeixinId() {
		return qrcodeWeixinId;
	}

	public void setQrcodeWeixinId(int qrcodeWeixinId) {
		this.qrcodeWeixinId = qrcodeWeixinId;
	}
	
	
}
