package com.mashup.thing.youtuber;

import com.mashup.thing.youtuber.domain.YouTuber;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface YouTuberRepository extends JpaRepository<YouTuber, Long>, JpaSpecificationExecutor<YouTuber> {

    List<YouTuber> findAll(Specification<YouTuber> spec);

}
