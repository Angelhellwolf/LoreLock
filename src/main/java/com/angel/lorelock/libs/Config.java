package com.angel.lorelock.libs;

import com.angel.lorelock.Main;
import java.util.List;

public class Config {
    private String replace(String input) {
        return input.replace("&", "§");
    }
    private String NoNull(String input){
        if(input != null){
            return input;
        }
        return "-1";
    }
    public String getLore() {
        return replace(NoNull(Main.config.getString("Lore")));
    }
    public boolean isDeathDropEnabled() {
        if (Main.config.isBoolean("DeathDrop")) {
            return Main.config.getBoolean("DeathDrop");
        } else {
            return false;
        }
    }
    public List<String> getDeathDropWorlds() {
        return Main.config.getStringList("DeathDropWorld.World");
    }

    public List<String> getAntiLoreMessages() {
        return Main.config.getStringList("AntiLore.message");
    }


}
