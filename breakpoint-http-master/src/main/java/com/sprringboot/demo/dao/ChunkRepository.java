package com.sprringboot.demo.dao;

import com.sprringboot.demo.entity.Chunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName ChunkRepository.java
 * @Description TODO
 * @createTime 2018年11月28日 16:34:00
 */
@Repository
public interface ChunkRepository extends JpaRepository<Chunk, Long>, JpaSpecificationExecutor<Chunk> {

}
