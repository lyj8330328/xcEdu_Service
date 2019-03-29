package com.xuecheng.managecms.service.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.managecms.dao.CmsPageRepository;
import com.xuecheng.managecms.dao.CmsTemplateRepository;
import com.xuecheng.managecms.service.CmsService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: 98050
 * @Time: 2019-03-20 14:55
 * @Feature:
 */
@Service
public class CmsServiceImpl implements CmsService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    /**
     *
     * @param page 页码
     * @param size 页大小
     * @param queryPageRequest 具体请求参数
     * @return 分页列表
     */
    @Override
    public QueryResponseResult queryByPage(int page, int size, QueryPageRequest queryPageRequest) {
        if (queryPageRequest == null){
            queryPageRequest = new QueryPageRequest();
        }
        //1.自定义查询条件匹配器

        //1.1 页面别名模糊查询
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("pageType", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("pageName", ExampleMatcher.GenericPropertyMatchers.contains());
        //1.2 封装自定义查询对象
        CmsPage cmsPage = new CmsPage();
        //站点Id
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        //页面别名
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAlias())){
            cmsPage.setPageAliase(queryPageRequest.getPageAlias());
        }
        //模板Id
        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId())){
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        //页面名称
        if (StringUtils.isNotEmpty(queryPageRequest.getPageName())){
            cmsPage.setPageName(queryPageRequest.getPageName());
        }
        //页面Id
        if (StringUtils.isNotEmpty(queryPageRequest.getPageId())){
            cmsPage.setPageId(queryPageRequest.getPageId());
        }


        //2.构造分页对象
        if (page <= 0){
            page = 1;
        }
        //mongodb的页数从0开始
        page -= 1;
        if (size <= 0){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page, size);

        //3.创建条件实例
        Example<CmsPage> example = Example.of(cmsPage, matcher);

        //4.查询
        Page<CmsPage> pages = this.cmsPageRepository.findAll(example, pageable);


        //5.返回结果组装
        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setList(pages.getContent());
        queryResult.setTotal(pages.getTotalElements());

        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    /**
     * @param cmsPage cms对象
     * @return
     */
    @Override
    public CmsPageResult add(CmsPage cmsPage) {
        //1.校验cmsPage是否为空
        if (cmsPage == null){
            //抛出异常，非法请求
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        }
        //2.根据页面名称查询（页面名称已在mongodb创建了唯一索引）
        CmsPage temp = this.cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        //3.校验页面是否存在，已存在则抛出异常
        if (temp != null){
            //抛出异常，已存在相同的页面名称
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        //4.添加页面主键由spring data自动生成
        cmsPage.setPageId(null);
        CmsPage save = cmsPageRepository.save(cmsPage);
        //5.返回结果
        return new CmsPageResult(CommonCode.SUCCESS, save);
    }

    /**
     * 根据页面id查询
     * @param id
     * @return
     */
    @Override
    public CmsPage findById(String id) {
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }else {
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
            return null;
        }
    }

    /**
     * 更新页面
     * @param id 页面id
     * @param cmsPage 页面对象
     * @return
     */
    @Override
    public CmsPageResult update(String id, CmsPage cmsPage) {
        CmsPage temp = findById(id);
        if (temp != null){
            //更新页面:模板id、所属站点、页面别名、页面名称、访问路径、物理路径
            temp.setTemplateId(cmsPage.getTemplateId());
            temp.setSiteId(cmsPage.getSiteId());
            temp.setPageAliase(cmsPage.getPageAliase());
            temp.setPageName(cmsPage.getPageName());
            temp.setPageWebPath(cmsPage.getPageWebPath());
            temp.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            temp.setDataUrl(cmsPage.getDataUrl());
            CmsPage save = this.cmsPageRepository.save(temp);
            return new CmsPageResult(CommonCode.SUCCESS, save);
        }else {
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
            return null;
        }
    }

    /**
     * 删除页面
     * @param id
     * @return
     */
    @Override
    public ResponseResult delete(String id) {
        CmsPage cmsPage = findById(id);
        if (cmsPage != null){
            // 删除页面
            this.cmsPageRepository.deleteById(id);
            return ResponseResult.SUCCESS();
        }
        return  ResponseResult.FAIL();
    }

    /**
     * 页面静态化
     * @param pageId
     * @return
     */
    @Override
    public String getPageHtml(String pageId) {
        //1.获取页面模型数据
        Map model = getModelByPageId(pageId);
        if (model == null){
            //2.获取页面模型为空的时候抛出异常
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAISNULL);
        }
        //3.获取页面模板
        String templateContent = getTemplateByPageId(pageId);
        if (StringUtils.isEmpty(templateContent)){
            //4.页面模板为空的话就抛出异常
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        //5.执行静态化
        String html = generateHtml(templateContent,model);
        if (StringUtils.isEmpty(html)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }
        return html;
    }

    /**
     * 页面静态化
     * @param templateContent
     * @param model
     * @return
     */
    private String generateHtml(String templateContent, Map model){
        try {
            //1.创建配置类
            Configuration configuration = new Configuration(Configuration.getVersion());
            //2.模板加载器
            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            stringTemplateLoader.putTemplate("template", templateContent);
            configuration.setTemplateLoader(stringTemplateLoader);
            //4.得到模板
            Template template = configuration.getTemplate("template", "utf-8");
            //5.静态化
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            return html;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据页面Id查询模板
     * @param pageId
     * @return
     */
    private String getTemplateByPageId(String pageId) {
        //1.查询页面信息
        CmsPage cmsPage = this.findById(pageId);
        if (cmsPage == null){
            //2.页面不存在
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        }
        //3.取出getTemplateId
        String templateId = cmsPage.getTemplateId();
        if (StringUtils.isEmpty(templateId)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        System.out.println(templateId);
        //4.查询页面模板
        Optional<CmsTemplate> optional = this.cmsTemplateRepository.findById(templateId);
        if (optional.isPresent()){
            CmsTemplate cmsTemplate = optional.get();
            //5.模板文件Id
            String templateFileId = cmsTemplate.getTemplateFileId();
            //6.根据id查询文件
            GridFSFile gridFSFile = this.gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(templateFileId)));
            //7.打开下载流对象
            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            //8.创建gridResource用于获取流对象
            GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
            //9.获取流中的数据
            try {
                return IOUtils.toString(gridFsResource.getInputStream(),"UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据页面Id查询模型数据
     * @param pageId
     * @return
     */
    private Map getModelByPageId(String pageId) {
        //1.查询页面信息
        CmsPage cmsPage = this.findById(pageId);
        if (cmsPage == null){
            //2.页面不存在
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        }
        //3.取出dataUrl
        String dataUrl = cmsPage.getDataUrl();
        if (StringUtils.isEmpty(dataUrl)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAISNULL);
        }
        ResponseEntity<Map> entity = this.restTemplate.getForEntity(dataUrl, Map.class);
        return entity.getBody();
    }
}
