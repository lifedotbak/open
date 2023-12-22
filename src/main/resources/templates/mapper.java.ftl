package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import ${package.Parent}.search.${entity}Search;

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
  
    List<${entity}> query(${entity}Search search);
    
    IPage<${entity}> queryPage(IPage<${entity}> page, ${entity}Search search);
  
}
