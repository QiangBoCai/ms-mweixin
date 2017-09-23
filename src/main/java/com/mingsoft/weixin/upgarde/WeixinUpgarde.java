package com.mingsoft.weixin.upgarde;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mingsoft.base.entity.ResultJson;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.biz.IRoleModelBiz;
import com.mingsoft.basic.entity.ModelEntity;
import com.mingsoft.basic.entity.RoleModelEntity;
import com.mingsoft.util.AESUtil;
import com.mingsoft.util.StringUtil;
import com.mingsoft.weixin.action.BaseAction;

import net.mingsoft.base.util.PropertiesUtil;
import net.mingsoft.base.util.SpringUtil;

public class WeixinUpgarde  extends BaseAction {
	/**
	 * 微信升级包
	 */
	private static final long serialVersionUID = 7575749647205573895L;
	
	public ResultJson setup() {
		ResultJson result = new ResultJson();
		
		//检查当前系统是拥有代码
	   if(!this.checkModel("com.mingsoft.weixin.action.MenuAction")) {
			result.setResult(false);
			result.setResultMsg("请先使用源码或Maven方式加载模块到系统！");
			return result;
		}

	   //获取当前模块版本号
	    String version = this.getVersion("com/mingsoft/weixin","05000000");
	    if(version == null) {
	    	result.setResult(false);
			result.setResultMsg("当前系统模块版本号异常！");
			return result;	
	    }
	    
	    //业务处理代码
	    IModelBiz modelBiz = (IModelBiz) SpringUtil.getBean(IModelBiz.class);
	    IRoleModelBiz roleModelBiz = (IRoleModelBiz) SpringUtil.getBean(IRoleModelBiz.class);
	    ModelEntity model = modelBiz.getEntityByModelCode("05000000");
	    ModelEntity modelChild = new ModelEntity();
	    if(model == null){
	    	model = new ModelEntity();
			model.setModelTitle("微信");
			model.setModelCode("05000000");
			model.setModelIcon("&#xe834;");
			model.setModelManagerId(0);
			model.setModelIsMenu(1);
			model.setModelDatetime(new Timestamp(System.currentTimeMillis()));
			modelBiz.saveEntity(model);
			//子菜单的安装
			modelChild.setModelModelId(model.getModelId());
			modelChild.setModelTitle("公众号管理");
			modelChild.setModelCode("05010000");
			modelChild.setModelUrl("/weixin/index.do");
			modelChild.setModelManagerId(0);
			modelChild.setModelIsMenu(1);
			modelChild.setModelParentIds(model.getModelId()+"");
			modelChild.setModelDatetime(new Timestamp(System.currentTimeMillis()));
			modelBiz.saveEntity(modelChild);
			//角色的权限
			List list = new ArrayList();
			HttpServletRequest request = SpringUtil.getRequest();
			RoleModelEntity roleModel = new RoleModelEntity();
			roleModel.setModelId(model.getModelId());
			roleModel.setRoleId(this.getManagerBySession(request).getManagerRoleID());
			list.add(roleModel);
			RoleModelEntity roleModel2 = new RoleModelEntity();
			roleModel2.setModelId(modelChild.getModelId());
			roleModel2.setRoleId(this.getManagerBySession(request).getManagerRoleID());
			list.add(roleModel2);
			roleModelBiz.saveEntity(list);
			result.setResultMsg("安装成功");
	    }else{
	    	result.setResult(false);
			result.setResultMsg("当前系统已安装该插件！");
			return result;	
	    }
		return result;
	}
	
	
	private boolean checkModel(String className) {
		try {
			Class cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private String getVersion(String pak,String modelCode) {
		try {
			String value = PropertiesUtil.get(pak+"/resources/resources_zh_CN.properties", "version");
			value = AESUtil.decrypt(value, StringUtil.Md5(modelCode).substring(16));
			if(value==null) {
				return null;
			}
			return value;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}