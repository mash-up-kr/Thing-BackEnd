package com.mashup.thing.youtuber;

import com.mashup.thing.youtuber.domain.YouTuber;
import com.mashup.thing.youtuber.domain.YouTuber_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class YouTuberSpec {
    private YouTuberSpec() {
    }

    public static Specification<YouTuber> isTags(List<String> commonTags, List<String> categoryTags) {

        Specification<YouTuber> spec = Specification.where((root, query, criteriaBuilder) -> {
            query.distinct(true);
            root.fetch(YouTuber_.VIDEOS, JoinType.LEFT);
            return null;
        });

        Specification<YouTuber> commonSpecification = YouTuberSpec.isCommonTags(commonTags);
        Specification<YouTuber> categorySpecification = YouTuberSpec.isCategoryTags(categoryTags);

        return spec.and(commonSpecification.and(categorySpecification));
    }

    private static Specification<YouTuber> isCommonTags(List<String> commonTags) {
        Specification<YouTuber> commonSpecification;
        int size = commonTags.size();
        commonSpecification = YouTuberSpec.isTag(commonTags.get(0));
        for (int i = 1; i < size; i++) {

            commonSpecification = commonSpecification.and(YouTuberSpec.isTag(commonTags.get(i)));
        }
        return commonSpecification;
    }

    private static Specification<YouTuber> isCategoryTags(List<String> categoryTags) {
        Specification<YouTuber> categorySpecification;
        int size = categoryTags.size();
        categorySpecification = YouTuberSpec.isTag(categoryTags.get(0));
        for (int i = 1; i < size; i++) {
            categorySpecification = categorySpecification.or(YouTuberSpec.isTag(categoryTags.get(i)));
        }
        return categorySpecification;
    }

    private static Specification<YouTuber> isTag(String tag) {
        return (Specification<YouTuber>) (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(YouTuber_.TAG), "%" + tag + "%");
    }

}

