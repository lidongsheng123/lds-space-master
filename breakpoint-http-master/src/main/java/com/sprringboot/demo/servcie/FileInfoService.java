package com.sprringboot.demo.servcie;

import com.sprringboot.demo.dao.FileInfoRepository;
import com.sprringboot.demo.entity.FileInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName FileInfoService.java
 * @Description TODO
 * @createTime 2018年11月28日 16:55:00
 */
@Service
public class FileInfoService {

    @Resource
    private FileInfoRepository fileInfoRepository;

    public FileInfo addFileInfo(FileInfo fileInfo) {
        return fileInfoRepository.save(fileInfo);
    }
}
