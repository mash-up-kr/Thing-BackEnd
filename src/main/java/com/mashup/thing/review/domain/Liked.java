package com.mashup.thing.review.domain;

public enum Liked {
    LIKE("좋아요"), NO("싫어요");

    private String liked;

    Liked(String liked) {
        this.liked = liked;
    }

    public String getLiked() {
        return liked;
    }
}
