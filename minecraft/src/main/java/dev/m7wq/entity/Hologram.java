package dev.m7wq.entity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import dev.m7wq.TopsConfig;
import dev.m7wq.TopsAPI;

public class Hologram {

    private HologramLine line;

    public Hologram(HologramLine line){
        this.line = line;
    }


    private ArmorStand armorStand;

    public void create(Location location){

        TopsConfig config = TopsAPI.getInstance().getConfig();

        armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        String lineLabel = config.getLineFormat()
                                .replace("%name%", line.getName())
                                .replace("%amount%",String.valueOf(line.getAmount()))
                                .replace("%rank%", String.valueOf(line.getRank()));
        
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(lineLabel);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setMarker(true);

    }

    public void delete(){

        armorStand.remove();
    }

}
