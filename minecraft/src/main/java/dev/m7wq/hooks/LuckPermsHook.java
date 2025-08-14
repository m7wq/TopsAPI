package dev.m7wq.hooks;



import dev.m7wq.configs.HologramConfig;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;

public class LuckPermsHook {


    public static String format(String name, HologramConfig config){

      

        LuckPerms luckPerms = LuckPermsProvider.get();

        if (luckPerms==null) {
            return name;
        }

        String format = config.getLuckPermsFormat(); 

   

        CachedMetaData cachedMetaData = luckPerms.getUserManager().getUser(name).getCachedData().getMetaData();
        String suffix = cachedMetaData.getSuffix();
        String prefix = cachedMetaData.getPrefix();

        if (prefix!=null) {
            format = format.replace("%prefix%", prefix);
        }
 
        if (suffix!=null) {
            format = format.replace("%suffix%", suffix);
        }

        
        format = format.replace("%name%", name);
     

     
        return format;
        
        
    }

}
