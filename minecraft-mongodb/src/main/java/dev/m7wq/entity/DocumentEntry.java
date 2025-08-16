package dev.m7wq.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DocumentEntry {

    private String key;
    private Integer value;

}
