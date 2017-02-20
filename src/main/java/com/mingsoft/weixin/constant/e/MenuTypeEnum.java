package com.mingsoft.weixin.constant.e;

import com.mingsoft.base.constant.e.BaseEnum;

/**
 * 铭飞科技
 * Copyright: Copyright (c) 2014 - 2015
 * @author 王天培 QQ:78750478Comments:菜单点击类型
 * Create Date:2015-1-19Modification history:
 * 1、click：点击推事件</br> 2、view：跳转URL</br> 3、scancode_push：扫码推事件</br>
 * 4、scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框</br>
 * 5、pic_sysphoto：弹出系统拍照发图</br> 6、pic_photo_or_album：弹出拍照或者相册发图</br>
 * 7、pic_weixin：弹出微信相册发图器</br> 8、location_select：弹出地理位置选择器</br>
 */
public enum MenuTypeEnum implements BaseEnum {

	/**
	 *点击推事件
	 */
	CLICK(1), 
	/**
	 * 跳转URL
	 */
	VIEW(2), 
	/**
	 * 扫码推事件
	 */
	SCANCODE_PUSH(3), 
	/**
	 * 扫码推事件且弹出“消息接收中”提示框
	 */
	SCANCODE_WAITMSG(4),
	/**
	 * 弹出系统拍照发图
	 */
	PIC_SYSPHOTO(5), 
	/**
	 * 弹出拍照或者相册发图
	 */
	PIC_PHOTO_OR_ALBUM(6), 
	/**
	 * 弹出微信相册发图器
	 */
	PIC_WEIXIN(7), 
	/**
	 * 弹出地理位置选择器
	 */
	LOCATION_SELECT(8);

	MenuTypeEnum(Object code) {
		this.code = code;
	}

	private Object code;

	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return Integer.valueOf(code + "");
	}

}
