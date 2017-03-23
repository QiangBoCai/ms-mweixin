package com.mingsoft.mwebsite.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mchange.v1.util.ArrayUtils;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import com.mingsoft.basic.biz.IManagerBiz;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.biz.IRoleBiz;
import com.mingsoft.basic.biz.IRoleModelBiz;
import com.mingsoft.basic.constant.Const;
import com.mingsoft.basic.constant.ModelCode;
import com.mingsoft.basic.constant.e.CookieConstEnum;
import com.mingsoft.basic.constant.e.SessionConstEnum;
import com.mingsoft.basic.entity.ManagerEntity;
import com.mingsoft.basic.entity.ModelEntity;
import com.mingsoft.basic.entity.RoleEntity;
import com.mingsoft.basic.entity.RoleModelEntity;
import com.mingsoft.mwebsite.biz.IWebsiteBiz;
import com.mingsoft.mwebsite.entity.WebsiteEntity;
import com.mingsoft.parser.IParserRegexConstant;
import com.mingsoft.util.PageUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.bean.ListBean;
import net.mingsoft.basic.util.BasicUtil;

/**
 * 网站基本信息控制层
 * @author 史爱华
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2014-07-14<br/>
 * 历史修订：2017-3-20 bootstrap-table列表更改<br/>
 */
@Controller
@RequestMapping("/${managerPath}/website")
public class WebsiteAction extends BaseAction{
	
	/**
	 * appBiz业务层的注入
	 */
	@Autowired
	private IWebsiteBiz websiteBiz;
	
	/**
	 * managerBiz业务层的注入
	 */
	@Autowired
	private IManagerBiz managerBiz;
	
	/**
	 * 返回站点管理页面
	 * @param request
	 * @param mode
	 * @param response
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap mode,HttpServletResponse response){
		return view("/mwebsite/index");
	}
	
	/**
	 * 查询站点列表信息
	 * @param request 请求对象
	 * @param mode ModelMap实体对象
	 * @param response 响应对象
	 * @return 站点列表数据
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		BasicUtil.startPage();
		List<BaseEntity> websiteList = websiteBiz.query();
		EUListBean _list = new EUListBean(websiteList, (int) BasicUtil.endPage(websiteList).getTotal());
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(_list, new DoubleValueFilter(),new DateValueFilter("yyyy-MM-dd")));
	}
	
	/**
	 * 根据id删除站点信息
	 * @param basicId 要删除的站点Id
	 * @param request 请求对象
	 * @return 返回当前页数
	 */
	@RequestMapping("/{basicId}/delete")
	@ResponseBody
	public void delete(@PathVariable int basicId, HttpServletRequest request){
		WebsiteEntity website= (WebsiteEntity) websiteBiz.getEntity(basicId);
		/*
		 * 删除对应的站点管理员
		 */
		if(website!=null){
			int managerId = website.getWebsiteManagerId();
			managerBiz.deleteEntity(managerId);
			websiteBiz.deleteBasic(basicId);
		}
	}
	
	/**
	 * 批量id删除站点信息
	 * @param basicId 要删除的站点Id
	 * @param request 请求对象
	 * @return 返回当前页数
	 */
	@RequestMapping("/batchDelete")
	@ResponseBody
	public void batchDelete(HttpServletRequest request,HttpServletResponse response){
		String websiteIds = request.getParameter("websiteIds");
		if (StringUtil.isBlank(websiteIds) && StringUtil.isInteger(websiteIds.split(","))) {
			this.outJson(response, null, false);
		} else {
			int[] ids = StringUtil.stringsToInts(websiteIds.split(","));
			int appId = this.getAppId(request);
			Arrays.sort(ids);
			int postion = Arrays.binarySearch(ids, appId);
			if(postion>-1) {
				ids[postion] = 0;
			}
			websiteBiz.delete(ids);
			this.outJson(response, null, true);
		}
	}
	
