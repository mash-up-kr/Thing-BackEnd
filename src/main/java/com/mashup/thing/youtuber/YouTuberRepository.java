package com.mashup.thing.youtuber;

import com.mashup.thing.youtuber.domain.YouTuber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YouTuberRepository extends JpaRepository<YouTuber, Long> {
}
