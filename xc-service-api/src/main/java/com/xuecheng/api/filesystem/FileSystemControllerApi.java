package com.xuecheng.api.filesystem;

import com.xuecheng.framework.domain.filesystem.response.UploadFileResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 98050
 * @Time: 2019-04-17 17:27
 * @Feature:
 */
@RequestMapping("/filesystem")
@Api(value = "文件系统管理接口",description = "文件系统管理接口",tags = {"文件系统管理接口"})
public interface FileSystemControllerApi {

    /**
     * 上传文件
     * @param multipartFile 文件
     * @param filetag 文件标签
     * @param businesskey 业务key
     * @param metadata 元信息，json格式
     * @return
     */
    @ApiOperation("上传文件")
    UploadFileResult upload(MultipartFile multipartFile,String filetag,String businesskey,String metadata);
}
