package com.spyker.framework.mybatis.plus;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * 自动填充默认元对象处理器
 */
@Component
public class DefaultMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {

		this.setFieldValByName("createTime", new Date(), metaObject);
		this.setFieldValByName("modifyTime", new Date(), metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.setFieldValByName("modifyTime", new Date(), metaObject);
	}

}
