package com.mashup.thing.review.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByYouTuberIdAndLiked(Long youTuberId, Liked like, Pageable createAt);
    Optional<Review> findByIdAndUserIdAndYouTuberId(Long reviewId, Long userId, Long youTuberId);
}
