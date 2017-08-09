package com.mingsoft.weixin.util;

import java.util.Map;

import com.mingsoft.util.MD5Util;
import com.mingsoft.util.StringUtil;

/**
 * 微信通用工具类
 * @author 铭飞开发团队
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2017年5月25日<br/>
 * 历史修订：<br/>
 */
public class WeixinUtil {

	/**
	 * 按照微信的需求生成支付key
	 * 
	 * @param params
	 *            参数
	 * @param key
	 *            商户交易key
	 * @return 支付key
	 */
	public static String getPaySign(Map<String, String> params, String key) {
		// 签名步骤一：按字典序排序参数
		Map<String, String> temp = StringUtil.sortMapByKey(params);
		// 签名步骤二：在string后加入KEY
		String sign = StringUtil.buildUrl("", temp).replace("?", "") + "&key=" + key;
		// 签名步骤三：MD5加密
		sign = MD5Util.MD5Encode(sign, "utf8");
		// 签名步骤四：所有字符转为大写
		sign = sign.toUpperCase();
		return sign;
	}
}
