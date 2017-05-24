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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mingsoft.weixin.biz.IWxOauthBiz;
import com.mingsoft.weixin.entity.WxOauthEntity;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;
import net.mingsoft.basic.util.BasicUtil;
import com.mingsoft.weixin.constant.e.OauthTypeEnum;
import net.mingsoft.basic.bean.ListBean;

import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import net.mingsoft.basic.bean.EUListBean;
	
/**
 * 微信网页2.0授权表管理控制层
 * @author 铭飞开发团队
 * @version 
 * 版本号：1.0.0<br/>
 * 创建日期：2017-5-24 22:47:12<br/>
 * 历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/weixin/wxOauth")
public class WxOauthAction extends com.mingsoft.weixin.action.BaseAction{
	
	/**
	 * 注入微信网页2.0授权表业务层
	 */	
	@Autowired
	private IWxOauthBiz wxOauthBiz;
	
	
	/**
	 * 查询微信网页2.0授权表列表
	 * @param wxOauth 微信网页2.0授权表实体
	 * <i>wxOauth参数包含字段信息参考：</i><br/>
	 * oauthWeixinId <br/>
	 * oauthId 子增长ID<br/>
	 * oauthAppId 关联微信应用ID<br/>
	 * oauthSuccessUrl 授权成功重定向地址<br/>
	 * oauthErrorUrl 授权失败者或错误重定向地址<br/>
	 * oauthDescription 授权描述<br/>
	 * oauthType 授权类型。1.弹出授权界面 2.不弹出授权界面<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>[<br/>
	 * { <br/>
	 * oauthWeixinId: <br/>
	 * oauthId: 子增长ID<br/>
	 * oauthAppId: 关联微信应用ID<br/>
	 * oauthSuccessUrl: 授权成功重定向地址<br/>
	 * oauthErrorUrl: 授权失败者或错误重定向地址<br/>
	 * oauthDescription: 授权描述<br/>
	 * oauthType: 授权类型。1.弹出授权界面 2.不弹出授权界面<br/>
	 * }<br/>
	 * ]</dd><br/>	 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute WxOauthEntity wxOauth,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		BasicUtil.startPage();
		List wxOauthList = wxOauthBiz.query(wxOauth);
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(new EUListBean(wxOauthList,(int)BasicUtil.endPage(wxOauthList).getTotal()),new DoubleValueFilter(),new DateValueFilter()));
	}
	/**
	 * 返回主界面index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return view ("/weixin/wx_oauth/index");
	}
	
	/**
	 * 返回编辑界面*_form
	 */
	@RequestMapping("/form")
	public String form(@ModelAttribute WxOauthEntity wxOauth,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(wxOauth.getOauthId() != null){
			BaseEntity wxOauthEntity = wxOauthBiz.getEntity(wxOauth.getOauthId());			//根据学校id查询
			model.addAttribute("wxOauthEntity",wxOauthEntity);
		}
		model.addAttribute("os", BasicUtil.enumToMap(OauthTypeEnum.values()));
		
		return view ("/weixin/wx_oauth/oauth_form");
	}
	
	/**
	 * 获取微信网页2.0授权表
	 * @param wxOauth 微信网页2.0授权表实体
	 * <i>wxOauth参数包含字段信息参考：</i><br/>
	 * oauthWeixinId <br/>
	 * oauthId 子增长ID<br/>
	 * oauthAppId 关联微信应用ID<br/>
	 * oauthSuccessUrl 授权成功重定向地址<br/>
	 * oauthErrorUrl 授权失败者或错误重定向地址<br/>
	 * oauthDescription 授权描述<br/>
	 * oauthType 授权类型。1.弹出授权界面 2.不弹出授权界面<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * oauthWeixinId: <br/>
	 * oauthId: 子增长ID<br/>
	 * oauthAppId: 关联微信应用ID<br/>
	 * oauthSuccessUrl: 授权成功重定向地址<br/>
	 * oauthErrorUrl: 授权失败者或错误重定向地址<br/>
	 * oauthDescription: 授权描述<br/>
	 * oauthType: 授权类型。1.弹出授权界面 2.不弹出授权界面<br/>
	 * }</dd><br/>
	 */
	@RequestMapping("/get")
	@ResponseBody
	public void get(@ModelAttribute WxOauthEntity wxOauth,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		if(wxOauth.getOauthId()<=0) {
			this.outJson(response, null, false, getResString("err.error", this.getResString("oauth.id")));
			return;
		}
		WxOauthEntity _wxOauth = (WxOauthEntity)wxOauthBiz.getEntity(wxOauth.getOauthId());
		this.outJson(response, _wxOauth);
	}
	
	/**
	 * 保存微信网页2.0授权表实体
	 * @param wxOauth 微信网页2.0授权表实体
	 * <i>wxOauth参数包含字段信息参考：</i><br/>
	 * oauthWeixinId <br/>
	 * oauthId 子增长ID<br/>
	 * oauthAppId 关联微信应用ID<br/>
	 * oauthSuccessUrl 授权成功重定向地址<br/>
	 * oauthErrorUrl 授权失败者或错误重定向地址<br/>
	 * oauthDescription 授权描述<br/>
	 * oauthType 授权类型。1.弹出授权界面 2.不弹出授权界面<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * oauthWeixinId: <br/>
	 * oauthId: 子增长ID<br/>
	 * oauthAppId: 关联微信应用ID<br/>
	 * oauthSuccessUrl: 授权成功重定向地址<br/>
	 * oauthErrorUrl: 授权失败者或错误重定向地址<br/>
	 * oauthDescription: 授权描述<br/>
	 * oauthType: 授权类型。1.弹出授权界面 2.不弹出授权界面<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/save")
	@ResponseBody
	public void save(@ModelAttribute WxOauthEntity wxOauth, HttpServletResponse response, HttpServletRequest request) {
		//验证授权成功重定向地址的值是否合法			
		if(StringUtil.isBlank(wxOauth.getOauthSuccessUrl())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.success.url")));
			return;			
		}
		if(!StringUtil.checkLength(wxOauth.getOauthSuccessUrl()+"", 1, 200)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.success.url"), "1", "200"));
			return;			
		}
		//验证授权失败者或错误重定向地址的值是否合法			
		if(StringUtil.isBlank(wxOauth.getOauthErrorUrl())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.error.url")));
			return;			
		}
		if(!StringUtil.checkLength(wxOauth.getOauthErrorUrl()+"", 1, 200)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.error.url"), "1", "200"));
			return;			
		}
		//验证授权描述的值是否合法			
		if(StringUtil.isBlank(wxOauth.getOauthDescription())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.description")));
			return;			
		}
		if(!StringUtil.checkLength(wxOauth.getOauthDescription()+"", 1, 50)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.description"), "1", "50"));
			return;			
		}
		//验证授权类型。1.弹出授权界面 2.不弹出授权界面的值是否合法			
		if(StringUtil.isBlank(wxOauth.getOauthType())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.type")));
			return;			
		}
		if(!StringUtil.checkLength(wxOauth.getOauthType()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.type"), "1", "10"));
			return;			
		}
		wxOauthBiz.saveEntity(wxOauth);
		this.outJson(response, true);
	}

	/**
	 * @param wxOauth 微信网页2.0授权表实体
	 * <i>wxOauth参数包含字段信息参考：</i><br/>
	 * oauthId:多个oauthId直接用逗号隔开,例如oauthId=1,2,3,4
	 * 批量删除微信网页2.0授权表
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            <dd>{code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }</dd>
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody List<WxOauthEntity> wxOauths,HttpServletResponse response, HttpServletRequest request) {
		int[] ids = new int[wxOauths.size()];
		for(int i = 0;i<wxOauths.size();i++){
			ids[i] = wxOauths.get(i).getOauthId();
		}
		wxOauthBiz.delete(ids);		
		this.outJson(response, true);
	}
	
	/** 
	 * 更新微信网页2.0授权表信息微信网页2.0授权表
	 * @param wxOauth 微信网页2.0授权表实体
	 * <i>wxOauth参数包含字段信息参考：</i><br/>
	 * oauthWeixinId <br/>
	 * oauthId 子增长ID<br/>
	 * oauthAppId 关联微信应用ID<br/>
	 * oauthSuccessUrl 授权成功重定向地址<br/>
	 * oauthErrorUrl 授权失败者或错误重定向地址<br/>
	 * oauthDescription 授权描述<br/>
	 * oauthType 授权类型。1.弹出授权界面 2.不弹出授权界面<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * oauthWeixinId: <br/>
	 * oauthId: 子增长ID<br/>
	 * oauthAppId: 关联微信应用ID<br/>
	 * oauthSuccessUrl: 授权成功重定向地址<br/>
	 * oauthErrorUrl: 授权失败者或错误重定向地址<br/>
	 * oauthDescription: 授权描述<br/>
	 * oauthType: 授权类型。1.弹出授权界面 2.不弹出授权界面<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/update")
	@ResponseBody	 
	public void update(@ModelAttribute WxOauthEntity wxOauth, HttpServletResponse response,
			HttpServletRequest request) {
		//验证授权成功重定向地址的值是否合法			
		if(StringUtil.isBlank(wxOauth.getOauthSuccessUrl())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.success.url")));
			return;			
		}
		if(!StringUtil.checkLength(wxOauth.getOauthSuccessUrl()+"", 1, 200)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.success.url"), "1", "200"));
			return;			
		}
		//验证授权失败者或错误重定向地址的值是否合法			
		if(StringUtil.isBlank(wxOauth.getOauthErrorUrl())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.error.url")));
			return;			
		}
		if(!StringUtil.checkLength(wxOauth.getOauthErrorUrl()+"", 1, 200)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.error.url"), "1", "200"));
			return;			
		}
		//验证授权描述的值是否合法			
		if(StringUtil.isBlank(wxOauth.getOauthDescription())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.description")));
			return;			
		}
		if(!StringUtil.checkLength(wxOauth.getOauthDescription()+"", 1, 50)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.description"), "1", "50"));
			return;			
		}
		//验证授权类型。1.弹出授权界面 2.不弹出授权界面的值是否合法			
		if(StringUtil.isBlank(wxOauth.getOauthType())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.type")));
			return;			
		}
		if(!StringUtil.checkLength(wxOauth.getOauthType()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.type"), "1", "10"));
			return;			
		}
		wxOauthBiz.updateEntity(wxOauth);
		this.outJson(response, true);
	}
	
		
}