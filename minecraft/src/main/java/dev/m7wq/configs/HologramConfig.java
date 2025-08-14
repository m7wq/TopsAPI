package dev.m7wq.configs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder.Default;

@Builder
@Getter
@Setter
public class HologramConfig {

    @Default
    private String luckPermsFormat = "%prefix% %sufix%%name%";

    @Default
    private double lineSpacing = 0.25;

    @Default
    private String header = "&8-------- LEADERBOARD --------";

    @Default
    private String lineFormat = "&8#%rank%. &e%name% &7(&f%amount%&7)";

    @Default
    private String footer = "&8-------- LEADERBOARD --------";

}
