package ${package.Controller};
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujing.springbootscaffold.core.api.ApiController;
import com.xujing.springbootscaffold.core.api.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 * @author ${author}
 * @since ${date}
 */
@Api("${table.comment}-服务")
@RestController
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}s</#if>")
public class ${table.controllerName} extends ApiController{
    @Autowired
    private ${table.serviceName} ${table.entityPath}Service;

    @ApiOperation("创建单个${entity}")
    @PostMapping("")
    public Result<Boolean> create(@RequestBody ${entity} ${table.entityPath}) {
        return ${table.entityPath}Service.save(${table.entityPath}) ? success(true) : failed(false);
    }

    @ApiOperation("更新单个${entity}")
    @PutMapping("")
    public Result<Boolean> update(@RequestBody ${entity} ${table.entityPath}) {
        if (${table.entityPath}.getId() != null){
            LambdaQueryWrapper<${entity}> wrapper = new LambdaQueryWrapper<${entity}>();
            wrapper.eq(${entity}::getId, ${table.entityPath}.getId());
            return ${table.entityPath}Service.update(${table.entityPath},wrapper) ? success(true) : failed(false);
        }
        return failed("user id is null.");
    }

    @ApiOperation("删除单个${entity}")
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return ${table.entityPath}Service.removeById(id) ? success(true) : failed(false);
    }

    @ApiOperation("获取单个${entity}")
    @GetMapping("/{id}")
    public Result<${entity}> get(@PathVariable(value = "id") Long id) {
        return success(${table.entityPath}Service.getById(id));
    }

    @ApiOperation("获取列表${entity}")
    @GetMapping("")
    public Result<Page<${entity}>> list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,@ModelAttribute ${entity} ${table.entityPath}) {
        Page<${entity}> list = ${table.entityPath}Service.getBaseMapper().selectPage(
            new Page<>(pageNo,pageSize), Wrappers.<${entity}>lambdaQuery()
        );
        return success(list);
    }
}