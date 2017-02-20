
package com.mingsoft.weixin.entity;

import com.mingsoft.base.entity.BaseEntity;

/**
 * 微信红包实体
 * @Package com.mingsoft.weixin.entity 
 * @author 李书宇
 * @version 
 * 版本号：<br/>
 * 创建日期：@date 2015年9月9日<br/>
 * 历史修订：<br/>
 */
public class WeixinRedPackEntity extends BaseEntity{

	/**
	 * 红包id
	 */
	private int redPacketId;
	
	/**
	 * 微信id
	 */
	private int redPacketWeixinId;
	
	/**
	 * 站点id
	 */
	private int redPacketAppId;
	
	/**
	 * 红包祝福语
	 */
	private String redPacketWishing;
	
	/**
	 * 活动名称
	 */
	private String redPacketActivityName;
	
	/**
	 * 备注
	 */
	private String redPacketRemark;
	
	/**
	 * 红包金额
	 */
	private int redPacketTotalMoney;
	
	/**
	 * 红包发放总数
	 */
	private int redPacketTotalNum;
	
	/**
	 * 红包发放的用户openId
	 */
	private int redPacketOpenId;

	public int getRedPacketId() {
		return redPacketId;
	}

	public void setRedPacketId(int redPacketId) {
		this.redPacketId = redPacketId;
	}

	public int getRedPacketWeixinId() {
		return redPacketWeixinId;
	}

	public void setRedPacketWeixinId(int redPacketWeixinId) {
		this.redPacketWeixinId = redPacketWeixinId;
	}

	public int getRedPacketAppId() {
		return redPacketAppId;
	}

	public void setRedPacketAppId(int redPacketAppId) {
		this.redPacketAppId = redPacketAppId;
	}

	public String getRedPacketWishing() {
		return redPacketWishing;
	}

	public void setRedPacketWishing(String redPacketWishing) {
		this.redPacketWishing = redPacketWishing;
	}

	public String getRedPacketActivityName() {
		return redPacketActivityName;
	}

	public void setRedPacketActivityName(String redPacketActivityName) {
		this.redPacketActivityName = redPacketActivityName;
	}

	public String getRedPacketRemark() {
		return redPacketRemark;
	}

	public void setRedPacketRemark(String redPacketRemark) {
		this.redPacketRemark = redPacketRemark;
	}

	public int getRedPacketTotalMoney() {
		return redPacketTotalMoney;
	}

	public void setRedPacketTotalMoney(int redPacketTotalMoney) {
		this.redPacketTotalMoney = redPacketTotalMoney;
	}

	public int getRedPacketTotalNum() {
		return redPacketTotalNum;
	}

	public void setRedPacketTotalNum(int redPacketTotalNum) {
		this.redPacketTotalNum = redPacketTotalNum;
	}

	public int getRedPacketOpenId() {
		return redPacketOpenId;
	}

	public void setRedPacketOpenId(int redPacketOpenId) {
		this.redPacketOpenId = redPacketOpenId;
	}
	
	
	
}
