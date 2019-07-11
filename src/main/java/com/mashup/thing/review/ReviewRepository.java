package com.mashup.thing.review;

import com.mashup.thing.review.domain.Liked;
import com.mashup.thing.review.domain.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByYouTuberIdAndLiked(Long youTuberId, Liked like, Pageable createAt);
}
