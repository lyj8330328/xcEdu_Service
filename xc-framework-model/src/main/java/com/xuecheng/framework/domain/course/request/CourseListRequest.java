package com.xuecheng.framework.domain.course.request;

import com.xuecheng.framework.model.request.RequestData;
import lombok.Data;
import lombok.ToString;

/**
 * @author 98050
 */
@Data
@ToString
public class CourseListRequest extends RequestData {
    /**
     * 公司id
     */
    private String companyId;
}
