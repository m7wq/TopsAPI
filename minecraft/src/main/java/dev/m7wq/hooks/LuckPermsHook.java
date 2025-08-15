package dev.m7wq.hooks;



import dev.m7wq.configs.HologramConfig;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;

public class LuckPermsHook {


    public static String format(String name, HologramConfig config){

      

        LuckPerms luckPerms = LuckPermsProvider.get();

        if (luckPerms==null) {
            return name;
        }

        String format = config.getLuckPermsFormat(); 

   
        User user = luckPerms.getUserManager().getUser(name);

        if (user==null) {
            return name;
        }

        CachedMetaData cachedMetaData = user.getCachedData().getMetaData();
        
        if(cachedMetaData == null){
            return name;
        }
        
        String suffix = cachedMetaData.getSuffix();
        String prefix = cachedMetaData.getPrefix();

        if (prefix!=null) {
            format = format.replace("%prefix%", prefix);
        }else{
            format = format.replace("%prefix%", "");
        }
 
        if (suffix!=null) {
            format = format.replace("%suffix%", suffix);
        }else{
            format = format.replace("%suffix%", "");
        }

        
        format = format.replace("%name%", name);
     

     
        return format;
        
        
    }

}
