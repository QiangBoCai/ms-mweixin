package com.mingsoft.weixin.action.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.mingsoft.order.biz.IOrderBiz;
import net.mingsoft.order.constant.e.OrderPaymentEnum;
import net.mingsoft.order.constant.e.OrderStatusEnum;
import net.mingsoft.order.entity.OrderEntity;
import com.mingsoft.util.JsonUtil;
import com.mingsoft.util.StringUtil;
import com.mingsoft.weixin.action.BaseAction;
import com.mingsoft.weixin.bean.WeixinPayOrderBean;
import com.mingsoft.weixin.biz.IWeixinBiz;
import com.mingsoft.weixin.constant.e.WeixinPayEnum;
import com.mingsoft.weixin.entity.WeixinEntity;
import com.mingsoft.weixin.entity.WeixinPeopleEntity;
import com.mingsoft.weixin.util.OauthUtils;
import com.mingsoft.weixin.util.WeixinPayUtils;
import com.mingsoft.weixin.util.XmlUtils;
import com.mingsoft.weixin.util.bean.WeixinPeopleEntityUtils;

/**
 * 铭飞科技
 * Copyright: Copyright (c) 2014 - 2015
 * @author 王天培 QQ:78750478
 * Comments:微信支付
 * Create Date:2015-1-19
 * Modification history:
 */
@Controller
@RequestMapping("/weixin/pay")
public class WeixinPayAction extends BaseAction {

	/**
	 * 参数名称</br>
	 * 前端传入订单ID的</br>
	 */
	private static final String ORDER_ID="orderId";
	
	/**
	 * 参数名称</br>
	 * 前端传入微信Id的</br>
	 */
	private static final String WEIXIN_ID = "weixinId";
	

	/**
	 * 注入微信业务层
	 */
	@Autowired
	private IWeixinBiz weixinBiz;
	
