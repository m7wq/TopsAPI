package dev.m7wq.entity.process;

import java.util.List;

import dev.m7wq.configs.HologramConfig;
import dev.m7wq.entity.holograms.Hologram;

public interface SortingProcessor {

    void process(List<Hologram> holograms, HologramConfig config);

}
