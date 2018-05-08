package com.monkeyframework.message.provider.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.monkeyframework.exception.monkeyException;
import com.monkeyframework.exception.ServiceException;
import com.monkeyframework.model.page.Pagination;
import com.monkeyframework.message.provider.dao.ThirdAuthMapper;
import com.monkeyframework.message.provider.dao.extend.ThirdAuthExtendMapper;
import com.monkeyframework.message.entity.ThirdAuth;
import com.monkeyframework.message.model.ThirdAuthSo;
import com.monkeyframework.message.model.ThirdAuthType;
import com.monkeyframework.message.service.PushTemplateService;
import com.monkeyframework.message.service.SmsTemplateService;
import com.monkeyframework.message.service.ThirdAuthService;

/**
 * @author xusm
 * 
 */
@Service("thirdAuthService")
public class ThirdAuthServiceImpl implements ThirdAuthService {
	
	@Resource
	private ThirdAuthMapper thirdAuthMapper;
	
	@Resource
	private ThirdAuthExtendMapper thirdAuthExtendMapper;
	
	@Resource
	private SmsTemplateService smsTemplateService;
	
	@Resource
	private PushTemplateService pushTemplateService;
	
	public ThirdAuth get(Long id) {
		return thirdAuthMapper.selectByPrimaryKey(id);
	}

	/**
	 * 新添加
	 * 
	 * @param log
	 * @return
	 */
	public Long save(ThirdAuth log) throws monkeyException {
		log.setCreateTime(new Date());
		log.setIsDelete(false);
		thirdAuthMapper.insert(log);
		return log.getId();
	}
	
	/**
	 * 修改
	 * 
	 * @param log
	 * @return
	 */
	public Long update(ThirdAuth log) throws monkeyException {
		log.setUpdateTime(new Date());
		thirdAuthMapper.updateByPrimaryKey(log);
		return log.getId();
	}
	
	/**
	 * 修改
	 * 
	 * @param log
	 * @return
	 */
	public Long update(Long id, ThirdAuth log) throws monkeyException {
		ThirdAuth po = this.get(id);
		String[] ignoreProperties = {"id", "createTime"};
		BeanUtils.copyProperties(log, po, ignoreProperties);
		this.update(po);
		return id;
	}

	/**
	 * 删除短信模板
	 * 
	 * @param role
	 * @throws monkeyException
	 */
	public Long delete(Long id) throws monkeyException {
		ThirdAuth auth = this.get(id);
		if(auth.getType() == ThirdAuthType.TYPE_SMS) {
			if(smsTemplateService.isExistByThirdAuthId(id)) {
				throw new ServiceException("", "有短信模板在使用“" + auth.getName() + "”的第三方帐号信息发送短信！");
			}
		}
		if(auth.getType() == ThirdAuthType.TYPE_PUSH) {
			if(pushTemplateService.isExistByThirdAuthId(id)) {
				throw new ServiceException("", "有短信模板在使用“" + auth.getName() + "”的第三方帐号信息发送短信！");
			}
		}
		auth.setIsDelete(true);
		return update(auth);
	}
	
	/**
	 * 获取所有短信模板
	 * 
	 * @throws monkeyException
	 */
	public List<ThirdAuth> findAll() throws monkeyException {
		return thirdAuthExtendMapper.selectByAll();
	}
	
	@Override
	public Pagination findByPage(Pagination page, ThirdAuthSo so) {
		List<ThirdAuth> list = thirdAuthExtendMapper.selectByPage(page, so);
		page.setList(list);
		return page;
	}
	
	@Override
	public List<ThirdAuth> findListByType(Integer type) {
		return thirdAuthExtendMapper.selectByType(type);
	}	
	
}
