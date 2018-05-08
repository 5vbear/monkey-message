package com.monkeyframework.message.provider.dao.extend;

import com.monkeyframework.model.page.Pagination;
import com.monkeyframework.message.entity.PushTemplate;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PushTemplateExtendMapper {
	
	List<PushTemplate> selectByPage(@Param("page") Pagination page, @Param("keyword") String keyword);
	
	PushTemplate selectByGroupAndCode(@Param("group") String group, @Param("code") String code);

}