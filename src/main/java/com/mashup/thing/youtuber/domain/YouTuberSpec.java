package com.mashup.thing.youtuber.domain;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class YouTuberSpec {
    private static final String SEPARATOR = ",";

    private YouTuberSpec() {
    }

    public static Specification<YouTuber> isTags(List<String> commonTags, List<String> categoryTags) {
        Specification<YouTuber> spec = fetchJoinVideo();

        return spec.and(isCommonTags(commonTags).and(isCategoryTags(categoryTags)));
    }

    public static Specification<YouTuber> isTags(String commonTag, String categoryTag) {
        Specification<YouTuber> spec = fetchJoinVideo();
        List<String> commonTags = Arrays.stream(commonTag.split(SEPARATOR)).collect(Collectors.toList());
        List<String> categoryTags = Arrays.stream(categoryTag.split(SEPARATOR)).collect(Collectors.toList());

        return spec.and(isCommonTags(commonTags).and(isCategoryTags(categoryTags)));
    }

    private static Specification<YouTuber> fetchJoinVideo() {
        return Specification.where((root, query, criteriaBuilder) -> {
            query.distinct(true);
            root.fetch(YouTuber_.VIDEOS, JoinType.LEFT);
            return null;
        });
    }

    private static Specification<YouTuber> isCommonTags(List<String> commonTags) {
        Specification<YouTuber> commonSpecification;
        int size = commonTags.size();
        commonSpecification = YouTuberSpec.isCommonTag(commonTags.get(0));
        for (int i = 1; i < size; i++) {

            commonSpecification = commonSpecification.and(YouTuberSpec.isCommonTag(commonTags.get(i)));
        }
        return commonSpecification;
    }

    private static Specification<YouTuber> isCategoryTags(List<String> categoryTags) {
        Specification<YouTuber> categorySpecification;
        int size = categoryTags.size();
        categorySpecification = YouTuberSpec.isCategoryTag(categoryTags.get(0));
        for (int i = 1; i < size; i++) {
            categorySpecification = categorySpecification.or(YouTuberSpec.isCategoryTag(categoryTags.get(i)));
        }
        return categorySpecification;
    }


    private static Specification<YouTuber> isCommonTag(String tag) {
        return (Specification<YouTuber>) (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(YouTuber_.COMMON_TAG), "%" + tag + "%");
    }

    private static Specification<YouTuber> isCategoryTag(String tag) {
        return (Specification<YouTuber>) (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(YouTuber_.CATEGORY_TAG), "%" + tag + "%");
    }

}

