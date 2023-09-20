package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import ${package.Parent}.search.${entity}Search;

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

    List<${entity}> list(${entity}Search search);

    IPage<${entity}> listPage(IPage<${entity}> page,@Param("search") ${entity}Search search);

}
