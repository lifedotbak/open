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


/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

	private final ${table.mapperName} ${table.mapperName?uncap_first};

	@Override
	public ${entity} get(String id){

     ${entity} result =	getById(id);

     log.info("result --> {}", result);

     return result;
	}

	@Override
	public void update(${entity} update){

		updateById(update);
	}

	@Override
	public void insert(${entity} insert){

		save(insert);
	}

	@Override
	public void delete(String id){

		removeById(id);
	}

	@Override
    public IPage<${entity}> listPage(IPage<${entity}> page, ${entity}Search search){

    page =  ${table.mapperName?uncap_first}.listPage(page,search);

    log.info("page --> {}", page);

    return page;
    }

    @Override
    public List<${entity}> list(${entity}Search search){

    List<${entity}> result = new ArrayList<${entity}>();

    result =  ${table.mapperName?uncap_first}.list(search);

    log.info("queryList --> {}", result);

    return result;
    }
}
