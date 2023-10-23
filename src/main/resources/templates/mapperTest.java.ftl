package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import ${package.Mapper}.${table.mapperName};
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.base.BaseTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
public class ${table.mapperName}Test extends BaseTest {

    @Autowired
    private ${table.mapperName} ${table.mapperName?uncap_first};

}