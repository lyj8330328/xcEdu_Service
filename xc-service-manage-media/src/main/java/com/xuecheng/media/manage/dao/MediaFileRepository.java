package com.xuecheng.media.manage.dao;

import com.xuecheng.framework.domain.media.MediaFile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: 98050
 * @Time: 2019-06-21 10:57
 * @Feature:
 */
public interface MediaFileRepository extends MongoRepository<MediaFile,String> {
}
