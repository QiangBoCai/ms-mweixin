//package com.mingsoft.weixin.test;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//
//import com.mingsoft.weixin.biz.IWeixinRedPackBiz;
//import com.mingsoft.weixin.entity.WeixinRedPackEntity;
//
///**
// * 
// * 微信红包测试类
// * @author 史爱华(qq:924690193)
// * @version 
// * 版本号：100-000-000<br/>
// * 创建日期：2015年9月09日 上午16:05:46<br/>
// * 历史修订：<br/>
// */
//public class TestWeixinRedPack extends com.mingsoft.base.test.BaseTest{
//	/**
//	 * 微信红包业务层
//	 */
//	private IWeixinRedPackBiz weixinRedPackBiz;
//	
//	/**
//	 * 获取微信红包业务层
//	 * @return
//	 */
//	public IWeixinRedPackBiz getWeixinRedPackNoBiz() {
//		return weixinRedPackBiz;
//	}
//	
//	/**
//	 * 注入微信红包业务层
//	 * @param weixinRedPackNoBiz  微信红包业务层
//	 */
//	@Resource(name = "weixinRedPackBiz")
//	public void setWeixinRedPackBiz(IWeixinRedPackBiz weixinRedPackBiz) {
//		this.weixinRedPackBiz = weixinRedPackBiz;
//	}
//	
//	/**
//	 * 测试微信红包保存方法
//	 */
//	@Test
//	public void save(){
//		WeixinRedPackEntity weixinRedPack = new WeixinRedPackEntity();
//		weixinRedPack.setRedPacketActivityName("红包活动");
//		weixinRedPack.setRedPacketAppId(1548);
//		weixinRedPack.setRedPacketRemark("红包活动备注");
//		weixinRedPack.setRedPacketTotalAmount(90.09);
//		weixinRedPack.setRedPacketTotalNum(32);
//		weixinRedPack.setRedPacketWeixinId(2);
//		weixinRedPack.setRedPacketWishing("红包祝福语");
//		weixinRedPackBiz.saveEntity(weixinRedPack);
//	}
//	
//	/**
//	 * 测试微信红包更新
//	 */
//	@Test
//	public void update(){
//		WeixinRedPackEntity weixinRedPack = (WeixinRedPackEntity) weixinRedPackBiz.getEntity(1);
//		weixinRedPack.setRedPacketActivityName("更新红包活动");
//		weixinRedPack.setRedPacketRemark("更新红包备注");
//		weixinRedPack.setRedPacketTotalAmount(8890);
//		weixinRedPack.setRedPacketWishing("更新红包祝福语");
//		weixinRedPack.setRedPacketWeixinId(13);
//		weixinRedPack.setRedPacketTotalNum(1234);
//		weixinRedPackBiz.updateEntity(weixinRedPack);
//	}
//	
//	/**
//	 * 测试查询微信红包列表
//	 */
//	@Test
//	public void query(){
//		List<WeixinRedPackEntity> weixinRedPack = weixinRedPackBiz.queryByAppIdAndWeixinId(1548, 13);
//		LOG.debug(weixinRedPack);
//	}
//}
