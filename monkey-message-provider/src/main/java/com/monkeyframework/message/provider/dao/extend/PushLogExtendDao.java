package com.monkeyframework.message.provider.dao.extend;

import com.monkeyframework.model.page.Pagination;
import com.monkeyframework.message.model.PushLogBo;
import com.monkeyframework.message.model.PushLogSo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PushLogExtendDao {
    
    List<PushLogBo> findPage(@Param("page") Pagination page, @Param("so") PushLogSo so);
        
}