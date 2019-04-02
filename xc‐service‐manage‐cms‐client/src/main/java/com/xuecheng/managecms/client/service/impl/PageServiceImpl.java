package com.xuecheng.managecms.client.service.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.managecms.client.dao.CmsPageRepository;
import com.xuecheng.managecms.client.dao.CmsSiteRepository;
import com.xuecheng.managecms.client.mq.ConsumerPostPage;
import com.xuecheng.managecms.client.service.PageService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @Author: 98050
 * @Time: 2019-04-01 18:40
 * @Feature:
 */
@Service
public class PageServiceImpl implements PageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerPostPage.class);

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Autowired
    private CmsSiteRepository cmsSiteRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    /**
     * 将静态html页面保存到服务器物理路径下
     * @param pageId 页面id
     */
    @Override
    public void savePageToServerPath(String pageId) {
        Optional<CmsPage> optional1 = this.cmsPageRepository.findById(pageId);
        if (!optional1.isPresent()){
            LOGGER.info("get cmsPage by id is null,page id is :{}",pageId);
            return;
        }
        //1.取出页面的物理路径
        CmsPage cmsPage = optional1.get();
        String pagePhysicalPath = cmsPage.getPagePhysicalPath();

        Optional<CmsSite> optional2 = this.cmsSiteRepository.findById(cmsPage.getSiteId());
        if (!optional2.isPresent()){
            LOGGER.info("get cmsSite by id is null,site id is :{}",cmsPage.getSiteId());
            return;
        }
        //2.取出站点的物理路径
        CmsSite cmsSite = optional2.get();
        String sitePhysicalPath = cmsSite.getSitePhysicalPath();

        //3.拼接页面的物理
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sitePhysicalPath).append(pagePhysicalPath).append(cmsPage.getPageName());

        //4.根据文件id下载html文件
        String fileId = cmsPage.getHtmlFileId();
        InputStream inputStream = findHtmlFileById(fileId);
        if (inputStream == null){
            LOGGER.info("getFileById InputStream is null,htmlFileId is :{}",fileId);
            return;
        }
        //5.复制文件
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(stringBuffer.toString()));
            IOUtils.copy(inputStream, fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private InputStream findHtmlFileById(String fileId) {
        try {
            //4.1根据id查询文件
            GridFSFile gridFSFile = this.gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
            //4.2打开下载流对象
            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            //4.3创建gridResource用于获取流对象
            GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
            return gridFsResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