	/**
	 * 注入订单业务层
	 */
	@Autowired
	private IOrderBiz orderBiz;
	
	
	/**
	 * 手机端提交支付。先进入pay方法组织参数</br>
	 * 跳转授权登录,调用微信JS-API必须携带微信openId</br>
	 * 必须配合订单模块使用</br>
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/pay")
	public String pay(HttpServletRequest request, HttpServletResponse response)  {
		String orderId = request.getParameter(ORDER_ID);//获取订单ID
		String weixinId = request.getParameter(WEIXIN_ID);//微信Id
		//判断接受参数
		if(!StringUtil.isInteger(orderId) || !StringUtil.isInteger(weixinId)){
			return "redirect:"+request.getContextPath()+"/500/error.do";
		}
		//获取微信信息
		WeixinEntity weixin = this.weixinBiz.getEntityById(Integer.parseInt(weixinId));
		
		Map<String,Object> map = this.assemblyRequestMap(request);
		map.put(ORDER_ID,orderId);//订单Id
		map.put(WEIXIN_ID,weixinId);//微信Id
//		map.put(R_KEY,rKey);
		//将需要传递的参数转换成json格式，进行加密后传递
		String state = this.encryptByAES(request,JsonUtil.getObjectToJsonObject(map));
		return "redirect:" + OauthUtils.getCodeUrl(weixin.getWeixinAppID(),weixin.getWeixinOauthUrl()+"/weixin/pay/gateway.do",true,state);
	}

	/**
	 * 微信统一下单支付,提交参数组织</br>
	 * 必须配合订单模块使用</br>
	 * 请求参数:</br>
	 * 		orderId:订单号</br>
	 * 		notifyUrl:接收微信支付异步通知回调地址</br>
	 * 		weixinId:调用微支付的微信ID
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/gateway")
	public void gateway(HttpServletRequest request, HttpServletResponse response){
		String state = request.getParameter("state");//获取重定向参数
		String code = request.getParameter("code"); //用户同意授权就可以获得
		if(StringUtil.isBlank(state) || StringUtil.isBlank(code)){
			//返回错误地址
			this.outJson(response, null, false);
			return ;
		}
		
		//将参数进行解密，该参数约定为json数据键:weixinId,orderId
		state = this.decryptByAES(request, state);
		//将json转换为map
		Map<String,Object> stateMap = JsonUtil.getMap4Json(state);
		int weixinId = Integer.parseInt(stateMap.get(WEIXIN_ID).toString());//微信Id
		int orderId = Integer.parseInt(stateMap.get(ORDER_ID).toString());//订单Id
		String rKey = "";//stateMap.get(R_KEY).toString();//重定向模版的key值
		
		//获取微信信息
		WeixinEntity weixin = this.weixinBiz.getEntityById(weixinId);
		
		//获取用户openid;调用微信JS支付接口必须要用户openId
		OauthUtils au = new OauthUtils(weixin.getWeixinAppID(),weixin.getWeixinAppSecret());
		Map<String,Object> userMap = au.getUser(code);
		//将获取到的微信用户详情转换为用户实体信息
		WeixinPeopleEntity weixinPeopleEntity = WeixinPeopleEntityUtils.userInfoToWeixinPeople(userMap,weixin.getAppId(),weixin.getWeixinId());
		WeixinPayUtils weixinPayUtils = new WeixinPayUtils(weixin.getWeixinAppID(), weixin.getWeixinAppSecret());
		
		//获取订单需要支付的订单信息
		OrderEntity order = (OrderEntity) this.orderBiz.getEntity(orderId);
		
		//该订单需要支付的价格(注订单价格不能有小数)
		int priceStr = (int) (order.getOrderPrice()*100);
		
		WeixinPayOrderBean wpb = new WeixinPayOrderBean();
		wpb.setNonce(StringUtil.randomNumber(32));//生成32为随机数
		wpb.setTimeStart(Long.toString(System.currentTimeMillis()));//订单生成时间
		
		wpb.setAppId(weixin.getWeixinAppID()); //微信应用ID
		wpb.setMchId(weixin.getWeixinPayMchId()); //商户号
		wpb.setBody(order.getOrderDescription());//订单描述
		wpb.setOutTradeNo(order.getOrderNo()); //商户订单号
		wpb.setTotalFee(priceStr); //支付价格
		wpb.setSpbillCreateIp(this.getHostIp()); //终端IP(必须)
		wpb.setNotifyUrl(weixin.getWeixinOauthUrl()+"/weixin/pay/notify.do"); //接收微信支付成功通知(必须)
		wpb.setTradeType(WeixinPayEnum.JSAPI.toString()); //支付方式类型
		wpb.setOpenId(weixinPeopleEntity.getWeixinPeopleOpenId());
		//加载请求参数的xml
		String xml = XmlUtils.buildXmlPayUnifiedOrder(wpb,weixin.getWeixinPayKey());
		//调用统一下单接口
		Map<String,Object> mapUnifiedorder = weixinPayUtils.unifiedorder(xml);
		LOG.debug("return mapUnifiedorder" + mapUnifiedorder);
		
		/*-----------拼装JS调用微信api的必须参数-----------*/		
		// 读取模版内容
		String content = "";//this.generaterPage(rKey,cmsParser,request);
		//替换模版中的自定义参数
		content = content.replace("{appId/}", weixin.getWeixinAppID());//微信appId
		content = content.replace("{nonceStr/}",wpb.getNonce());//随机字符串
		//订单详情扩展字符串,统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
		content = content.replace("{package/}",mapUnifiedorder.get("prepay_id").toString());
		content = content.replace("{timeStamp/}",wpb.getTimeStart());//时间戳
		//生成签名采用MD5加密
		Map<String, String> mapPay = new HashMap<String, String>();
		mapPay.put("timeStamp", wpb.getTimeStart());
		mapPay.put("signType", "MD5");
		mapPay.put("appId", weixin.getWeixinAppID());
		mapPay.put("nonceStr",wpb.getNonce());		
		mapPay.put("package","prepay_id=" + mapUnifiedorder.get("prepay_id"));
		String sign = this.getPaySign(mapPay, weixin.getWeixinPayKey());
		content = content.replace("{sign/}",sign);
		
