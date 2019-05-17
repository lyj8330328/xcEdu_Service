package com.xuecheng.managecms;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.managecms.service.CmsService;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author: 98050
 * @Time: 2019-03-29 15:57
 * @Feature:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFsTest {
    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private CmsService cmsService;

    /**
     * 存储文件
     * @throws FileNotFoundException
     */
    @Test
    public void testGridFs() throws FileNotFoundException {
        //1.要存储的文件
        File file = new File("D:\\index_banner.ftl");
        //2.定义输入流
        FileInputStream inputStream = new FileInputStream(file);
        //3.向GridFS存储文件
        CmsPage cmsPage = this.cmsService.findById("5c9df9dadff2be27386be51b");
        ObjectId objectId = gridFsTemplate.store(inputStream, "轮播图测试页面");
        //4.得到文件ID
        String fileId = objectId.toString();
        System.out.println(fileId);
    }


    /**
     * 存储文件
     * @throws FileNotFoundException
     */
    @Test
    public void testGridFs2() throws FileNotFoundException {
        //1.要存储的文件
        File file = new File("D:\\course.ftl");
        //2.定义输入流
        FileInputStream inputStream = new FileInputStream(file);
        //3.向GridFS存储文件
        ObjectId objectId = gridFsTemplate.store(inputStream, "课程详情模板文件","");
        //4.得到文件ID
        String fileId = objectId.toString();
        System.out.println(fileId);
    }

    @Autowired
    private GridFSBucket gridFSBucket;
    /**
     * 读取文件
     * @throws IOException
     */
    @Test
    public void queryFile() throws IOException {
        String fileId = "5c9e1103dff2be5cb0d36022";
        //1.根据id查询文件
        GridFSFile gridFSFile = this.gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
        //2.打开下载流对象
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //3.创建gridResource用于获取流对象
        GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
        //4.获取流中的数据
        String s = IOUtils.toString(gridFsResource.getInputStream(),"UTF-8");
        System.out.println(s);
    }


    @Test
    public void testDelFile(){
        String id = "";
        //根据文件id删除fs.files和fs.chunks中的记录
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id)));
    }
}
