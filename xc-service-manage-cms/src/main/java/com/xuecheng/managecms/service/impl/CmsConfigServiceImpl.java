package com.xuecheng.managecms.service.impl;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsConfigModel;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsConfigResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.managecms.dao.CmsConfigRepository;
import com.xuecheng.managecms.service.CmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
        //1.构造分页对象
        Pageable pageable = PageRequest.of(page, size);
        //2.分页查询
        Page<CmsConfig> cmsConfigs = this.cmsConfigRepository.findAll(pageable);
        //3.返回结果
        QueryResult<CmsConfig> queryResult = new QueryResult<>();
        queryResult.setList(cmsConfigs.getContent());
        queryResult.setTotal(cmsConfigs.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public CmsConfigResult add(CmsConfig cmsConfig) {
        //1.判断cmsConfig对象是否为空
        if (cmsConfig == null){
            ExceptionCast.cast(CmsCode.CMS_CONFIG_NOTEXISTS);
        }
        //2.根据数据模型名称进行查询，在name字段上创建了索引
        CmsConfig temp = this.cmsConfigRepository.findByName(cmsConfig.getName());
        if (temp != null){
            ExceptionCast.cast(CmsCode.CMS_ADDCONFIG_EXISTSNAME);
        }
        //3.获取数据模型中保存的数据
        List<CmsConfigModel> model = cmsConfig.getModel();
        if (model.size() == 0){
            ExceptionCast.cast(CmsCode.CMS_CONFIG_MODEL_ISNULL);
        }
        //4.添加
        cmsConfig.setId(null);
        CmsConfig save = this.cmsConfigRepository.save(cmsConfig);

        return new CmsConfigResult(CommonCode.SUCCESS, save);
    }

    @Override
    public CmsConfig findById(String id) {
        Optional<CmsConfig> optional = this.cmsConfigRepository.findById(id);
        if (!optional.isPresent()){
            ExceptionCast.cast(CmsCode.CMS_CONFIG_NOTEXISTS);
        }
        return optional.get();
    }

    @Override
    public CmsConfigResult update(String id, CmsConfig cmsConfig) {
        CmsConfig update = findById(id);
        if (update != null){
            update.setModel(cmsConfig.getModel());
            update.setName(cmsConfig.getName());
            CmsConfig result = this.cmsConfigRepository.save(update);
            return new CmsConfigResult(CommonCode.SUCCESS, result);
        }else {
            ExceptionCast.cast(CmsCode.CMS_CONFIG_NOTEXISTS);
            return null;
        }
    }

    @Override
    public ResponseResult delete(String id) {
        CmsConfig cmsConfig = findById(id);
        if (cmsConfig != null){
            this.cmsConfigRepository.delete(cmsConfig);
            return ResponseResult.SUCCESS();
        }else {
            ExceptionCast.cast(CmsCode.CMS_CONFIG_NOTEXISTS);
            return ResponseResult.FAIL();
        }
    }
}
