package dev.m7wq;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import dev.m7wq.configs.HologramConfig;
import dev.m7wq.entity.holograms.HologramsContainer;
import dev.m7wq.entity.process.SortingProcessor;
import lombok.Getter;

public class Test {

    @Getter
    public static class Data{

        int kills;
        
    }

    public static void main(String[] args) {
        

        HologramsContainer hologramsContainer;

        Map<Player, Data> dataMap = new HashMap<>(); // Your Data map
        
        // dataMap.put...

        // Initialize your container
        hologramsContainer = new HologramsContainer(
            HologramConfig.builder().build(), // Configuration of the holograms container
             new Location(null, 0, 0, 0) // Holograms main location
        );

        hologramsContainer = new HologramsContainer(
            HologramConfig.
            myLocation
        );

        SortingProcessor<Player, Data> processor = SortingProcessor.<Player, Data>builder()
            .comparator(
                Comparator.comparingInt(entry -> entry.getValue().getKills()) // Comparing Method (by kills)
            )
            .mapper(entry -> Map.entry(entry.getKey().getName(), entry.getValue().getKills())) // Mapping into <String, Integer>
            .map(dataMap) // Tops map that is gonna be sorted
            .limit(10) // Tops limit (amount of players on the top leaderboard)
            .build();  
            


        TopsAPI.getInstance().appendContainer(hologramsContainer, processor);



        HologramConfig config = hologramsContainer.getConfig();
        
        // The header of the holograms container
        config.setHeader("-----LEADERBOARD-----");

        /*
         * Line design
         * Placeholders:-
         * 1. %rank% the place of the player in the leaderboard
         * 2. %name% player-in-leaderboard name
         * 3. %amount% the amount of what the holograms container comparing by
         */
        config.setLineFormat("%rank%. %name% - %amount%");
        
        // The space between each hologram into the container
        config.setLineSpacing(0.245);

        /*
         * IN THE LATEST UPDATES: I added LuckPerms support
         * If lcukperms is found this will be automatically replaced with %name%
         * Placeholders:-
         * 1. %prefix% The prefix of the player rank
         * 2. %suffix% The suffix of the player rank
         * 3. %name% The name of the current player
         * NOTE:-
         * 1. If %prefix% was written on the format and player rank doesnt have a prefix the replacment will be ""
         * 2. If %suffix% was written on the format and player rank doesnt have a suffix the replacment will be ""
         * - Thats mean if %prefix% and %suffix% were written and player rank didnt have them the output will be "%name%" (the name only)
         */
        config.setLuckPermsFormat("%prefix% | %suffix%%name%");

        // The footer of the holograms container
        config.setFooter("----------------------");
        
    }

}
