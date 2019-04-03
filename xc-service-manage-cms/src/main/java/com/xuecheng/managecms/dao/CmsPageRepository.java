package com.xuecheng.managecms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: 98050
 * @Time: 2019-03-19 22:10
 * @Feature: mongodb  mapper
 */
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {


    /**
     * 页面新增时根据页面名称、站点Id、页面pageWebPath进行校验
     * @param pageName 页面名称
     * @param siteId 站点ID
     * @param pageWebPath 页面路径
     * @return
     */
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName,String siteId,String pageWebPath);


    /**
     * 根据页面名称查询
     * @param pageName
     * @return
     */
    CmsPage findByPageName(String pageName);

    /**
     * 根据页面名称和类型查询
     * @param pageName
     * @param pageType
     * @return
     */
    CmsPage findByPageNameAndPageType(String pageName,String pageType);

    /**
     * 根据站点和页面类型查询记录数
     * @param siteId
     * @param pageType
     * @return
     */
    int countBySiteIdAndPageType(String siteId,String pageType);

    /**
     * 根据站点和页面类型分页查询
     * @param siteId
     * @param pageType
     * @param pageable
     * @return
     */
    Page<CmsPage> findBySiteIdAndPageType(String siteId, String pageType, Pageable pageable);
}
