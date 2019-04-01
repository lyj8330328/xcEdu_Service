package com.xuecheng.managecms.service.impl;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.response.CmsConfigResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.managecms.dao.CmsConfigRepository;
import com.xuecheng.managecms.service.CmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: 98050
 * @Time: 2019-03-28 16:49
 * @Feature:
 */
@Service
public class CmsConfigServiceImpl implements CmsConfigService {

    @Autowired
    private CmsConfigRepository cmsConfigRepository;

    /**
     * 根据id查询配置管理信息
     * @param id
     * @return
     */
    @Override
    public CmsConfig getConfigById(String id) {
        Optional<CmsConfig> optional = cmsConfigRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public QueryResponseResult queryByPage(int page, int size) {
        return null;
    }

    @Override
    public CmsConfigResult add(CmsConfig cmsConfig) {
        return null;
    }

    @Override
    public CmsConfig findById(String id) {
        return null;
    }

    @Override
    public CmsConfigResult update(String id, CmsConfig cmsConfig) {
        return null;
    }

    @Override
    public ResponseResult delete(String id) {
        return null;
    }
}
