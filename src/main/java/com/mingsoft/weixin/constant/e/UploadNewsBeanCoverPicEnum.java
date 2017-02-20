package com.mingsoft.weixin.constant.e;

import com.mingsoft.base.constant.e.BaseEnum;

/** 
 * 上传素材是否显示封面 0显示 1不显示
 * @author  付琛  QQ:1658879747 
 * @version 1.0 
 * 创建时间：2015年11月22日 下午4:31:21  
 * 版本号：100-000-000<br/>
 * 历史修订<br/>
 */
public enum UploadNewsBeanCoverPicEnum implements BaseEnum{
	/**
	 * 不显示封面
	 */
	HIDE_COVER_PIC(0,"不显示封面"),
	
	/**
	 * 显示封面
	 */
	SHOW_COVER_PIC(1,"显示封面");
	
	UploadNewsBeanCoverPicEnum(int id,Object code){
		this.id = id;
		this.code = code;
	}
	private Object code;

	private int id;

	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return code.toString();
	}
	

}
