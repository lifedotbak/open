package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import com.spyker.framework.response.RestResponse;

import ${package.Parent}.search.${entity}Search;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    List<${entity}> query(${entity}Search search);

    IPage<${entity}> queryPage(IPage<${entity}> page, ${entity}Search search);

    ${entity} get(String id);

    RestResponse<?> insert(${entity} ${entity});

    RestResponse<?> update(${entity} ${entity});

    RestResponse<?> delete(String id);

}