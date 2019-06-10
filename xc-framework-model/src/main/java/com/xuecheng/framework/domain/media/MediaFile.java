package com.xuecheng.framework.domain.media;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author 98050
 */
@Data
@ToString
@Document(collection = "media_file")
public class MediaFile {
    @Id
    /**
     * 文件id
     */
    private String fileId;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件原始名称
     */
    private String fileOriginalName;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件url
     */
    private String fileUrl;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * mimetype
     */
    private String mimeType;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 文件状态
     */
    private String fileStatus;
    /**
     * 上传时间
     */
    private Date uploadTime;
    /**
     * 处理状态
     */
    private String processStatus;
    /**
     * hls处理
     */
    private MediaFileProcessm3u8 mediaFileProcessm3u8;

    /**
     * tag标签用于查询
     */
    private String tag;


}
