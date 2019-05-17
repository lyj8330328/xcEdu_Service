package com.xuecheng.framework.domain.course.response;

import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: 98050
 * @Time: 2019-05-17 16:46
 * @Feature:
 */
@Data
@ToString
@NoArgsConstructor
public class CoursePublicResult extends ResponseResult {
    private String previewUrl;
    public CoursePublicResult(ResultCode resultCode,String previewUrl){
        super(resultCode);
        this.previewUrl = previewUrl;
    }
}
