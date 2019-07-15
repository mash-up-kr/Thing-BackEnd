package com.mashup.thing.review.domain;

import com.mashup.thing.exception.review.NotFoundLiked;

import java.util.Arrays;

public enum Liked {
    LIKE(0), NO( 1);

    private Integer like;

    Liked(Integer like) {
        this.like = like;
    }

    public static Liked findByLiked(Integer like) {
        return Arrays.stream(Liked.values())
                .filter(liked -> liked.hasLike(like))
                .findAny()
                .orElseThrow(NotFoundLiked::new);
    }

    public boolean hasLike(Integer code) {
        return this.like.equals(like);
    }

    public Integer getLike() {
        return like;
    }
}
