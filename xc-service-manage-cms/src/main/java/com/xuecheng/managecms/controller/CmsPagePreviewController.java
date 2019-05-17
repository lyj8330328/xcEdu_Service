package com.xuecheng.managecms.controller;

import com.xuecheng.framework.web.BaseController;
import com.xuecheng.managecms.service.CmsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * @Author: 98050
 * @Time: 2019-03-30 19:12
 * @Feature: 页面预览
 */
@Controller
public class CmsPagePreviewController extends BaseController {

    @Autowired
    private CmsService cmsService;

    @RequestMapping(value = "/cms/preview/{pageId}",method = RequestMethod.GET)
    public void preview(@PathVariable("pageId") String pageId) throws IOException {
        String pageHtml = "";
        if (!pageId.equals("text.png")){
            pageHtml = this.cmsService.getPageHtml(pageId);
        }
        response.setHeader("Content-type", "text/html;charset=utf-8");
        if (StringUtils.isNotEmpty(pageHtml)){
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(pageHtml.getBytes(StandardCharsets.UTF_8));
        }
    }
}
