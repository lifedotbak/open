package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

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

    void insert(${entity} insert);

    void delete(String id);

    void update(${entity} update);

    ${entity} get(String id);

    IPage<${entity}> listPage(IPage<${entity}> page, ${entity}Search search);

    List<${entity}> list(${entity}Search search);

}
