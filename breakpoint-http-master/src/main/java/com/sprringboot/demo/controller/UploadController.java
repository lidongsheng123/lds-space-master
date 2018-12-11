package com.sprringboot.demo.controller;

import com.sprringboot.demo.entity.Chunk;
import com.sprringboot.demo.entity.FileInfo;
import com.sprringboot.demo.servcie.ChunkService;
import com.sprringboot.demo.servcie.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sprringboot.demo.utils.FileUtils.generatePath;
import static com.sprringboot.demo.utils.FileUtils.merge;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName UploadController.java
 * @Description TODO
 * @createTime 2018年11月28日 16:41:00
 */
@RestController
@RequestMapping("/uploader")
@Slf4j
@CrossOrigin
public class UploadController {


    @Value("${prop.upload-folder}")
    private String uploadFolder;
    @Resource
    private FileInfoService fileInfoService;
    @Resource
    private ChunkService chunkService;

    @PostMapping("/chunk")
    public Object uploadChunk(Chunk chunk) {
        Map<Object, Object> result = new HashMap<>();
        MultipartFile file = chunk.getFile();
        log.debug("file originName: {}, chunkNumber: {}", file.getOriginalFilename(), chunk.getChunkNumber());
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(generatePath(uploadFolder, chunk));
            //文件写入指定路径
            Files.write(path, bytes);
            log.debug("文件 {} 写入成功, uuid:{}", chunk.getFilename(), chunk.getIdentifier());
            chunkService.saveChunk(chunk);
            result.put("message", "success");
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return "后端异常...";
        }
    }

    @GetMapping("/chunk")
    public Object checkChunk(Chunk chunk, HttpServletResponse response) {
        HashMap<String, Object> result = new HashMap<>();
        List<Integer> uploaded_chunks = new ArrayList<>();
        //检查文件是否上传过
        boolean flag = chunkService.checkChunk(chunk.getIdentifier(), chunk.getChunkNumber());
        if (flag) {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            result.put("uploaded_chunks", uploaded_chunks);
            return result;
        } else {
            //文件上传成功的块
            uploaded_chunks = chunkService.checkChunkSucess(chunk);
            if (uploaded_chunks != null && !uploaded_chunks.isEmpty()) {
                result.put("uploaded_chunks", uploaded_chunks);
                return result;
            }
        }
        return null;
    }

    @PostMapping("/mergeFile")
    public String mergeFile(@RequestBody FileInfo fileInfo) {
        String filename = fileInfo.getFilename();
        String file = uploadFolder + "/" + fileInfo.getIdentifier() + "/" + filename;
        String folder = uploadFolder + "/" + fileInfo.getIdentifier();
        merge(file, folder, filename);
        fileInfo.setLocation(file);
        fileInfoService.addFileInfo(fileInfo);
        return "success";
    }
}
