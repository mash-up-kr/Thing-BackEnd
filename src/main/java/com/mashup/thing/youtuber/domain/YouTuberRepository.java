package com.mashup.thing.youtuber.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface YouTuberRepository extends JpaRepository<YouTuber, Long>, JpaSpecificationExecutor<YouTuber> {

    List<YouTuber> findAll(Specification<YouTuber> spec);

    Page<YouTuber> findByOrderBySoaringDesc(Pageable pageable);

}
