package com.mingsoft.weixin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingsoft.basic.servlet.BaseServlet;

/**
 * mswx-铭飞微信酒店预订平台
 * Copyright: Copyright (c) 2013 - 2015
 * Company:景德镇铭飞科技有限公司
 * @author 荣繁奎
 * @version 100-000-000
 * 版权所有
 * Comments:微信扫描回复,显示二维码的活动地址
 * Create Date:2014-3-14
 * Modification history:暂无
 */
@SuppressWarnings("serial")
public class QrcodeActiveShowServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String hotelId = req.getParameter("hotelId");
		String actionNum = req.getParameter("action");
		String openId = req.getParameter("openid");
		logger.debug("openid:"+openId);
		req.getRequestDispatcher("/hotel/qrcode/qrcode_active_show.ftl").forward(req, resp);
	}

	
}
