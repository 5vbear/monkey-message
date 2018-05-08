package com.monkeyframework.message.provider.service.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.monkeyframework.exception.monkeyException;
import com.monkeyframework.exception.ServiceException;
import com.monkeyframework.model.page.Pagination;
import com.monkeyframework.message.provider.dao.SmsTemplateMapper;
import com.monkeyframework.message.provider.dao.extend.SmsTemplateExtendMapper;
import com.monkeyframework.message.entity.SmsTemplate;
import com.monkeyframework.message.service.SmsTemplateService;

import freemarker.template.Template;

/**
 * @author xusm
 * 
 */
@Service("smsTemplateService")
public class SmsTemplateServiceImpl implements SmsTemplateService {
	
	private static final Log logger = LogFactory.getLog(SmsTemplateServiceImpl.class);

	@Resource
	private SmsTemplateMapper smsTemplateMapper;
	
	@Resource
	private SmsTemplateExtendMapper smsTemplateExtendMapper;
	
	public SmsTemplate get(Long id) {
		return smsTemplateMapper.selectByPrimaryKey(id);
	}

	/**
	 * 新添加
	 * 
	 * @param log
	 * @return
	 */
	public void save(SmsTemplate log) throws monkeyException {
		String code = log.getTmpCode();
		String group = log.getTmpGroup();
		if(this.isExistByGroupAndCode(group, code)) {
			throw new ServiceException("", "有相同的存在");
		}
		log.setIsDelete(false);
		log.setCreateTime(new Date());
		smsTemplateMapper.insert(log);
	}
	
	/**
	 * 修改
	 * 
	 * @param log
	 * @return
	 */
	public void update(SmsTemplate log) throws monkeyException {
		log.setUpdateTime(new Date());
		smsTemplateMapper.updateByPrimaryKey(log);
	}
	
	/**
	 * 修改
	 * 
	 * @param log
	 * @return
	 */
	public void update(Long id, SmsTemplate log) throws monkeyException {
		String code = log.getTmpCode();
		String group = log.getTmpGroup();
		SmsTemplate existTemp = this.getByGroupAndCode(group, code);
		if(null != existTemp && !id.equals(existTemp.getId())) {
			throw new ServiceException("", "有相同的存在");
		}
		SmsTemplate po = this.get(id);
		String[] ignoreProperties = {"id", "createTime", "isDelete"};
		BeanUtils.copyProperties(log, po, ignoreProperties);
		this.update(po);
	}

	/**
	 * 删除短信模板
	 * 
	 * @param role
	 * @throws monkeyException
	 */
	public void delete(Long id) throws monkeyException {
		SmsTemplate temp = this.get(id);
		temp.setIsDelete(true);
		this.update(temp);
	}
	
	/**
	 * 获取所有短信模板
	 * 
	 * @throws monkeyException
	 */
	/*public List<SmsTemplate> findAll() throws monkeyException {
		SmsTemplateExample example = new SmsTemplateExample();
		example.createCriteria();
		return smsTemplateMapper.selectByExample(example);
	}*/
	
	public boolean isExistByThirdAuthId(Long  thirdAuthId) {
		/*SmsTemplateExample example = new SmsTemplateExample();
		example.createCriteria().andThirdAuthIdEqualTo(thirdAuthId);
		int count = smsTemplateMapper.countByExample(example);
		if(count > 0)
			return true;
		else*/
			return false;
	}
	
	public SmsTemplate getByGroupAndCode(String group, String code) throws monkeyException {
		return smsTemplateExtendMapper.selectByGroupAndCode(group, code);
	}
	
	public boolean isExistByGroupAndCode(String group, String code) throws monkeyException {
		SmsTemplate temp = this.getByGroupAndCode(group, code);
		if(null == temp)
			return false;
		else
			return true;
	}
	
	public String buildContent(String group, String code, Map<String, String> data) {
		try {
			SmsTemplate smsTemplate = this.getByGroupAndCode(group, code);
            Template template = new Template(null, new StringReader(smsTemplate.getTemplate()), null); 
			StringWriter out = new StringWriter();
			template.process(data, out);
			return out.getBuffer().toString();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public String buildContent(SmsTemplate smsTemplate, Map<String, String> data) {
		try {
            Template template = new Template(null, new StringReader(smsTemplate.getTemplate()), null); 
			StringWriter out = new StringWriter();
			template.process(data, out);
			return out.getBuffer().toString();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}


	@Override
	public Pagination findByPage(Pagination page, String keyword) {
		List<SmsTemplate> list = smsTemplateExtendMapper.selectByPage(page,keyword);
		page.setList(list);
		return page;
	}

	@Override
	public void deletes(List<Long> idList) throws monkeyException {
		for (Long id : idList) {
			this.delete(id);
		}
	}
	
}
