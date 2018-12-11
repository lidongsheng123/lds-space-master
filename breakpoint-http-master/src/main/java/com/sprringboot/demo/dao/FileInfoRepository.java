package com.sprringboot.demo.dao;

import com.sprringboot.demo.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName FileInfoRepository.java
 * @Description TODO
 * @createTime 2018年11月28日 16:54:00
 */
@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
}
