package com.sprringboot.demo.servcie;

import com.sprringboot.demo.entity.Chunk;
import com.sprringboot.demo.dao.ChunkRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName ChunkService.java
 * @Description TODO
 * @createTime 2018年11月28日 16:32:00
 */
@Service
public class ChunkService {


    @Resource
    private ChunkRepository chunkRepository;

    public void saveChunk(Chunk chunk) {
        chunkRepository.save(chunk);
    }

    public boolean checkChunk(String identifier, Integer chunkNumber) {
        Specification<Chunk> specification = (Specification<Chunk>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("identifier"), identifier));
            predicates.add(criteriaBuilder.equal(root.get("chunkNumber"), chunkNumber));
            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
        return chunkRepository.findOne(specification).orElse(null) == null;
    }


    public List<Integer> checkChunkSucess(Chunk chunk) {

        List<Integer> resultlist = new ArrayList<>();
        Specification querySpecifi = new Specification<Chunk>() {
            @Override
            public Predicate toPredicate(Root<Chunk> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNoneBlank(chunk.getIdentifier())) {
                    predicates.add(criteriaBuilder.equal(root.get("identifier"), chunk.getIdentifier()));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<Chunk> resultList = this.chunkRepository.findAll(querySpecifi);
        if (resultList != null && !resultList.isEmpty()) {
            for (Chunk chunk1 : resultList) {
                resultlist.add(chunk1.getChunkNumber());
            }
        }
        return resultlist;
    }
}