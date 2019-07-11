package com.mashup.thing.user.dto;

import com.mashup.thing.search.domain.Search;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResSearchDto {
    private Long id;
    private String text;
    private LocalDateTime createAt;

    private ResSearchDto(Search search) {
        this.id = search.getId();
        this.text = search.getText();
        this.createAt = search.getCreateAt();
    }

    public static ResSearchDto from(Search search) {
        return new ResSearchDto(search);
    }
}
