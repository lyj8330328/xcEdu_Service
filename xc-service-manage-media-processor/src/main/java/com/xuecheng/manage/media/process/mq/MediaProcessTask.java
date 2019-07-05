package com.xuecheng.manage.media.process.mq;

import com.alibaba.fastjson.JSON;
import com.xuecheng.framework.domain.media.MediaFile;
import com.xuecheng.framework.domain.media.MediaFileProcessm3u8;
import com.xuecheng.framework.utils.HlsVideoUtil;
import com.xuecheng.framework.utils.Mp4VideoUtil;
import com.xuecheng.manage.media.process.dao.MediaFileRepository;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: 98050
 * @Time: 2019-07-03 22:33
 * @Feature:
 */
@Component
public class MediaProcessTask {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MediaProcessTask.class);

    /**
     * ffmpeg绝对路径
     */
    @Value("${xc-service-manage-media.mq.ffmpeg-path}")
    private String ffmpegPath;

    @Value("${xc-service-manage-media.mq.video-location}")
    private String serverPath;

    private final MediaFileRepository mediaFileRepository;

    @Autowired
    public MediaProcessTask(MediaFileRepository mediaFileRepository) {
        this.mediaFileRepository = mediaFileRepository;
    }



    @RabbitListener(queues = "${xc-service-manage-media.mq.routingkey-media-video}")
    public void receiveMediaProcessTask(String msg){
        Map msgMap = JSON.parseObject(msg,Map.class);
        LOGGER.info("receive media process task msg:{}",msgMap);
        //1.解析消息
        String mediaId = (String) msgMap.get("mediaId");
        //2.获取媒体资源文件
        Optional<MediaFile> optional = this.mediaFileRepository.findById(mediaId);
        if (optional.isPresent()){
            return;
        }
        MediaFile mediaFile = optional.get();
        //3.获取媒体资源文件类型
        String fileType = mediaFile.getFileType();
        if (fileType == null || !fileType.equals("avi")){
            //处理状态设置为无需处理
            mediaFile.setFileStatus("303004");
            this.mediaFileRepository.save(mediaFile);
            return;
        }else {
            //处理状态设置为未处理
            mediaFile.setFileStatus("303001");
            this.mediaFileRepository.save(mediaFile);
        }
        //4.生成mp4
        String videoPath = serverPath + mediaFile.getFilePath() + mediaFile.getFileName();
        String videoName = mediaFile.getFileId() + ".mp4";
        String videoFolderPath = serverPath + mediaFile.getFilePath();
        Mp4VideoUtil videoUtil = new Mp4VideoUtil(ffmpegPath, videoPath, videoName, videoFolderPath);

        String result = videoUtil.generateMp4();
        if (result == null || !result.equals("success")){
            //5.操作失败写入处理日志
            //处理状态设置为处理失败
            processFail(mediaFile,mediaFileRepository, result,"303003");
            return;
        }
        //5.生成m3u8
        //mp4视频地址
        videoPath = serverPath + mediaFile.getFilePath() + videoName;
        String m3u8Name = mediaFile.getFileId() + ".m3u8";
        String m3u8FolderPath = serverPath + mediaFile.getFilePath() + "hls/";
        HlsVideoUtil hlsVideoUtil = new HlsVideoUtil(ffmpegPath, videoPath, m3u8Name, m3u8FolderPath);
        result = hlsVideoUtil.generateM3u8();
        if (result == null || !result.equals("success")){
            //操作失败写入处理日志
            processFail(mediaFile,mediaFileRepository,result,"303003");
            return;
        }
        //6.获取m3u8列表
        List<String> tsList = hlsVideoUtil.get_ts_list();
        //7.更新处理状态为成功
        mediaFile.setProcessStatus("303002");
        MediaFileProcessm3u8 mediaFileProcessm3u8 = new MediaFileProcessm3u8();
        mediaFileProcessm3u8.setTslist(tsList);
        mediaFile.setMediaFileProcessm3u8(mediaFileProcessm3u8);
        //8.设置m3u8文件url
        mediaFile.setFileUrl(mediaFile.getFilePath() + "hls/" + m3u8Name);
        mediaFileRepository.save(mediaFile);
    }

    /**
     * 处理失败
     * @param mediaFile 媒体资源文件
     * @param mediaFileRepository
     * @param status 状态
     * @param result
     */
    private void processFail(MediaFile mediaFile, MediaFileRepository mediaFileRepository, String status, String result) {
        mediaFile.setProcessStatus(status);
        MediaFileProcessm3u8 mediaFileProcessm3u8 = new MediaFileProcessm3u8();
        mediaFileProcessm3u8.setErrormsg(result);
        mediaFile.setMediaFileProcessm3u8(mediaFileProcessm3u8);
        mediaFileRepository.save(mediaFile);
    }


}
