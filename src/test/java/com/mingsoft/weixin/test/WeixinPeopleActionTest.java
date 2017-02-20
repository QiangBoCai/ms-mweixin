//package com.mingsoft.weixin.test;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.alibaba.fastjson.JSONArray;
//import com.mingsoft.base.test.BaseTest;
//import com.mingsoft.util.JsonUtil;
//import com.mingsoft.weixin.bean.NewsBean;
//import com.mingsoft.weixin.biz.IWeixinPeopleBiz;
//import com.mingsoft.weixin.entity.WeixinPeopleEntity;
//import com.mingsoft.weixin.util.MessageUtils;
//import com.mingsoft.weixin.util.UserUtils;
//
///**
// * 
// * 微信用户业务层测试
// * @author 成卫雄(qq:330216230)
// * @version 
// * 版本号：100-000-000<br/>
// * 创建日期：2015年8月31日 上午10:30:43<br/>
// * 历史修订：<br/>
// */
//public class WeixinPeopleActionTest extends BaseTest{
//
//	@Autowired
//	private IWeixinPeopleBiz weixinPeopleBiz;
//	
//	/**
//	 * 测试微信获取用户信息接口
//	 */
//	public List<String> queryUserOpenidList(){
//		UserUtils user = new UserUtils("wx7e5c5824684a7005","f521c14a77f1179e846303ff33c86089");
//		//得到用户相关集合（包含总数和openId集合）
//		Map<String,Object> userMap =  user.queryUserOpenidList();
//	//	LOG.debug(userMap);
//		//得到data的map集合
//		Map<String,Object> userOpenIdMap = JsonUtil.getMap4Json(userMap.get("data").toString()); 
//	//	LOG.debug(userOpenIdMap);
//		//得到openid集合
//		List<String> list =  JSONArray.parseArray(userOpenIdMap.get("openid").toString(), String.class);
//	//	LOG.debug(list);
//		return list;
//	}
//	
////	public Map<Object,String> queryUserInfo(){
////		UserUtils user = new UserUtils("wx7e5c5824684a7005","f521c14a77f1179e846303ff33c86089");
////		user.queryAllUserInfo(nextOpenid, peopleNum);
////	}
//	
//	/**
//	 * 测试微信用户发送消息
//	 */
//	@Test
//	public void sendMessage(){
//		MessageUtils messageUtil = new MessageUtils("wx7e5c5824684a7005","f521c14a77f1179e846303ff33c86089");
//		//得到用户openId的list集合
//		List<String> openIdList = this.queryUserOpenidList();
//		//遍历所有用户基本信息
//		for(int i=0;i<openIdList.size();i++){
//			//得到所有用户基本信息集合
//		//	Map<String,Object> userInfoMap = user.syncUserInfo(openIdList.get(i));
//		//	LOG.debug(userInfoMap);			
//		//	LOG.debug(userInfoMap.get("nickname"));			
//		//	String temp = messageUtil.sendTextToUser("o8pZtuKO9Vpb3ghsOQD5uBp7Go5g","Mark");
//			NewsBean newsBean = new NewsBean();
//			NewsBean newsBean2 = new NewsBean();
//			NewsBean newsBean3 = new NewsBean();
//			newsBean.setTitle("多图文测试大标题");
//			newsBean.setPicurl("http://pic14.nipic.com/20110522/7411759_164157418126_2.jpg");
//			newsBean.setDescription("多图文大描述");
//			newsBean.setUrl("http://ming-soft.com");
//			newsBean2.setTitle("多图文小标题");
//			newsBean2.setPicurl("http://ming-soft.com/templets/1/ming-soft//images/fei.jpg");
//			newsBean2.setDescription("多图文小描述");
//			newsBean2.setUrl("http://www.baidu.com");
//			newsBean3.setTitle("多图文副标题");
//			newsBean3.setPicurl("http://img2.3lian.com/img2007/19/33/005.jpg");
//			newsBean3.setDescription("多图文副描述");
//			newsBean3.setUrl("http://ming-soft.com/html/1//5526/index.html");
//			List<NewsBean> list = new ArrayList<NewsBean>();
//			list.add(newsBean);
//			list.add(newsBean2);
//			list.add(newsBean3);
//			String newsTmep = messageUtil.sendNewsToUser(openIdList.get(i), list);
//	//		String newsTemp1 = messageUtil.sendNewsToUser("o8pZtuNV4Fr608H8oozibwDBqM1Y", list);
//	//		String temp1 = messageUtil.sendTextToUser("o8pZtuIjlszCOQSZzkiwqBS1EZPI","铭飞科技");
//	//		String temp2 = messageUtil.sendTextToUser("o8pZtuL7F25QtlSGe3eneLDD5knk","JOY");
//	//		String temp3 = messageUtil.sendTextToUser("o8pZtuNV4Fr608H8oozibwDBqM1Y","梦中故里");
//			LOG.debug(newsTmep);
//	//		LOG.debug(newsTemp1);
////			LOG.debug(temp1);
////			LOG.debug(temp2);
////			LOG.debug(temp3);
//		}
//	}
//	
//	/**
//	 * 新增测试
//	 */
//	//@Test
//	public void save(){
//		WeixinPeopleEntity weixinPeopleEntity = new WeixinPeopleEntity();
//		weixinPeopleEntity.setPeopleUserNickName("111111111111");
//		weixinPeopleEntity.setWeixinPeopleState(WeixinPeopleEntity.WEIXIN_PEOPLE_OPEN);
//		weixinPeopleEntity.setPeopleAppId(83);
//		weixinPeopleEntity.setWeixinPeopleAppId(83);
//		weixinPeopleEntity.setWeixinPeopleWeixinId(1);
//		weixinPeopleEntity.setWeixinPeopleOpenId("dasdasdas");
//		weixinPeopleEntity.setPeopleDateTime(new Date());
//		this.weixinPeopleBiz.savePeopleUser(weixinPeopleEntity);		
//	}
//	
//	
//}
