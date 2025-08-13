package dev.m7wq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter@Getter
public class TopsConfig {

    private String header;
    private String lineFormat;
    private String footer;
    private double lineSpacing;
    private long refreshInterval; // Ticks


    

    
}
