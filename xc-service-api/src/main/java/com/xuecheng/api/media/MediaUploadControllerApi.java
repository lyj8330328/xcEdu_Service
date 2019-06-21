package com.xuecheng.api.media;

import com.xuecheng.framework.domain.media.response.CheckChunkResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 98050
 * @Time: 2019-06-19 22:36
 * @Feature:
 */
@RequestMapping("/media/upload")
@Api(value = "媒体资源管理接口",description = "媒体资源管理接口，提供文件上传、文件管理接口")
public interface MediaUploadControllerApi {

    /**
     * 文件上传注册
     * @param fileMd5 文件md5
     * @param fileName 文件名称
     * @param fileSize 文件大小
     * @param mimetype 文件类型
     * @param fileExt 文件扩展名
     * @return
     */
    @ApiOperation("文件上传注册")
    @PostMapping("/register")
    ResponseResult register(@RequestParam("fileMd5") String fileMd5,
                            @RequestParam("fileName") String fileName,
                            @RequestParam("fileSize") Long fileSize,
                            @RequestParam("mimetype") String mimetype,
                            @RequestParam("fileExt") String fileExt);

    /**
     * 分块检查
     * @param fileMd5 文件md5
     * @param chunk 分块编号
     * @param chunkSize 分块大小
     * @return
     */
    @ApiOperation("分块检查")
    @PostMapping("/checkChunk")
    CheckChunkResult checkChunk(@RequestParam("fileMd5") String fileMd5,
                                @RequestParam("chunk") Integer chunk,
                                @RequestParam("chunkSize") Integer chunkSize);

    /**
     * 分块上传
     * @param file 分块文件
     * @param chunk 分块编号
     * @param fileMd5 文件md5
     * @return
     */
    @ApiOperation("上传分块")
    @PostMapping("/uploadChunk")
    ResponseResult uploadChunk(@RequestParam("file") MultipartFile file,
                               @RequestParam("chunk") Integer chunk,
                               @RequestParam("fileMd5") String fileMd5);

    /**
     * 分块合并
     * @param fileMd5 文件md5
     * @param fileName 文件名称
     * @param fileSize 文件大小
     * @param mimetype 文件类型
     * @param fileExt 文件扩展名
     * @return
     */
    @ApiOperation("合并分块")
    @PostMapping("mergeChunk")
    ResponseResult mergeChunk(@RequestParam("fileMd5") String fileMd5,
                              @RequestParam("fileName") String fileName,
                              @RequestParam("fileSize") Long fileSize,
                              @RequestParam("mimetype") String mimetype,
                              @RequestParam("fileExt") String fileExt);
}
