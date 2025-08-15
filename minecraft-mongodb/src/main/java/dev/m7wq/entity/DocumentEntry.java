package dev.m7wq.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DocumentEntry {

    String key;
    Integer value;

}
