package com.mingsoft.weixin.action.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.entity.ListJson;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;
import com.mingsoft.weixin.action.BaseAction;
import com.mingsoft.weixin.biz.INewsBiz;
import com.mingsoft.weixin.entity.NewsEntity;

/**
 * 
 * Copyright: Copyright (c) 2014 - 2015
 * Company:景德镇铭飞科技有限公司
 * @author 刘继平
 * @version 300-001-001
 * 版权所有 铭飞科技
 * Comments: 微信素材控制层，继承BasicAction
 * Create Date:2014-9-25
 * Modification history:
 */
@Controller("webWeixin")
@RequestMapping("/weixin/news")
public class NewsAction extends BaseAction {

	/**
	 * 素材业务层注入
	 */
	@Autowired
	private INewsBiz newsBiz;
	
	
	/**
	 * 图文素材读取列表页面
	 */
	@RequestMapping("/{weixinId}/list.do")
	@ResponseBody
	public void list(@PathVariable int weixinId,HttpServletRequest request,HttpServletResponse response){
		int appId = this.getAppId(request);//应用编号
		String pageNo = request.getParameter("pageNo"); //分页
		String pageSize = request.getParameter("pageSize"); //分页数量
		String categoryId = request.getParameter("categoryId"); //分页数量
		String newsType = request.getParameter("newsType"); //分页数量
		if (!StringUtil.isInteger(pageNo)) {
			pageNo = "1";
		}
		int _pageSize = 10;
		if (StringUtil.isInteger(pageSize)) {
			_pageSize = Integer.parseInt(pageSize);
		}
		
		Integer _categoryId = 0;
		if (StringUtil.isInteger(categoryId)) {
			_categoryId = Integer.parseInt(categoryId);
		}
		
		Integer _newsType = 0;
		if (StringUtil.isInteger(newsType)) {
			_newsType = Integer.parseInt(newsType);
		}
		int count = newsBiz.queryCountNewsByAppIdAndWeixinIdAndType(appId, weixinId, _newsType);
		PageUtil page=new PageUtil(Integer.parseInt(pageNo),_pageSize,count,getUrl(request)+"/weixin/news/list.do");
		List<NewsEntity> list = newsBiz.queryNewsList(appId,weixinId, _newsType, _categoryId, page);
		ListJson  obj = new ListJson(count, list);
		this.outJson(response, JSONObject.toJSONString(obj));
	}

	
}