	/**
	 * 跳转到站点保存页面
	 * @param request 请求对象
	 * @param mode ModelMap实体对象
	 * @return 站点保存页面
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request,ModelMap mode){
		//超级管理员识别
		mode.addAttribute("SystemManager",true);
		WebsiteEntity website = new WebsiteEntity();
		//传入一个空的app
		mode.addAttribute("website",website);
		return view("/mwebsite/website_form");
	}
	
	/**
	 * 跳转到修改页面
	 * @param mode ModelMap实体对象
	 * @param appId 站点id
	 * @param request 请求对象
	 * @return 站点修改页面
	 */
	@RequestMapping(value="/{websiteId}/edit")
	public String edit(ModelMap mode,@PathVariable int websiteId, HttpServletRequest request) {
		WebsiteEntity website = null;
		if (websiteId<0) {
			website = this.getWebsite(request);
		} else {
			website = (WebsiteEntity) websiteBiz.getEntity(websiteId);
		}

		//判断否是超级管理员,是的话不显示站点风格
		if(this.isSystemManager(request)){
			mode.addAttribute("SystemManager",true);
		}else{
			mode.addAttribute("SystemManager",false);
		}
		mode.addAttribute("website",website);
		return view("/mwebsite/website");
	}

	/**
	 * 更新站点信息
	 * @param mode ModelMap实体对象
	 * @param app	站点对象
	 * @param request 请求对象
	 * @param response 相应对象
	 */
	@RequestMapping("/update")
	public void update(ModelMap mode,@ModelAttribute WebsiteEntity website, HttpServletRequest request, HttpServletResponse response){
		mode.clear();
		// 获取Session值
		ManagerEntity managerSession = (ManagerEntity) getManagerBySession(request);
		if(managerSession==null){
			return ;
		}
		mode.addAttribute("managerSession",managerSession);
		//判断否是超级管理员,不是则不修改应用续费时间和清单
		if(!this.isSystemManager(request)){
			website.setWebsitePayDate(null);
			website.setWebsitePay(null);
			// 设置当前的站点id
			website.setWebsiteId(this.getAppId(request));
		}
		int managerRoleID = managerSession.getManagerRoleID();
		//判断站点数据的合法性
		// 获取cookie
		String cookie =this.getCookie(request, CookieConstEnum.PAGENO_COOKIE);
		int pageNo = this.getPageNo(request);
		mode.addAttribute("pageNo", pageNo);
		if(!checkForm(website,response)){
			return;
		}
		if(!StringUtil.isBlank(website.getWebsiteLogo())) {
			website.setWebsiteLogo( website.getWebsiteLogo().replace("|", ""));
		}
		//更新站点信息
		websiteBiz.updateEntity(website);
		this.outJson(response, ModelCode.APP, true, String.valueOf(pageNo), String.valueOf(managerRoleID));
	}
	
	/**
	 * 保存站点信息
	 * @param app 站点实体对象
	 * @param request 请求对象
	 * @param response 相应对象
	 */
	@RequestMapping("/save")
	public void save(@ModelAttribute WebsiteEntity website, HttpServletRequest request, HttpServletResponse response){
		//验证站点数据的合法性
		if(!checkForm(website,response)){
			return;
		}
		//问题:由于上传的图片路径后面可能带有｜符合。所以要进行将“｜”替换空
		//空值判断
		if(!StringUtil.isBlank(website.getWebsiteLogo())) {
			website.setWebsiteLogo( website.getWebsiteLogo().replace("|", ""));
		}
		//问题:去掉域名后面的"/"
		//String appUrl = website.getWebsiteHostUrl();
		//website.setWebsiteUrl(appUrl);
		websiteBiz.saveEntity(website);
		if(isSystemManager(request)) {
			String file = this.getRealPath(request,IParserRegexConstant.REGEX_SAVE_TEMPLATE+File.separator+ website.getWebsiteId());
			File fileName = new File(file);
	        fileName.mkdir();
		}
		this.outJson(response, ModelCode.APP, true,null);
	}
	
