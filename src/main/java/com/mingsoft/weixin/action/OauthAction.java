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
import com.mingsoft.weixin.constant.e.OauthTypeEnum;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mingsoft.weixin.biz.IOauthBiz;
import com.mingsoft.weixin.entity.OauthEntity;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;
import com.mingsoft.base.entity.BaseEntity;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.bean.ListBean;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import net.mingsoft.basic.bean.EUListBean;
	
/**
 * 微信网页2.0授权表管理控制层
 * @author 铭飞开发团队
 * @version 
 * 版本号：1.0.0<br/>
 * 创建日期：2017-5-25 22:04:59<br/>
 * 历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/weixin/oauth")
public class OauthAction extends com.mingsoft.weixin.action.BaseAction{
	
	/**
	 * 注入微信网页2.0授权表业务层
	 */	
	@Autowired
	private IOauthBiz oauthBiz;
	
	
	/**
	 * 查询微信网页2.0授权表列表
	 * @param oauth 微信网页2.0授权表实体
	 * <i>oauth参数包含字段信息参考：</i><br/>
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
	public void list(@ModelAttribute OauthEntity oauth,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		BasicUtil.startPage();
		List oauthList = oauthBiz.query(oauth);
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(new EUListBean(oauthList,(int)BasicUtil.endPage(oauthList).getTotal()),new DoubleValueFilter(),new DateValueFilter()));
	}
	
	/**
	 * 返回主界面index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return view ("/weixin/oauth/index");
	}
	
	/**
	 * 返回编辑界面*_form
	 */
	@RequestMapping("/form")
	public String form(@ModelAttribute OauthEntity oauth,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(oauth.getOauthId() != null){
			BaseEntity oauthEntity = oauthBiz.getEntity(oauth.getOauthId());			//根据Oauthid查询实体
			model.addAttribute("oauthEntity",oauthEntity);
		}
		model.addAttribute("os", BasicUtil.enumToMap(OauthTypeEnum.values()));
		
		return view ("/weixin/oauth/oauth_form");
	}
	/**
	 * 获取微信网页2.0授权表
	 * @param oauth 微信网页2.0授权表实体
	 * <i>oauth参数包含字段信息参考：</i><br/>
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
	public void get(@ModelAttribute OauthEntity oauth,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		if(oauth.getOauthId()<=0) {
			this.outJson(response, null, false, getResString("err.error", this.getResString("oauth.id")));
			return;
		}
		OauthEntity _oauth = (OauthEntity)oauthBiz.getEntity(oauth.getOauthId());
		this.outJson(response, _oauth);
	}
	
	/**
	 * 保存微信网页2.0授权表实体
	 * @param oauth 微信网页2.0授权表实体
	 * <i>oauth参数包含字段信息参考：</i><br/>
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
	public void save(@ModelAttribute OauthEntity oauth, HttpServletResponse response, HttpServletRequest request) {
		//验证授权成功重定向地址的值是否合法			
		if(StringUtil.isBlank(oauth.getOauthSuccessUrl())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.success.url")));
			return;			
		}
		if(!StringUtil.checkLength(oauth.getOauthSuccessUrl()+"", 1, 200)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.success.url"), "1", "200"));
			return;			
		}
		//验证授权失败者或错误重定向地址的值是否合法			
		if(StringUtil.isBlank(oauth.getOauthErrorUrl())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.error.url")));
			return;			
		}
		if(!StringUtil.checkLength(oauth.getOauthErrorUrl()+"", 1, 200)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.error.url"), "1", "200"));
			return;			
		}
		//验证授权描述的值是否合法			
		if(StringUtil.isBlank(oauth.getOauthDescription())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.description")));
			return;			
		}
		if(!StringUtil.checkLength(oauth.getOauthDescription()+"", 1, 50)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.description"), "1", "50"));
			return;			
		}
		//验证授权类型。1.弹出授权界面 2.不弹出授权界面的值是否合法			
		if(StringUtil.isBlank(oauth.getOauthType())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.type")));
			return;			
		}
		if(!StringUtil.checkLength(oauth.getOauthType()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.type"), "1", "10"));
			return;			
		}
		oauthBiz.saveEntity(oauth);
		this.outJson(response, true);
	}

	/**
	 * @param oauth 微信网页2.0授权表实体
	 * <i>oauth参数包含字段信息参考：</i><br/>
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
	public void delete(@RequestBody List<OauthEntity> oauths,HttpServletResponse response, HttpServletRequest request) {
		int[] ids = new int[oauths.size()];
		for(int i = 0;i<oauths.size();i++){
			ids[i] = oauths.get(i).getOauthId();
		}
		oauthBiz.delete(ids);		
		this.outJson(response, true);
	}
	
	/** 
	 * 更新微信网页2.0授权表信息微信网页2.0授权表
	 * @param oauth 微信网页2.0授权表实体
	 * <i>oauth参数包含字段信息参考：</i><br/>
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
	public void update(@ModelAttribute OauthEntity oauth, HttpServletResponse response,
			HttpServletRequest request) {
		//验证授权成功重定向地址的值是否合法			
		if(StringUtil.isBlank(oauth.getOauthSuccessUrl())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.success.url")));
			return;			
		}
		if(!StringUtil.checkLength(oauth.getOauthSuccessUrl()+"", 1, 200)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.success.url"), "1", "200"));
			return;			
		}
		//验证授权失败者或错误重定向地址的值是否合法			
		if(StringUtil.isBlank(oauth.getOauthErrorUrl())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.error.url")));
			return;			
		}
		if(!StringUtil.checkLength(oauth.getOauthErrorUrl()+"", 1, 200)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.error.url"), "1", "200"));
			return;			
		}
		//验证授权描述的值是否合法			
		if(StringUtil.isBlank(oauth.getOauthDescription())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.description")));
			return;			
		}
		if(!StringUtil.checkLength(oauth.getOauthDescription()+"", 1, 50)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.description"), "1", "50"));
			return;			
		}
		//验证授权类型。1.弹出授权界面 2.不弹出授权界面的值是否合法			
		if(StringUtil.isBlank(oauth.getOauthType())){
			this.outJson(response, null,false,getResString("err.empty", this.getResString("oauth.type")));
			return;			
		}
		if(!StringUtil.checkLength(oauth.getOauthType()+"", 1, 10)){
			this.outJson(response, null, false, getResString("err.length", this.getResString("oauth.type"), "1", "10"));
			return;			
		}
		oauthBiz.updateEntity(oauth);
		this.outJson(response, true);
	}
	
		
}