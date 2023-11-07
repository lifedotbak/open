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
        List<${entity}> result =  ${table.mapperName?uncap_first}.query(search);

        return result;
    }

    @Override
    public IPage<${entity}> queryPage(IPage<${entity}> page, ${entity}Search search){
        page =  ${table.mapperName?uncap_first}.queryPage(page, search);

        return page;
    }

    @Override
    public ${entity} get(String id){
         ${entity} result = getById(id);
         return result;
    }

    @Override
    public boolean insert(${entity} ${entity}){
        return  save(${entity});
    }

    @Override
    public boolean update(${entity} ${entity}){
       return  updateById(${entity});
    }

    @Override
    public boolean delete(String id){
        return    removeById(id);
    }

}