	/**
	 * 判断站点域名的合法性
	 * @param app 要验证的站点信息
	 * @param response response对象
	 */
	public boolean checkForm(WebsiteEntity website, HttpServletResponse response){
		/*
		 * 判断数据的合法性
		 */
		if(!(StringUtil.isBlank(website.getWebsiteKeyword())) && !StringUtil.checkLength(website.getWebsiteKeyword(), 0,1000)){
			this.outJson(response, ModelCode.APP, false,getResString("err.length",this.getResString("website.keyword",com.mingsoft.mwebsite.constant.Const.RESOURCES),"0","1000"));
			return false;
		}
		if(!(StringUtil.isBlank(website.getWebsiteCopyright())) && !StringUtil.checkLength(website.getWebsiteCopyright(), 0,1000)){
			this.outJson(response, ModelCode.APP, false,getResString("err.length",this.getResString("website.copyright",com.mingsoft.mwebsite.constant.Const.RESOURCES),"0","1000"));
			return false;
		}
		if(!(StringUtil.isBlank(website.getWebsiteDescription())) && !StringUtil.checkLength(website.getWebsiteDescription(), 0,1000)){
			this.outJson(response, ModelCode.APP, false,getResString("err.length",this.getResString("website.descrip",com.mingsoft.mwebsite.constant.Const.RESOURCES),"0","1000"));
			return false;
		}
		if(!StringUtil.checkLength(website.getWebsiteName(),1,50)){
			this.outJson(response, ModelCode.APP, false,getResString("err.length",this.getResString("website.title",com.mingsoft.mwebsite.constant.Const.RESOURCES),"1","50"));
			return false;
		}
		if(!(StringUtil.isBlank(website.getWebsiteStyle()))  && !StringUtil.checkLength(website.getWebsiteStyle(),1,30)){
			this.outJson(response, ModelCode.APP, false,getResString("err.length",this.getResString("website.style",com.mingsoft.mwebsite.constant.Const.RESOURCES),"1","30"));
			return false;
		}
		if(!StringUtil.checkLength(website.getWebsiteHostUrl(),10,150)){
			this.outJson(response, ModelCode.APP, false,getResString("err.length",this.getResString("website.url",com.mingsoft.mwebsite.constant.Const.RESOURCES),"10","150"));
			return false;
		}
		if(!(StringUtil.isBlank(website.getWebsitePay()))  &&  !StringUtil.checkLength(website.getWebsitePay(),0,500)){
			this.outJson(response, ModelCode.APP, false,getResString("err.length",this.getResString("website.pay",com.mingsoft.mwebsite.constant.Const.RESOURCES),"0","500"));
			return false;
		}
		return true;
	}
	
