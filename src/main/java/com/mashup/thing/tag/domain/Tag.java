package com.mashup.thing.tag.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
@Table
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private TagType mainTag;

    private String subTag;


    public Integer getMainTagIndex() {
        return this.mainTag.getIndex();
    }
}
