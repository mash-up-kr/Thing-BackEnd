package com.mashup.thing.ranking.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ResRankingsDto {
    List<ResRankingDto> rankings;
    private Integer currentPage;
    private Integer nextPage;
    private Integer totalPage;
    private LocalDateTime createAt;
    private String filterType;

}