	/**
	 * 判断是否有重复的域名
	 * @param request 请求对象
	 * @return true:重复,false:不重复
	 */
	@RequestMapping("/checkUrl")
	@ResponseBody
	public boolean checkUrl(HttpServletRequest request){
		if(request.getParameter("websiteUrl")!=null){
			if(websiteBiz.countByUrl(request.getParameter("websiteUrl"))>0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	/**
	 * 安装初始化
	 */
	@RequestMapping("/init")
	public void init(HttpServletRequest request,HttpServletResponse response) {
		// 获取用户项目的访问路径(地址+端口+发布后的项目名)
		// 新增超级管理员
		IManagerBiz managerBiz = (IManagerBiz) this.getBean(request.getServletContext(), "managerBiz");
		// 新建超级管理员
		ManagerEntity manager = new ManagerEntity();
		// 管理员账号，默认是
		String _managerName = "adminms";
		// 用户可以传入账号，但账号必须唯一
		String managerName = request.getParameter("managerName");
		if (!StringUtil.isBlank(managerName)) {
			_managerName = managerName;
		}
		if (managerBiz.queryManagerByManagerName(_managerName) == null) {
			manager.setManagerName(_managerName);
			// 管理员昵称，默认是
			manager.setManagerNickName("adminms");
			// 管理员密码，默认是msopen
			manager.setManagerPassword("9d8622060de5f24937b60585c3f4d66b");
			// 管理员角色id
			manager.setManagerRoleID(1);
			manager.setManagerTime(new Date());
			managerBiz.saveEntity(manager);
		}

		manager = managerBiz.queryManagerByManagerName(_managerName);
		// 获取管理员id
		int managerId = manager.getManagerId();
		// 新增一个角色
		RoleEntity role = new RoleEntity();
		// 角色业务层
		IWebsiteBiz websiteBiz = (IWebsiteBiz) this.getBean(request.getServletContext(), "websiteBiz");

		// 角色业务层
		IRoleBiz roleBiz = (IRoleBiz) this.getBean(request.getServletContext(), "roleBiz");
		if (roleBiz.getEntity(1) == null) {
			role.setRoleId(1);
			role.setRoleManagerId(managerId);
			role.setRoleName("超级管理员");
			websiteBiz.saveRole(role);
		}

		// 插入角色与模块的关联关系
		List<RoleModelEntity> listRoleModel = new ArrayList<RoleModelEntity>();

		// 插入系统设置模块
		ModelEntity systemModel = new ModelEntity();
		systemModel.setModelCode("00000000");
		systemModel.setModelIcon("&#xe71f");
		systemModel.setModelTitle("系统设置");
		systemModel.setModelModelId(0);

		// 模块管理业务
		IModelBiz modelBiz = (IModelBiz) this.getBean(request.getServletContext(), "modelBiz");
		ModelEntity _model = modelBiz.getEntityByModelCode("00000000");
		int systemModelId = 0;
		// 判断应用设置模块是否已经存在，如果存在则不再进行保存
		if (_model == null) {
			modelBiz.saveEntity(systemModel);
			systemModelId = systemModel.getModelId();

			// 系统设置模块与角色的关系
			RoleModelEntity systemRoleModel = new RoleModelEntity();
			systemRoleModel.setModelId(systemModelId);
			systemRoleModel.setRoleId(1);
			listRoleModel.add(systemRoleModel);
		} else {
			systemModelId = _model.getModelId();
		}

		// 插入应用管理模块
		ModelEntity websiteModel = new ModelEntity();
		websiteModel.setModelIcon("");
		websiteModel.setModelCode("00000002");
		websiteModel.setModelModelId(systemModelId);
		websiteModel.setModelTitle("应用管理");
		websiteModel.setModelUrl("/website/list.do");
		int _websiteModleId = 0;
		// 根据模块编码获取应用管理的模块编码
		ModelEntity _websiteModel = modelBiz.getEntityByModelCode("00000002");
		// 判断应用管理模块是否已经存在如果不存在则进行保存，如果存在取已经存在的应用管理的模块id
		if (_websiteModel == null) {
			modelBiz.saveEntity(websiteModel);
			_websiteModleId = websiteModel.getModelId();

			// 应用管理模块与角色的关系
			RoleModelEntity websiteRoleModel = new RoleModelEntity();
			websiteRoleModel.setModelId(_websiteModleId);
			websiteRoleModel.setRoleId(1);
			listRoleModel.add(websiteRoleModel);

		}
		
		// 插入应用管理模块
		ModelEntity modelEntity = new ModelEntity();
		modelEntity.setModelIcon("");
		modelEntity.setModelCode("00000001");
		modelEntity.setModelModelId(systemModelId);
		modelEntity.setModelTitle("模块管理");
		modelEntity.setModelUrl("/model/list.do");
		int _modelId = 0;
		// 根据模块编码获取应用管理的模块编码
		ModelEntity _modelEntity = modelBiz.getEntityByModelCode("00000001");
		// 判断应用管理模块是否已经存在如果不存在则进行保存，如果存在取已经存在的应用管理的模块id
		if (_modelEntity == null) {
			modelBiz.saveEntity(modelEntity);
			_modelId = modelEntity.getModelId();

			// 应用管理模块与角色的关系
			RoleModelEntity modelRoleModel = new RoleModelEntity();
			modelRoleModel.setModelId(_modelId);
			modelRoleModel.setRoleId(1);
			listRoleModel.add(modelRoleModel);
		}
		

		if (listRoleModel.size() > 0) {
			// 模块管理业务
			IRoleModelBiz roleModelBiz = (IRoleModelBiz) this.getBean(request.getServletContext(), "roleModelBiz");
			roleModelBiz.saveEntity(listRoleModel);
			ModelEntity model = modelBiz.getEntityByModelCode("12050000");
			if (model != null) {
				model.setModelManagerId(0);
				modelBiz.updateEntity(model);
			}
			model = modelBiz.getEntityByModelCode("12020000");
			if (model != null) {
				model.setModelManagerId(0);
				modelBiz.updateEntity(model);
			}

		}
		this.outJson(response, "站群安装成功");

	}
}
