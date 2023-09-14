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

import com.spyker.framework.response.RestResponse;


/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    private final ${table.mapperName} ${table.mapperName?uncap_first};

    @Override
    public List<${entity}> query(${entity}Search search){
        List<${entity}> ${entity}List =  ${table.mapperName?uncap_first}.query(search);

        return ${entity}List;
    }

    @Override
    public IPage<${entity}> queryPage(IPage<${entity}> page, ${entity}Search search){
        page =  ${table.mapperName?uncap_first}.queryPage(page, search);

        return page;
    }

    @Override
    public ${entity} get(String id){
         ${entity} ${entity} = getById(id);

         return ${entity};
    }

    @Override
    public RestResponse<?> insert(${entity} ${entity}){
        save(${entity});

        return RestResponse.success(${entity});
    }

    @Override
    public RestResponse<?> update(${entity} ${entity}){
        updateById(${entity});

        return RestResponse.success();
    }

    @Override
    public RestResponse<?> delete(String id){
        removeById(id);

        return RestResponse.success();
    }

}