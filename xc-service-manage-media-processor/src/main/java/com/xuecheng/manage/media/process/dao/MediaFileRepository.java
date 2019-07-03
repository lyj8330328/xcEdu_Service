package com.xuecheng.manage.media.process.dao;

import com.xuecheng.framework.domain.media.MediaFile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: 98050
 * @Time: 2019-07-03 22:25
 * @Feature:
 */
public interface MediaFileRepository extends MongoRepository<MediaFile,String> {
}
