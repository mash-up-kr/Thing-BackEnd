package com.mashup.thing.tag;

import com.mashup.thing.tag.service.TagService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @ApiOperation(value = "GET TAGS LIST", notes = "GET TAGS LIST API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "TAGS 조회 성공"),
            @ApiResponse(code = 403, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/v1/tags")
    public ResponseEntity<ResTagsDto> getCategoryRankings(@RequestHeader(value = "uid") String uid) {
        return ResponseEntity.status(HttpStatus.OK).body(tagService.getTags());
    }
}
