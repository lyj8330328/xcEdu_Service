package com.xuecheng.filesystem.service;

import com.xuecheng.framework.domain.filesystem.response.UploadFileResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 98050
 * @Time: 2019-04-18 15:12
 * @Feature:
 */
public interface FileSystemService {
    /**
     * 上传文件
     * @param multipartFile 文件
     * @param filetag 文件标签
     * @param businesskey 业务key
     * @param metadata 元信息，json格式
     * @return
     */
    UploadFileResult upload(MultipartFile multipartFile, String filetag, String businesskey, String metadata);
}
