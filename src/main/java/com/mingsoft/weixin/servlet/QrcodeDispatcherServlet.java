package com.mingsoft.weixin.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mingsoft.weixin.constant.Const;
import com.mingsoft.basic.servlet.BaseServlet;
import com.mingsoft.util.StringUtil;
/**
 * mswx-铭飞微信酒店预订平台
 * Copyright: Copyright (c) 2013 - 2015
 * Company:景德镇铭飞科技有限公司
 * @author 荣繁奎
 * @version 100-000-000
 * 版权所有
 * Comments:显示微信二维码的大图标
 * Create Date:2014-3-14
 * Modification history:暂无
 */
@WebServlet(urlPatterns="/weixin/qrcodeDispatcher")
public class QrcodeDispatcherServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String picture = req.getParameter("picture");
		String openId = req.getParameter("openid");
		logger.debug("openid:"+openId);
		if(!StringUtil.isBlank(picture)){
			//图片地址绝对路径
			String absolutyPath = com.mingsoft.base.constant.Const.PROJECT_PATH + Const.ORDER_PATH;
			//物理路径
			String path = com.mingsoft.base.constant.Const.HOST_URL + Const.ORDER_PATH;
			File file = new File(absolutyPath+picture);
			if(file.exists()){
				req.setAttribute("pictureUrl", path + picture );
//路径需要修改				
				req.getRequestDispatcher("/hotel/qrcode/qrcode_show.ftl").forward(req, resp);
			}else{
				this.sendHtml(req, resp,getResString("weixin.qrcode.no.error"));
			}
		}else{
			this.sendHtml(req, resp,getResString("weixin.qrcode.error"));
		}
	}

	
}
