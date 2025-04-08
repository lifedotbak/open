package ${package.Parent}.repository.redis;

import ${package.Entity}.${entity};

import org.springframework.data.repository.CrudRepository;

/**
* ${table.comment!}
*
*/
/** 一个负责存储和检索的组件，定义一个repository接口,和mapper功能类似，但不能放到mapper包下面，因为mybatis的扫描，放同一包下面会提示类冲突 */
public interface ${entity}Repository extends CrudRepository<${entity}, String> {}
