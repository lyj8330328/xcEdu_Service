package com.xuecheng.filesystem.dao;

import com.xuecheng.framework.domain.filesystem.FileSystem;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: 98050
 * @Time: 2019-04-17 17:35
 * @Feature:
 */
public interface FileSystemRepository extends MongoRepository<FileSystem,String> {
}
