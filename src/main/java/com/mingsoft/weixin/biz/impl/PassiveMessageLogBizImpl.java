
package com.mingsoft.weixin.biz.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.weixin.biz.IPassiveMessageLogBiz;
import com.mingsoft.weixin.dao.IPassiveMessageLogDao;

/**
 * 
 * 被动消息回复日志业务层，实现接口:IPassiveMessageBiz
 * @author 成卫雄(qq:330216230)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年8月26日 上午8:47:39<br/>
 * 历史修订：<br/>
 */
@Service("passiveMessageLogBiz")
public class PassiveMessageLogBizImpl extends BaseBizImpl implements IPassiveMessageLogBiz{

	/**
	 * 被动消息回复日志持久化层
	 */
	@Autowired
	private IPassiveMessageLogDao passiveMessageLogDao;
	
	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return this.passiveMessageLogDao;
	}

	/**
	 * 根据用户ID,事件ID,应用ID</br>
	 * 查询该用户在当前事件下响应消息的条数</br>
	 * @param weixinId 微信Id
	 * @param peopleId 用户ID
	 * @param eventId 事件ID
	 * @param appId 应用ID
	 * @return 日志条数
	 */
	public int getCountBySendMessage(int weixinId,int peopleId,int eventId,int appId,String key){
		return this.passiveMessageLogDao.getCountByCustom(appId,weixinId,peopleId,key,eventId);
	}	
	
}
