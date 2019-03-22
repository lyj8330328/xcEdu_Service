package com.xuecheng.managecms.service.impl;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.managecms.dao.CmsPageRepository;
import com.xuecheng.managecms.service.CmsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * @Author: 98050
 * @Time: 2019-03-20 14:55
 * @Feature:
 */
@Service
public class CmsServiceImpl implements CmsService {

    @Autowired
    private CmsPageRepository cmsPageRepository;


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
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
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
        //1.首先校验页面是否存在
        CmsPage temp = this.cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (temp == null){
            cmsPage.setPageId(null);
            this.cmsPageRepository.save(cmsPage);
            //2.返回结果
            return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
        }
        return new CmsPageResult(CommonCode.FAIL, null);
    }
}
