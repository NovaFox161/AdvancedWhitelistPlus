package com.cloudcraftgaming.advancedwhitelistplus;

import com.cloudcraftgaming.advancedwhitelistplus.listeners.JoinListener;
import com.cloudcraftgaming.advancedwhitelistplus.utils.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Nova Fox on 7/12/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: AdvancedWhiteList
 */
public class Main extends JavaPlugin {
    public static Main plugin;

    @Override
    public void onDisable() {}

    @Override
    public void onEnable() {
        plugin = this;

        FileManager.createConfig();

        FileManager.checkFileVersions();

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
    }
}