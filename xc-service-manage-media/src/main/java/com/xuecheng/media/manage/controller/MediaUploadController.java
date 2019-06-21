package com.xuecheng.media.manage.controller;

import com.xuecheng.api.media.MediaUploadControllerApi;
import com.xuecheng.framework.domain.media.response.CheckChunkResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.media.manage.service.MediaUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 98050
 * @Time: 2019-06-21 16:17
 * @Feature:
 */
@RestController
public class MediaUploadController implements MediaUploadControllerApi {

    private final MediaUploadService mediaUploadService;

    @Autowired
    public MediaUploadController(MediaUploadService mediaUploadService) {
        this.mediaUploadService = mediaUploadService;
    }

    @Override
    public ResponseResult register(@RequestParam("fileMd5") String fileMd5,
                            @RequestParam("fileName") String fileName,
                            @RequestParam("fileSize") Long fileSize,
                            @RequestParam("mimetype") String mimetype,
                            @RequestParam("fileExt") String fileExt){
        return this.mediaUploadService.register(fileMd5, fileName, fileSize, mimetype, fileExt);
    }

    @Override
    public CheckChunkResult checkChunk(@RequestParam("fileMd5") String fileMd5,
                                       @RequestParam("chunk") Integer chunk,
                                       @RequestParam("chunkSize") Integer chunkSize){
        return this.mediaUploadService.checkChunk(fileMd5, chunk, chunkSize);
    }

    @Override
    public ResponseResult uploadChunk(@RequestParam("file") MultipartFile file,
                                      @RequestParam("chunk") Integer chunk,
                                      @RequestParam("fileMd5") String fileMd5){
        return this.mediaUploadService.uploadChunk(file, chunk, fileMd5);
    }

    @Override
    public ResponseResult mergeChunk(@RequestParam("fileMd5") String fileMd5,
                                     @RequestParam("fileName") String fileName,
                                     @RequestParam("fileSize") Long fileSize,
                                     @RequestParam("mimetype") String mimetype,
                                     @RequestParam("fileExt") String fileExt){
        return this.mediaUploadService.mergeChunk(fileMd5, fileName, fileSize, mimetype, fileExt);
    }
}
