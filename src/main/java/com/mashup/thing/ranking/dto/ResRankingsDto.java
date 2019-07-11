package com.mashup.thing.ranking.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ResRankingsDto {
    List<ResRankingDto> rankings;
    private Integer currentPage;
    private Integer nextPage;
    private Integer totalPage;
    private LocalDateTime createAt;
    private String filterType;

    public ResRankingsDto(List<ResRankingDto> rankings,
                          int currentPage, int nextPage,
                          int totalPage, LocalDateTime createAt,
                          String filterType) {
        this.rankings = rankings;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
        this.totalPage = totalPage;
        this.createAt = createAt;
        this.filterType = filterType;
    }

    public ResRankingsDto() {
        this.rankings = new ArrayList<>();
    }
}
