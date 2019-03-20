package com.xuecheng.managecms.service.impl;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.managecms.dao.CmsPageRepository;
import com.xuecheng.managecms.service.CmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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


    @Override
    public QueryResponseResult queryByPage(int page, int size, QueryPageRequest queryPageRequest) {
        if (queryPageRequest == null){
            queryPageRequest = new QueryPageRequest();
        }
        if (page <= 0){
            page = 1;
        }
        /**
         * mongodb的页数从0开始
         */
        page -= 1;
        if (size <= 0){
            size = 10;
        }
        /**
         * 1.构建分页对象
         */
        Pageable pageable = PageRequest.of(page, size);
        /**
         * 2.分页查询
         */
        Page<CmsPage> pages = this.cmsPageRepository.findAll(pageable);
        /**
         * 3.组装返回结果
         */
        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setList(pages.getContent());
        queryResult.setTotal(pages.getTotalElements());
        /**
         * 4.返回
         */
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }
}
