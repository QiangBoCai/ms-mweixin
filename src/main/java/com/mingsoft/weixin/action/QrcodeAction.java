package com.mingsoft.weixin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mingsoft.weixin.constant.e.QrcodeTypeEnum;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mingsoft.weixin.biz.IQrcodeBiz;
import com.mingsoft.weixin.entity.QrcodeEntity;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;
import com.mingsoft.base.entity.BaseEntity;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.bean.ListBean;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import net.mingsoft.basic.bean.EUListBean;
	
/**
 * 微信带参数的二维码管理控制层
 * @author 铭飞开发团队
 * @version 
 * 版本号：1.0.0<br/>
 * 创建日期：2017-5-26 16:57:29<br/>
 * 历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/weixin/qrcode")
public class QrcodeAction extends com.mingsoft.weixin.action.BaseAction{
	
	/**
	 * 注入微信带参数的二维码业务层
	 */	
	@Autowired
	private IQrcodeBiz qrcodeBiz;
	
	
	/**
	 * 查询微信带参数的二维码列表
	 * @param qrcode 微信带参数的二维码实体
	 * <i>qrcode参数包含字段信息参考：</i><br/>
	 * qrcodeId 自增长ID<br/>
	 * qrcodeTitle 二维码名称<br/>
	 * qrcodeValue 二维码的场景值<br/>
	 * qrcodeType 二维码类型(1.永久二维码，2.临时二维码)<br/>
	 * qrcodeAppId 二维码所属应用ID<br/>
	 * qrcodeDescription 二维码描述<br/>
	 * qrcodeTime 二维码生成时间<br/>
	 * qrcodeExpireTime 二维码到期时间，最大为1800，以秒为单位(只有临时二维码才有该值)<br/>
	 * qrcodeWeixinId 微信编号<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>[<br/>
	 * { <br/>
	 * qrcodeId: 自增长ID<br/>
	 * qrcodeTitle: 二维码名称<br/>
	 * qrcodeValue: 二维码的场景值<br/>
	 * qrcodeType: 二维码类型(1.永久二维码，2.临时二维码)<br/>
	 * qrcodeAppId: 二维码所属应用ID<br/>
	 * qrcodeDescription: 二维码描述<br/>
	 * qrcodeTime: 二维码生成时间<br/>
	 * qrcodeExpireTime: 二维码到期时间，最大为1800，以秒为单位(只有临时二维码才有该值)<br/>
	 * qrcodeWeixinId: 微信编号<br/>
	 * }<br/>
	 * ]</dd><br/>	 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute QrcodeEntity qrcode,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		BasicUtil.startPage();
		List qrcodeList = qrcodeBiz.query(qrcode);
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(new EUListBean(qrcodeList,(int)BasicUtil.endPage(qrcodeList).getTotal()),new DoubleValueFilter(),new DateValueFilter()));
	}
	
	/**
	 * 返回主界面index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return view ("/weixin/qrcode/index");
	}
	
	/**
	 * 返回编辑界面qrcode_form
	 */
	@RequestMapping("/form")
	public String form(@ModelAttribute QrcodeEntity qrcode,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(qrcode.getQrcodeId() != null){
			BaseEntity qrcodeEntity = qrcodeBiz.getEntity(qrcode.getQrcodeId());			
			model.addAttribute("qrcodeEntity",qrcodeEntity);
		}
		model.addAttribute("os", BasicUtil.enumToMap(QrcodeTypeEnum.values()));
		return view ("/weixin/qrcode/qrcode_form");
	}
	
	/**
	 * 获取微信带参数的二维码
	 * @param qrcode 微信带参数的二维码实体
	 * <i>qrcode参数包含字段信息参考：</i><br/>
	 * qrcodeId 自增长ID<br/>
	 * qrcodeTitle 二维码名称<br/>
	 * qrcodeValue 二维码的场景值<br/>
	 * qrcodeType 二维码类型(1.永久二维码，2.临时二维码)<br/>
	 * qrcodeAppId 二维码所属应用ID<br/>
	 * qrcodeDescription 二维码描述<br/>
	 * qrcodeTime 二维码生成时间<br/>
	 * qrcodeExpireTime 二维码到期时间，最大为1800，以秒为单位(只有临时二维码才有该值)<br/>
	 * qrcodeWeixinId 微信编号<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * qrcodeId: 自增长ID<br/>
	 * qrcodeTitle: 二维码名称<br/>
	 * qrcodeValue: 二维码的场景值<br/>
	 * qrcodeType: 二维码类型(1.永久二维码，2.临时二维码)<br/>
	 * qrcodeAppId: 二维码所属应用ID<br/>
	 * qrcodeDescription: 二维码描述<br/>
	 * qrcodeTime: 二维码生成时间<br/>
	 * qrcodeExpireTime: 二维码到期时间，最大为1800，以秒为单位(只有临时二维码才有该值)<br/>
	 * qrcodeWeixinId: 微信编号<br/>
	 * }</dd><br/>
	 */
	@RequestMapping("/get")
	@ResponseBody
	public void get(@ModelAttribute QrcodeEntity qrcode,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		if(qrcode.getQrcodeId()<=0) {
			this.outJson(response, null, false, getResString("err.error", this.getResString("qrcode.id")));
			return;
		}
		QrcodeEntity _qrcode = (QrcodeEntity)qrcodeBiz.getEntity(qrcode.getQrcodeId());
		this.outJson(response, _qrcode);
	}
	
	/**
	 * 保存微信带参数的二维码实体
	 * @param qrcode 微信带参数的二维码实体
	 * <i>qrcode参数包含字段信息参考：</i><br/>
	 * qrcodeId 自增长ID<br/>
	 * qrcodeTitle 二维码名称<br/>
	 * qrcodeValue 二维码的场景值<br/>
	 * qrcodeType 二维码类型(1.永久二维码，2.临时二维码)<br/>
	 * qrcodeAppId 二维码所属应用ID<br/>
	 * qrcodeDescription 二维码描述<br/>
	 * qrcodeTime 二维码生成时间<br/>
	 * qrcodeExpireTime 二维码到期时间，最大为1800，以秒为单位(只有临时二维码才有该值)<br/>
	 * qrcodeWeixinId 微信编号<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * qrcodeId: 自增长ID<br/>
	 * qrcodeTitle: 二维码名称<br/>
	 * qrcodeValue: 二维码的场景值<br/>
	 * qrcodeType: 二维码类型(1.永久二维码，2.临时二维码)<br/>
	 * qrcodeAppId: 二维码所属应用ID<br/>
	 * qrcodeDescription: 二维码描述<br/>
	 * qrcodeTime: 二维码生成时间<br/>
	 * qrcodeExpireTime: 二维码到期时间，最大为1800，以秒为单位(只有临时二维码才有该值)<br/>
	 * qrcodeWeixinId: 微信编号<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/save")
	@ResponseBody
	public void save(@ModelAttribute QrcodeEntity qrcode, HttpServletResponse response, HttpServletRequest request) {
		//验证二维码名称的值是否合法			
		if(StringUtil.isBlank(qrcode.getQrcodeTitle())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("qrcode.title")));
			return;			
		}
		if(!StringUtil.checkLength(qrcode.getQrcodeTitle()+"", 1, 50)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("qrcode.title"), "1", "50"));
			return;			
		}
		//验证二维码的场景值的值是否合法			
		if(StringUtil.isBlank(qrcode.getQrcodeValue())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("qrcode.value")));
			return;			
		}
		if(!StringUtil.checkLength(qrcode.getQrcodeValue()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("qrcode.value"), "1", "10"));
			return;			
		}
		//验证二维码类型(1.永久二维码，2.临时二维码)的值是否合法			
		if(StringUtil.isBlank(qrcode.getQrcodeType())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("qrcode.type")));
			return;			
		}
		if(!StringUtil.checkLength(qrcode.getQrcodeType()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("qrcode.type"), "1", "10"));
			return;			
		}
		//验证二维码所属应用ID的值是否合法			
		if(StringUtil.isBlank(qrcode.getQrcodeAppId())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("qrcode.app.id")));
			return;			
		}
		if(!StringUtil.checkLength(qrcode.getQrcodeAppId()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("qrcode.app.id"), "1", "10"));
			return;			
		}
		//验证二维码描述的值是否合法			
		if(StringUtil.isBlank(qrcode.getQrcodeDescription())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("qrcode.description")));
			return;			
		}
		if(!StringUtil.checkLength(qrcode.getQrcodeDescription()+"", 1, 200)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("qrcode.description"), "1", "200"));
			return;			
		}
		//验证二维码到期时间，最大为1800，以秒为单位(只有临时二维码才有该值)的值是否合法			
		if(StringUtil.isBlank(qrcode.getQrcodeExpireTime())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("qrcode.expire.time")));
			return;			
		}
		if(!StringUtil.checkLength(qrcode.getQrcodeExpireTime()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("qrcode.expire.time"), "1", "10"));
			return;			
		}
		qrcodeBiz.saveEntity(qrcode);
		this.outJson(response, true);
	}

	/**
	 * @param qrcode 微信带参数的二维码实体
	 * <i>qrcode参数包含字段信息参考：</i><br/>
	 * qrcodeId:多个qrcodeId直接用逗号隔开,例如qrcodeId=1,2,3,4
	 * 批量删除微信带参数的二维码
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            <dd>{code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }</dd>
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody List<QrcodeEntity> qrcodes,HttpServletResponse response, HttpServletRequest request) {
		int[] ids = new int[qrcodes.size()];
		for(int i = 0;i<qrcodes.size();i++){
			ids[i] = qrcodes.get(i).getQrcodeId();
		}
		qrcodeBiz.delete(ids);		
		this.outJson(response, true);
	}
	
	/** 
	 * 更新微信带参数的二维码信息微信带参数的二维码
	 * @param qrcode 微信带参数的二维码实体
	 * <i>qrcode参数包含字段信息参考：</i><br/>
	 * qrcodeId 自增长ID<br/>
	 * qrcodeTitle 二维码名称<br/>
	 * qrcodeValue 二维码的场景值<br/>
	 * qrcodeType 二维码类型(1.永久二维码，2.临时二维码)<br/>
	 * qrcodeAppId 二维码所属应用ID<br/>
	 * qrcodeDescription 二维码描述<br/>
	 * qrcodeTime 二维码生成时间<br/>
	 * qrcodeExpireTime 二维码到期时间，最大为1800，以秒为单位(只有临时二维码才有该值)<br/>
	 * qrcodeWeixinId 微信编号<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * qrcodeId: 自增长ID<br/>
	 * qrcodeTitle: 二维码名称<br/>
	 * qrcodeValue: 二维码的场景值<br/>
	 * qrcodeType: 二维码类型(1.永久二维码，2.临时二维码)<br/>
	 * qrcodeAppId: 二维码所属应用ID<br/>
	 * qrcodeDescription: 二维码描述<br/>
	 * qrcodeTime: 二维码生成时间<br/>
	 * qrcodeExpireTime: 二维码到期时间，最大为1800，以秒为单位(只有临时二维码才有该值)<br/>
	 * qrcodeWeixinId: 微信编号<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/update")
	@ResponseBody	 
	public void update(@ModelAttribute QrcodeEntity qrcode, HttpServletResponse response,
			HttpServletRequest request) {
		//验证二维码名称的值是否合法			
		if(StringUtil.isBlank(qrcode.getQrcodeTitle())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("qrcode.title")));
			return;			
		}
		if(!StringUtil.checkLength(qrcode.getQrcodeTitle()+"", 1, 50)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("qrcode.title"), "1", "50"));
			return;			
		}
		//验证二维码的场景值的值是否合法			
		if(StringUtil.isBlank(qrcode.getQrcodeValue())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("qrcode.value")));
			return;			
		}
		if(!StringUtil.checkLength(qrcode.getQrcodeValue()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("qrcode.value"), "1", "10"));
			return;			
		}
		//验证二维码类型(1.永久二维码，2.临时二维码)的值是否合法			
		if(StringUtil.isBlank(qrcode.getQrcodeType())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("qrcode.type")));
			return;			
		}
		if(!StringUtil.checkLength(qrcode.getQrcodeType()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("qrcode.type"), "1", "10"));
			return;			
		}
		//验证二维码所属应用ID的值是否合法			
		if(StringUtil.isBlank(qrcode.getQrcodeAppId())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("qrcode.app.id")));
			return;			
		}
		if(!StringUtil.checkLength(qrcode.getQrcodeAppId()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("qrcode.app.id"), "1", "10"));
			return;			
		}
		//验证二维码描述的值是否合法			
		if(StringUtil.isBlank(qrcode.getQrcodeDescription())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("qrcode.description")));
			return;			
		}
		if(!StringUtil.checkLength(qrcode.getQrcodeDescription()+"", 1, 200)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("qrcode.description"), "1", "200"));
			return;			
		}
		
		//验证二维码到期时间，最大为1800，以秒为单位(只有临时二维码才有该值)的值是否合法			
		if(StringUtil.isBlank(qrcode.getQrcodeExpireTime())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("qrcode.expire.time")));
			return;			
		}
		if(!StringUtil.checkLength(qrcode.getQrcodeExpireTime()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("qrcode.expire.time"), "1", "10"));
			return;			
		}
		qrcodeBiz.updateEntity(qrcode);
		this.outJson(response, true);
	}
	
		
}