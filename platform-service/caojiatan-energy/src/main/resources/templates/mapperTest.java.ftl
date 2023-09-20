package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import ${package.Mapper}.${table.mapperName};
import org.springframework.beans.factory.annotation.Autowired;
import com.spyker.application.BaseTest;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
*/
@Slf4j
@RequiredArgsConstructor
public class ${table.mapperName} extends BaseTest {

  private final ${table.mapperName} ${table.mapperName?uncap_first};

}
