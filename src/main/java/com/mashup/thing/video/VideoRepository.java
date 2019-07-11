package com.mashup.thing.video;

import com.mashup.thing.video.domain.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findAllByYouTuberId(Long youTuberId, Pageable pageable);
}
