package dev.m7wq.entity.holograms;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import dev.m7wq.TopsAPI;
import dev.m7wq.configs.HologramConfig;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Hologram {

    private HologramConfig config;
    private HologramLine line;
    private String label;

    public Hologram(HologramLine line, HologramConfig config) {
        this.line = line;
        this.config = config;
    }

    public Hologram(String label, HologramConfig config) {
        this.label = label;
        this.config = config;
    }

    private ArmorStand armorStand;

    public void create(Location location){

        String lineLabel;
        
        
        if (this.label == null)
            lineLabel = config.getLineFormat()
                                .replace("%name%", line.getName())
                                .replace("%amount%",String.valueOf(line.getAmount()))
                                .replace("%rank%", String.valueOf(line.getRank()));
        else
            lineLabel = label;


        // Checks if the chunk isnt loaded
        if (!location.getChunk().isLoaded()) {
            System.out.println("TopsAPI: Loaded the chunk");
            location.getChunk().load();
        }

        ArmorStand armorStand = (ArmorStand) location.getWorld()
                        .spawnEntity(location,EntityType.ARMOR_STAND);


        armorStand.setCustomNameVisible(true);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setSmall(true);
        armorStand.setBasePlate(false);
        armorStand.setMarker(true);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&',lineLabel));


        if(TopsAPI.getInstance().getConfig().isDebug()){
            System.out.println("Spawned at: " + armorStand.getLocation());
            System.out.println("Entity UUID: " + armorStand.getUniqueId());
            System.out.println("Name: " + armorStand.getCustomName());
            System.out.println("Visible Name: " + armorStand.isCustomNameVisible());
        }

        this.armorStand = armorStand;

    }

    public void delete() {

        if(armorStand != null){
            armorStand.setCustomName("");
            armorStand.remove();
        }
    }

}
