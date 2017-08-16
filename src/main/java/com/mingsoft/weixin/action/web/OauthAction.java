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
 */package com.mingsoft.weixin.action.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mingsoft.base.constant.Const;
import com.mingsoft.people.biz.IPeopleBiz;
import com.mingsoft.people.entity.PeopleEntity;
import com.mingsoft.util.StringUtil;
import com.mingsoft.weixin.action.BaseAction;
import com.mingsoft.weixin.biz.IWeixinBiz;
import com.mingsoft.weixin.biz.IWeixinPeopleBiz;
import com.mingsoft.weixin.constant.SessionConst;
import com.mingsoft.weixin.entity.WeixinEntity;
import com.mingsoft.weixin.entity.WeixinPeopleEntity;
import com.mingsoft.weixin.util.WeixinOpenLoginUtil;
import com.mingsoft.weixin.util.bean.WeixinPeopleEntityUtils;

import net.mingsoft.basic.util.BasicUtil;

/**
 * 微信授权登录
 * @author Administrator
 *
 */
@Controller("webOauthAction")
@RequestMapping("/mweixin/oauth")
public class OauthAction extends BaseAction{
	
	/**
	 * 微信业务层
	 */
	@Autowired
	private IWeixinBiz weixinBiz;
	
	/**
	 * 微信用户业务层
	 */
	@Autowired
	private IWeixinPeopleBiz weixinPeopleBiz;
	
	
	@Autowired
	private IPeopleBiz peopleBiz;
	
	
	/**
	 * 授权用户后回调接受参数的方法,该方法用在
	 * 实例： https://open.weixin.qq.com/connect/oauth2/authorize?appid=&redirect_uri=自定义&response_type=code&scope=snsapi_base&state=自定义#wechat_redirect
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(HttpServletRequest request,HttpServletResponse response,RedirectAttributes model){
		//接收用户授权码
		String code = BasicUtil.getString("code");
		//微信地址可携带的返回参数，这里作为重定向地址的查询使用
		String state = request.getParameter("state");
		LOG.debug("获取到的code:"+code+"获取到的state:"+state);
		//获取授权成功后跳转的地址
		String url = BasicUtil.getString("url");
		//获取微信ID
		String weixinId = request.getParameter("weixinId");
		
		if(StringUtil.isBlank(code)) {
			model.addAttribute(Const.ERROR, "授权失败(code)");
			LOG.debug("授权失败(code)");
			return "redirect:"+Const.ERROR_500;	
		}
		
		if(StringUtil.isBlank(url)) {
			model.addAttribute(Const.ERROR, "授权成功后没有指定跳转地址(url)");
			LOG.debug("授权成功后没有指定跳转地址(url)");
			return "redirect:"+Const.ERROR_500;	
		}
		
		
		if(StringUtil.isBlank(weixinId)) {
			model.addAttribute(Const.ERROR, "没有指定微信编号(weixinId)");
			LOG.debug("没有指定微信编号(weixinId)");
			return "redirect:"+Const.ERROR_500;	
		}
		
		
		//查询微信详细信息
		WeixinEntity weixinEntity = weixinBiz.getEntityById(Integer.parseInt(weixinId));
		if(weixinEntity == null){
			LOG.error("未查询到对应授权的微信基础信息！");
			model.addAttribute(Const.ERROR, "未查询到对应授权的微信基础信息");
			//返回错误地址
			return "redirect:"+Const.ERROR_500;	
		}
		
		//获取微信用户信息
		WeixinOpenLoginUtil weixinOpenUtil = new WeixinOpenLoginUtil(weixinEntity.getWeixinAppID(),weixinEntity.getWeixinAppSecret());
		String userInfoJson = weixinOpenUtil.getUserInfo(code);
		WeixinPeopleEntity weixinPeopleEntity = WeixinPeopleEntityUtils.userInfoToWeixinPeople(userInfoJson, weixinEntity.getAppId(), weixinEntity.getWeixinId());
		if(weixinPeopleEntity ==  null){
			LOG.error("获取用户信息失败！"+userInfoJson);
			model.addAttribute(Const.ERROR, "获取用户信息失败！");
			//返回错误地址
			return "redirect:"+Const.ERROR_500;		
		}
		//检测系统中是否已经存在该用户
		WeixinPeopleEntity weixinPeople = weixinPeopleBiz.checkSoleWeixinPeople(weixinPeopleEntity);
		
		PeopleEntity people = (PeopleEntity)peopleBiz.getEntity(weixinPeople.getPeopleId());
		//将用户的openId压入session:weixn_people_session
		this.setPeopleBySession(request, people);
		//将微信实体压入session:weinxin_session
		this.setWeixinSession(request,com.mingsoft.weixin.constant.SessionConst.WEIXIN_SESSION,weixinEntity);
		return "redirect:"+url;			
	}
	
}
