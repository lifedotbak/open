package ${package.ServiceImpl};


import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import java.util.ArrayList;
import ${package.Parent}.search.${entity}Search;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
*
* ${table.comment!} 服务实现类
*/
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "${entity}")
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

// @formatter:off

	private final ${table.mapperName} ${table.mapperName?uncap_first};

	@Override
	public List<${entity}> query(${entity}Search search){

		List<${entity}> result =  ${table.mapperName?uncap_first}.query(search);
		log.info("result------>{}", result);

		return result;
	}

	@Override
	public IPage<${entity}> queryPage(IPage<${entity}> page, ${entity}Search search){

		page =  ${table.mapperName?uncap_first}.queryPage(page, search);
		log.info("page------>{}", page);

		return page;
	}

	/**
	* @param id
	* @return
	* @Cacheable
	* @Cacheble注解表示这个方法有了缓存的功能，方法的返回值会被缓存下来，下一次调用该方法前， 会去检查是否缓存中已经有值
	* ，如果有就直接返回，不调用方法。如果没有，就调用方法，然后把结果缓存起来。这个注解一般用在查询方法上。
	*/
	@Override
	//@Cacheable(key = "#id")
	public ${entity} get(String id){
		${entity} result = getById(id);

		log.info("result------>{}", result);

		return result;
	}


	/**
	* @param ${entity?uncap_first}
	* @return
	* @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来，
	* 供其它地方使用。它通常用在新增方法上。
	*/
	@Override
	//@CachePut(key = "#${entity?uncap_first}.id")
	public ${entity} insert(${entity} ${entity?uncap_first}){
		save(${entity?uncap_first});
		return ${entity?uncap_first};
	}

	/**
	* @param ${entity?uncap_first}
	* @return
	* @CachePut 加了@CachePut注解的方法，会把方法的返回值put到缓存里面缓存起来，
	* 供其它地方使用。它通常用在新增方法上。
	*/
	@Override
	//@CachePut(key = "#${entity?uncap_first}.id")
	public ${entity} update(${entity} ${entity?uncap_first}){
		updateById(${entity?uncap_first});
		return ${entity?uncap_first};
	}

	/**
	* @param id
	* @return
	* @CacheEvict 使用了CacheEvict注解的方法，会清空指定缓存。
	* 一般用在更新或者删除的方法上。
	*/
	@Override
	//@CacheEvict(key = "#id")
	public boolean delete(String id){
		return removeById(id);
	}

}