		//映射前端界面
		this.outString(response, content);	
	}	
	
	/**
	 * 支付回调</br>
	 * 必须配合订单模块使用</br>
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/notify")
	@ResponseBody
	public void notify(HttpServletRequest request, HttpServletResponse response) {
		//接受支付回调参数
		Map<String,Object> param = XmlUtils.buildXmlPayNotify(this.readStreamParameter(request));
		//int appId = this.getAppId(request);
		if (param!=null) {//判断回调参数
			//判断是否支付成功
			String returnCode = param.get("return_code").toString();
			LOG.debug("支付结果"+returnCode);
			//判断是否是存在的订单，如果不存在且有是支付成功的那么咋订单表里面增加一条记录
			if (!StringUtil.isBlank(returnCode) && returnCode.equalsIgnoreCase("SUCCESS")) {//真正支付成功
				String orderNo = param.get("out_trade_no").toString();//支付成功后返回的订单号
//				String orderPrice = param.get("total_fee").toString();//支付金额
				//根据订单ID查询订单信息
				OrderEntity order = orderBiz.getByOrderNo(orderNo);
				if (order!=null) {
					if(order.getOrderStatus()==OrderStatusEnum.UNPAY.toInt()) { 	//判断存在的订单是否已经支付成功，如果没有支付成功就进行支付操作
						order.setOrderStatus(OrderStatusEnum.PAY);
						order.setOrderPayment(OrderPaymentEnum.WEIXIN);
						orderBiz.updateEntity(order);
					}			
				}
			}
		}
	}	
	
	/**
	 * 自定义支付,系统不处理订单</br>
	 * 手机端提交支付。先进入pay方法组织参数</br>
	 * 跳转授权登录,调用微信JS-API必须携带微信openId</br>
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/customPay")	
	public String payCustom(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> map = this.assemblyRequestMap(request);
		Object weixinId = map.get(WEIXIN_ID);//微信Id
		Object rKey = null;//map.get(R_KEY);//起调支付自定义页面key
		//判断接受参数
		if(!StringUtil.isInteger(weixinId) || StringUtil.isBlank(rKey)){
			return "redirect:"+request.getContextPath()+"/500/error.do";
		}
		//获取微信信息
		WeixinEntity weixin = this.weixinBiz.getEntityById(Integer.parseInt(weixinId.toString()));
		
		//将需要传递的参数转换成json格式，进行加密后传递
		String state = this.encryptByAES(request,JsonUtil.getObjectToJsonObject(map));
		return "redirect:" + OauthUtils.getCodeUrl(weixin.getWeixinAppID(),weixin.getWeixinOauthUrl()+"/weixin/pay/customGateway.do",true,state);		
	}
	
	/**
	 * 自定义,系统不直接处理订单</br>
	 * 微信统一下单支付,提交参数组织</br>
	 * 必须配合订单模块使用</br>
	 * 请求参数:</br>
	 * 		notifyUrl:接收微信支付异步通知回调地址</br>
	 * 		weixinId:调用微支付的微信ID</br>
	 * 		orderDescription:订单描述</br>
	 * 		orderPrice：订单支付价格</br>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/customGateway")
	public void customGateway(HttpServletRequest request, HttpServletResponse response){
		String state = request.getParameter("state");//获取重定向参数
		String code = request.getParameter("code"); //用户同意授权就可以获得
		if(StringUtil.isBlank(state) || StringUtil.isBlank(code)){
			//返回错误地址
			this.outJson(response, null, false);
			return ;
		}
		
		//将参数进行解密，该参数约定为json数据键:weixinId,orderId
		state = this.decryptByAES(request, state);
		//将json转换为map
		Map<String,Object> stateMap = JsonUtil.getMap4Json(state);
		int weixinId = Integer.parseInt(stateMap.get(WEIXIN_ID).toString());//微信Id
		String rKey = "";//stateMap.get(R_KEY).toString();//重定向模版的key值
		String orderDescription = stateMap.get("orderDescription").toString();//获取订单描述
		String orderPrice = stateMap.get("orderPrice").toString();//获取订单价格
		
		//获取微信信息
		WeixinEntity weixin = this.weixinBiz.getEntityById(weixinId);
		
		//获取用户openid;调用微信JS支付接口必须要用户openId
		OauthUtils au = new OauthUtils(weixin.getWeixinAppID(),weixin.getWeixinAppSecret());
		Map<String,Object> userMap = au.getUser(code);
		//将获取到的微信用户详情转换为用户实体信息
		WeixinPeopleEntity weixinPeopleEntity = WeixinPeopleEntityUtils.userInfoToWeixinPeople(userMap,weixin.getAppId(),weixin.getWeixinId());
		WeixinPayUtils weixinPayUtils = new WeixinPayUtils(weixin.getWeixinAppID(), weixin.getWeixinAppSecret());
		
		//该订单需要支付的价格(注订单价格不能有小数)
		int priceStr = (int) (Double.parseDouble(orderPrice)*100);
		String orderNo = StringUtil.getDateSimpleStr();//生成订单号
		
		WeixinPayOrderBean wpb = new WeixinPayOrderBean();
		wpb.setNonce(StringUtil.randomNumber(32));//生成32为随机数
		wpb.setTimeStart(Long.toString(System.currentTimeMillis()));//订单生成时间
		
		wpb.setAppId(weixin.getWeixinAppID()); //微信应用ID
		wpb.setMchId(weixin.getWeixinPayMchId()); //商户号
		wpb.setBody(orderDescription);//订单描述
		wpb.setOutTradeNo(orderNo); //商户订单号
		wpb.setTotalFee(priceStr); //支付价格
		wpb.setSpbillCreateIp(this.getHostIp()); //终端IP(必须)
		wpb.setNotifyUrl(weixin.getWeixinOauthUrl()+"/weixin/pay/notify.do"); //接收微信支付成功通知(必须)
		wpb.setTradeType(WeixinPayEnum.JSAPI.toString()); //支付方式类型
		wpb.setOpenId(weixinPeopleEntity.getWeixinPeopleOpenId());
		//加载请求参数的xml
		String xml = XmlUtils.buildXmlPayUnifiedOrder(wpb,weixin.getWeixinPayKey());
		//调用统一下单接口
		Map<String,Object> mapUnifiedorder = weixinPayUtils.unifiedorder(xml);
		LOG.debug("return mapUnifiedorder" + mapUnifiedorder);
		
		/*-----------拼装JS调用微信api的必须参数-----------*/		
		// 读取模版内容
		String content = "";//this.generaterPage(rKey,cmsParser,request);
		//替换模版中的自定义参数
		content = content.replace("{appId/}", weixin.getWeixinAppID());//微信appId
		content = content.replace("{nonceStr/}",wpb.getNonce());//随机字符串
		//订单详情扩展字符串,统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
		content = content.replace("{package/}",mapUnifiedorder.get("prepay_id").toString());
		content = content.replace("{timeStamp/}",wpb.getTimeStart());//时间戳
		//生成签名采用MD5加密
		Map<String, String> mapPay = new HashMap<String, String>();
		mapPay.put("timeStamp", wpb.getTimeStart());
		mapPay.put("signType", "MD5");
		mapPay.put("appId", weixin.getWeixinAppID());
		mapPay.put("nonceStr",wpb.getNonce());		
		mapPay.put("package","prepay_id=" + mapUnifiedorder.get("prepay_id"));
		String sign = this.getPaySign(mapPay, weixin.getWeixinPayKey());
		content = content.replace("{sign/}",sign);
		
		//映射前端界面
		this.outString(response, content);	
	}	
	
	
}
