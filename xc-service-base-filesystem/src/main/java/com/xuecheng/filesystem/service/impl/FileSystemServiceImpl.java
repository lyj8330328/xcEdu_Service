package com.xuecheng.filesystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.xuecheng.filesystem.dao.FileSystemRepository;
import com.xuecheng.filesystem.service.FileSystemService;
import com.xuecheng.framework.domain.filesystem.FileSystem;
import com.xuecheng.framework.domain.filesystem.response.FileSystemCode;
import com.xuecheng.framework.domain.filesystem.response.UploadFileResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: 98050
 * @Time: 2019-04-18 15:25
 * @Feature:
 */
@Service
public class FileSystemServiceImpl implements FileSystemService {

    private static final Logger logger= LoggerFactory.getLogger(FileSystemServiceImpl.class);

    @Value("${xuecheng.fastdfs.tracker_servers}")
    private String tracker_servers;

    @Value("${xuecheng.fastdfs.connect_timeout_in_seconds}")
    private int connect_timeout_in_seconds;

    @Value("${xuecheng.fastdfs.network_timeout_in_seconds}")
    private int network_timeout_in_seconds;

    @Value("${xuecheng.fastdfs.charset}")
    private String charset;

    @Autowired
    private FileSystemRepository fileSystemRepository;


    /**
     * 加载fdfs的配置
     */
    private void initFdfsConfig(){
        try {
            ClientGlobal.initByTrackers(tracker_servers);
            ClientGlobal.setG_connect_timeout(connect_timeout_in_seconds);
            ClientGlobal.setG_network_timeout(network_timeout_in_seconds);
            ClientGlobal.setG_charset(charset);
        } catch (IOException | MyException e) {
            e.printStackTrace();
            //初始化文件系统出错
            ExceptionCast.cast(FileSystemCode.FS_INITFDFSERROR);
        }
    }

    /**
     * 上传文件
     * @param multipartFile 文件
     * @param filetag 文件标签
     * @param businesskey 业务key
     * @param metadata 元信息，json格式
     * @return
     */
    @Override
    public UploadFileResult upload(MultipartFile multipartFile, String filetag, String businesskey, String metadata) {
        if (multipartFile == null){
            ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_FILEISNULL);
        }
        //1.上传文件
        String fileId = fdfsUpload(multipartFile);

        //2.创建文件信息对象
        FileSystem fileSystem = new FileSystem();
        //2.1 文件id
        fileSystem.setFileId(fileId);
        //2.2 文件在文件系统中的路径
        fileSystem.setFilePath(fileId);
        //2.3 业务标识
        fileSystem.setBusinesskey(businesskey);
        //2.3 文件标签
        fileSystem.setFiletag(filetag);
        //2.4 元数据
        if (StringUtils.isNotEmpty(metadata)){
            try {
                Map map = JSON.parseObject(metadata,Map.class);
                fileSystem.setMetadata(map);
            }catch (Exception e){
                e.printStackTrace();
                ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_METAERROR);
            }

        }
        //2.5 名称
        fileSystem.setFileName(multipartFile.getOriginalFilename());
        //2.6 大小
        fileSystem.setFileSize(multipartFile.getSize());
        //2.7 文件类型
        fileSystem.setFileType(multipartFile.getContentType());
        //3.保存
        FileSystem save = fileSystemRepository.save(fileSystem);
        return new UploadFileResult(CommonCode.SUCCESS, save);
    }

    /**
     * 上传文件到fdfs
     * @param multipartFile
     * @return
     */
    private String fdfsUpload(MultipartFile multipartFile) {
        try {
            //1.加载配置
            initFdfsConfig();
            //2.创建tracker client
            TrackerClient trackerClient = new TrackerClient();
            //3. 获取trackerServer
            TrackerServer trackerServer = trackerClient.getConnection();
            //4. 获取storage
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            //5. 创建storage client
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
            //6. 上传文件
            //6.1 文件字节
            byte[] bytes = multipartFile.getBytes();
            //6.2 文件原始名称
            String name = multipartFile.getOriginalFilename();
            if (name == null){
                ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_NAMEISNULL);
            }
            //6.3 文件扩展名
            String extName = name.substring(name.lastIndexOf(".") + 1);
            //6.4 文件id
            return storageClient1.upload_file1(bytes, extName, null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
