package dev.m7wq.entity.holograms;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HologramLine {

    private int rank;
    private String name;
    private int amount;

}
