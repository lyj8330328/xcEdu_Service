package com.xuecheng.framework.domain.media;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author 98050
 */
@Data
@ToString
public class MediaFileProcessm3u8 extends MediaFileProcess {

    /**
     * ts列表
     */
    private List<String> tslist;

}
