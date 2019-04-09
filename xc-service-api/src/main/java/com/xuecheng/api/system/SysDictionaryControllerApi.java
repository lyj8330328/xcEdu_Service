package com.xuecheng.api.system;

import com.xuecheng.framework.domain.system.SysDictionary;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 98050
 * @Time: 2019-04-08 16:21
 * @Feature:
 */
@Api(value = "数据字典接口",description = "提供数据字典接口的管理、查询功能")
@RequestMapping("sys")
public interface SysDictionaryControllerApi {

    /**
     * 数据字典查询接口
     * @param type
     * @return
     */
    @ApiOperation(value = "数据字典查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type",value = "类型",required = true,paramType = "path",dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 10000,message = "操作成功"),
            @ApiResponse(code = 11111,message = "操作失败")
    })
    @GetMapping("dictionary/get/{type}")
    SysDictionary getByType(@PathVariable("type") String type);
}
