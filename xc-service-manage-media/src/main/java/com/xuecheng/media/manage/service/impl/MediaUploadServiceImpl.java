package com.xuecheng.media.manage.service.impl;

import com.xuecheng.framework.domain.media.MediaFile;
import com.xuecheng.framework.domain.media.response.CheckChunkResult;
import com.xuecheng.framework.domain.media.response.MediaCode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.media.manage.dao.MediaFileRepository;
import com.xuecheng.media.manage.service.MediaUploadService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * @Author: 98050
 * @Time: 2019-06-21 11:01
 * @Feature:
 */
@Service
public class MediaUploadServiceImpl implements MediaUploadService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MediaUploadServiceImpl.class);

    private final MediaFileRepository mediaFileRepository;

    @Autowired
    public MediaUploadServiceImpl(MediaFileRepository mediaFileRepository) {
        this.mediaFileRepository = mediaFileRepository;
    }

    @Value("${xc-service-manage-media.upload-location}")
    private String uploadPath;


    /**
     * 创建文件目录
     * @param fileMd5 文件md5
     * @return 是否创建成功
     */
    private boolean createFileFold(String fileMd5) {
        String fileFolderPath = getFileFolderPath(fileMd5);
        File fileFolder = new File(fileFolderPath);
        if (!fileFolder.exists()){
            return fileFolder.mkdirs();
        }
        return true;
    }


    /**
     * 根据文件的md5得到文件路径
     * 规则：
     * 一级目录：md5的第一个字符
     * 二级目录：md5的第二个字符
     * 三级目录：md5
     * 文件名字：md5+文件扩展名
     * @param fileMd5 文件md5值
     * @param fileExt 文件扩展名
     * @return 文件路径
     */
    private String getFilePath(String fileMd5, String fileExt) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(fileMd5, 0, 1)
                    .append("/")
                    .append(fileMd5,1,2)
                    .append("/")
                    .append(fileMd5)
                    .append("/")
                    .append(fileMd5)
                    .append(".")
                    .append(fileExt);
        return stringBuffer.toString();
    }


    /**
     * 得到文件目录
     * @param fileMd5
     * @return
     */
    private String getFileFolderPath(String fileMd5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(uploadPath)
                    .append(fileMd5, 0, 1)
                    .append("/")
                    .append(fileMd5,1,2)
                    .append("/")
                    .append(fileMd5)
                    .append("/");
        return stringBuffer.toString();
    }

    /**
     * 获取文件的相对路径
     * @param fileMd5
     * @param fileExt
     * @return
     */
    private String getFileFolderRelativePath(String fileMd5, String fileExt) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(fileMd5, 0, 1)
                    .append("/")
                    .append(fileMd5,1,2)
                    .append("/")
                    .append(fileMd5)
                    .append("/");
        return stringBuffer.toString();
    }

    /**
     * 获取分块文件目录
     * @param fileMd5
     * @return
     */
    private String getChunkFileFolderPath(String fileMd5) {
        return getFileFolderPath(fileMd5) + "/" + "chunks" + "/";
    }

    /**
     * 文件上传注册：检查文件是否存在，不存在的话就创建对应的文件存储目录
     * @param fileMd5 文件md5
     * @param fileName 文件名称
     * @param fileSize 文件大小
     * @param mimetype 文件类型
     * @param fileExt 文件扩展名
     * @return
     */
    @Override
    public ResponseResult register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
        //1.检查文件在磁盘上是否存在
        String filePath = getFilePath(fileMd5,fileExt);
        File file = new File(filePath);
        //2.检查文件信息在mongodb中是否存在
        Optional<MediaFile> optional = this.mediaFileRepository.findById(fileMd5);
        //3.如果文件存在则直接返回
        if (file.exists() && optional.isPresent()){
            ExceptionCast.cast(MediaCode.UPLOAD_FILE_REGISTER_EXIST);
        }
        //4.文件不存在，则直接上传
        boolean fileFold = createFileFold(fileMd5);
        if (!fileFold){
            ExceptionCast.cast(MediaCode.UPLOAD_FILE_REGISTER_CREATE_FOLDER_FAIL);
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 检查分块
     * @param fileMd5 文件md5
     * @param chunk 分块编号
     * @param chunkSize 分块大小
     * @return
     */
    @Override
    public CheckChunkResult checkChunk(String fileMd5, Integer chunk, Integer chunkSize) {
        //1.得到块文件所在路径
        String chunkFileFolderPath = getChunkFileFolderPath(fileMd5);
        //2.块文件的文件名以1，2，3顺序命名，没有扩展名
        File chunkFile = new File(chunkFileFolderPath + chunk);
        if (chunkFile.exists() && chunkFile.length() == chunkSize){
            return new CheckChunkResult(MediaCode.CHUNK_FILE_EXIST_CHECK,true);
        }else {
            return new CheckChunkResult(MediaCode.CHUNK_FILE_EXIST_CHECK,false);
        }
    }

    /**
     * 创建分块文件目录
     * @param fileMd5
     * @return
     */
    private boolean createChunkFileFold(String fileMd5) {
        String chunkFileFolderPath = getChunkFileFolderPath(fileMd5);
        File chunkFileFolder = new File(chunkFileFolderPath);
        if (!chunkFileFolder.exists()){
            return chunkFileFolder.mkdirs();
        }
        return true;
    }

    /**
     * 上传分块文件
     * @param file 分块文件
     * @param chunk 分块编号
     * @param fileMd5 文件md5
     * @return
     */
    @Override
    public ResponseResult uploadChunk(MultipartFile file, Integer chunk, String fileMd5) {
        if (file == null){
            ExceptionCast.cast(MediaCode.UPLOAD_CHUNK_IS_NULL);
        }
        //1.创建块文件目录
        boolean fileFold = createChunkFileFold(fileMd5);
        if (!fileFold){
            ExceptionCast.cast(MediaCode.CREATE_CHUNK_FOLDER_FAIL);
        }
        //2.块文件
        File chunkFile = new File(getChunkFileFolderPath(fileMd5) + chunk);
        //3.上传的块文件
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try{
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(chunkFile);
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("upload chunk file fail:{}",e.getMessage());
            ExceptionCast.cast(MediaCode.CHUNK_FILE_UPLOAD_FAIL);
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }


    /**
     * 合并块文件
     * @param fileMd5 文件md5
     * @param fileName 文件名称
     * @param fileSize 文件大小
     * @param mimetype 文件类型
     * @param fileExt 文件扩展名
     * @return
     */
    @Override
    public ResponseResult mergeChunk(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
        //1.将块文件合并
        //1.1获取块文件路径
        String chunkFileFolderPath = getChunkFileFolderPath(fileMd5);
        File chunkFileFolder = new File(chunkFileFolderPath);
        if (!chunkFileFolder.exists()){
            chunkFileFolder.mkdirs();
        }
        //1.2合并文件路径
        File mergeFile = new File(getFilePath(fileMd5, fileExt));
        //1.3创建合并文件
        if (mergeFile.exists()){
            mergeFile.delete();
        }
        boolean newFile = false;
        try {
            newFile = mergeFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("mergechunks..create mergeFile fail:{}",e.getMessage());
        }
        if (!newFile){
            ExceptionCast.cast(MediaCode.MERGE_FILE_CREATE_FAIL);
        }
        //1.4获取块文件列表
        List<File> chunkFiles = getChunkFiles(chunkFileFolder);
        //1.5合并文件
        mergeFile = mergeChunkFile(mergeFile,chunkFiles);
        if (mergeFile.length() == 0){
            ExceptionCast.cast(MediaCode.MERGE_FILE_FAIL);
        }
        //2.检验文件md5是否正确
        boolean check = checkFileMd5(mergeFile,fileMd5);
        if (!check){
            ExceptionCast.cast(MediaCode.MERGE_FILE_CHECKFAIL);
        }
        //3.向Mongodb中写入文件信息
        MediaFile mediaFile = new MediaFile();
        mediaFile.setFileId(fileMd5);
        mediaFile.setFileName(fileName + "." + fileExt);
        mediaFile.setFileOriginalName(fileName);
        //文件路径保存相对路径
        mediaFile.setFilePath(getFileFolderRelativePath(fileMd5,fileExt));
        mediaFile.setFileSize(fileSize);
        mediaFile.setMimeType(mimetype);
        mediaFile.setFileType(fileExt);
        mediaFile.setFileStatus("301002");
        mediaFileRepository.save(mediaFile);
        return new ResponseResult(CommonCode.SUCCESS);
    }


    /**
     * 文件md5校验
     * @param mergeFile
     * @param fileMd5
     * @return
     */
    private boolean checkFileMd5(File mergeFile, String fileMd5) {
        if (mergeFile == null || StringUtils.isEmpty(fileMd5)){
            return false;
        }
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(mergeFile);
            //得到文件的md5
            String mergeFileMd5 = DigestUtils.md5Hex(inputStream);
            if (fileMd5.equals(mergeFileMd5)){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("checkFileMd5 error,file is:{},md5 is: {}",mergeFile.getAbsoluteFile(),fileMd5);
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 合并文件
     * @param mergeFile
     * @param chunkFiles
     * @return
     */
    private File mergeChunkFile(File mergeFile, List<File> chunkFiles) {
        try {
            RandomAccessFile write = new RandomAccessFile(mergeFile, "rw");
            //缓冲区
            byte[] bytes = new byte[1024];
            //合并文件
            for (File chunk : chunkFiles){
                RandomAccessFile read = new RandomAccessFile(chunk, "rw");
                int len = -1;
                while ((len = read.read(bytes)) != -1){
                    write.write(bytes,0,len);
                }
                read.close();
            }
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("merge file error:{}",e.getMessage());
            return null;
        }
        return mergeFile;
    }

    /**
     * 获取块文件列表
     * @param chunkFileFolder
     * @return
     */
    private List<File> getChunkFiles(File chunkFileFolder) {
        File[] chunkFiles = chunkFileFolder.listFiles();
        if (chunkFiles == null){
            ExceptionCast.cast(MediaCode.GET_CHUNK_FILE_FAIL);
        }
        List<File> chunkFileList = new ArrayList<>(Arrays.asList(chunkFiles));
        //排序
        chunkFileList.sort(Comparator.comparingInt(f -> Integer.valueOf(f.getName())));
        return chunkFileList;
    }
}
