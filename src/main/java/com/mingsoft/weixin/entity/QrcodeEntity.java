package com.mingsoft.weixin.entity;

import java.util.Date;

import javax.ws.rs.FormParam;

import org.springframework.format.annotation.DateTimeFormat;

import com.mingsoft.base.entity.BaseEntity;

 /**
 * 微信带参数的二维码实体
 * @author 铭飞开发团队
 * @version 
 * 版本号：1.0.0<br/>
 * 创建日期：2017-5-26 16:57:29<br/>
 * 历史修订：<br/>
 */
public class QrcodeEntity extends BaseEntity {

	private static final long serialVersionUID = 1495789049607L;
	
	/**
	 * 自增长ID
	 */
	private Integer qrcodeId; 
	/**
	 * 二维码名称
	 */
	private String qrcodeTitle; 
	/**
	 * 二维码的场景值
	 */
	private Integer qrcodeValue; 
	/**
	 * 二维码类型(1.永久二维码，2.临时二维码)
	 */
	private Integer qrcodeType; 
	/**
	 * 二维码所属应用ID
	 */
	private Integer qrcodeAppId; 
	/**
	 * 二维码描述
	 */
	private String qrcodeDescription; 
	/**
	 * 二维码生成时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date qrcodeTime; 
	/**
	 * 二维码到期时间，最大为1800，以秒为单位(只有临时二维码才有该值)
	 */
	private Integer qrcodeExpireTime; 
	/**
	 * 微信编号
	 */
	private Integer qrcodeWeixinId; 
	
		
	/**
	 * 设置自增长ID
	 */
	public void setQrcodeId(Integer qrcodeId) {
		this.qrcodeId = qrcodeId;
	}

	/**
	 * 获取自增长ID
	 */
	public Integer getQrcodeId() {
		return this.qrcodeId;
	}
	
	/**
	 * 设置二维码名称
	 */
	public void setQrcodeTitle(String qrcodeTitle) {
		this.qrcodeTitle = qrcodeTitle;
	}

	/**
	 * 获取二维码名称
	 */
	public String getQrcodeTitle() {
		return this.qrcodeTitle;
	}
	
	/**
	 * 设置二维码的场景值
	 */
	public void setQrcodeValue(Integer qrcodeValue) {
		this.qrcodeValue = qrcodeValue;
	}

	/**
	 * 获取二维码的场景值
	 */
	public Integer getQrcodeValue() {
		return this.qrcodeValue;
	}
	
	/**
	 * 设置二维码类型(1.永久二维码，2.临时二维码)
	 */
	public void setQrcodeType(Integer qrcodeType) {
		this.qrcodeType = qrcodeType;
	}

	/**
	 * 获取二维码类型(1.永久二维码，2.临时二维码)
	 */
	public Integer getQrcodeType() {
		return this.qrcodeType;
	}
	
	/**
	 * 设置二维码所属应用ID
	 */
	public void setQrcodeAppId(Integer qrcodeAppId) {
		this.qrcodeAppId = qrcodeAppId;
	}

	/**
	 * 获取二维码所属应用ID
	 */
	public Integer getQrcodeAppId() {
		return this.qrcodeAppId;
	}
	
	/**
	 * 设置二维码描述
	 */
	public void setQrcodeDescription(String qrcodeDescription) {
		this.qrcodeDescription = qrcodeDescription;
	}

	/**
	 * 获取二维码描述
	 */
	public String getQrcodeDescription() {
		return this.qrcodeDescription;
	}
	
	/**
	 * 设置二维码生成时间
	 */
	public void setQrcodeTime(Date qrcodeTime) {
		this.qrcodeTime = qrcodeTime;
	}

	/**
	 * 获取二维码生成时间
	 */
	public Date getQrcodeTime() {
		return this.qrcodeTime;
	}
	
	/**
	 * 设置二维码到期时间，最大为1800，以秒为单位(只有临时二维码才有该值)
	 */
	public void setQrcodeExpireTime(Integer qrcodeExpireTime) {
		this.qrcodeExpireTime = qrcodeExpireTime;
	}

	/**
	 * 获取二维码到期时间，最大为1800，以秒为单位(只有临时二维码才有该值)
	 */
	public Integer getQrcodeExpireTime() {
		return this.qrcodeExpireTime;
	}
	
	/**
	 * 设置微信编号
	 */
	public void setQrcodeWeixinId(Integer qrcodeWeixinId) {
		this.qrcodeWeixinId = qrcodeWeixinId;
	}

	/**
	 * 获取微信编号
	 */
	public Integer getQrcodeWeixinId() {
		return this.qrcodeWeixinId;
	}
	
}