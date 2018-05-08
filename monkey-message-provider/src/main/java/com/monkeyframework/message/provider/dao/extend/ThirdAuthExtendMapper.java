package com.monkeyframework.message.provider.dao.extend;

import com.monkeyframework.model.page.Pagination;
import com.monkeyframework.message.entity.ThirdAuth;
import com.monkeyframework.message.model.ThirdAuthSo;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdAuthExtendMapper {
	
	List<ThirdAuth> selectByAll();
	
	List<ThirdAuth> selectByPage(@Param("page") Pagination page, @Param("so") ThirdAuthSo so);
	
	List<ThirdAuth> selectByType(@Param("type") Integer type);
	
}