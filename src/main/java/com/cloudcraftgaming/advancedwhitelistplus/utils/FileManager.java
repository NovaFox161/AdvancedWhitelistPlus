package com.cloudcraftgaming.advancedwhitelistplus.utils;

import com.cloudcraftgaming.advancedwhitelistplus.Main;

import java.io.File;

/**
 * Created by Nova Fox on 7/12/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: AdvancedWhiteList
 */
public class FileManager {
    private static Double conVersion = 1.0;

    public static void createConfig() {
        File file = new File(Main.plugin.getDataFolder() + "/config.yml");
        if (!(file.exists())) {
            Main.plugin.getLogger().info("Generating config.yml");

            Main.plugin.getConfig().addDefault("DO NOT DELETE", "AdvancedWhitelistPlus is developed and managed by Shades161");
            Main.plugin.getConfig().addDefault("Config Version", conVersion);
            Main.plugin.getConfig().addDefault("Check for Updates", true);

            Main.plugin.getConfig().addDefault("Slot.Reserve.Enabled", true);
            Main.plugin.getConfig().addDefault("Slot.Reserve.RandomKick.Enabled", true);

            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();

            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();
        }
    }


    public static void checkFileVersions() {
        if (Main.plugin.getConfig().getDouble("Config Version") != conVersion) {
            Main.plugin.getLogger().severe("Config.yml outdated!!");
            Main.plugin.getLogger().severe("Please save your settings, delete config, and restart server!");
            Main.plugin.getLogger().severe("Disabling plugin to prevent further errors!");
            Main.plugin.getServer().getPluginManager().disablePlugin(Main.plugin);
        }
    }
}