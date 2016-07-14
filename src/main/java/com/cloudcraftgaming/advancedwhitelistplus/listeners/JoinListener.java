package com.cloudcraftgaming.advancedwhitelistplus.listeners;

import com.cloudcraftgaming.advancedwhitelistplus.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

/**
 * Created by Nova Fox on 7/13/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: AdvancedWhiteList
 */
public class JoinListener implements Listener {
    private Main plugin;

    public JoinListener(Main instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void determineCanJoin(AsyncPlayerPreLoginEvent event) {
        if (plugin.getConfig().getString("Slot.Reserve.Enabled").equalsIgnoreCase("True")) {
            Integer maxPlayers = plugin.getServer().getMaxPlayers();
            Integer currentPlayers = Bukkit.getOnlinePlayers().size();
            if (currentPlayers >= maxPlayers){
                //Server is full, determine if should kick player or not.
                Player player = Bukkit.getPlayer(event.getUniqueId());
                if (player.hasPermission("AWP.slot.reserved")) {
                    //Kick random player, allow them to join.
                    if (plugin.getConfig().getString("Slot.Reserve.RandomKick.Enabled").equalsIgnoreCase("True")) {
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            if (!p.hasPermission("AWP.slot.reserved")) {
                                Bukkit.getScheduler().runTask(Main.plugin, new Runnable() {
                                    @Override
                                    public void run() {
                                        String kickMsgOr = "Randomly kicked for donor!";
                                        String kickMsg = ChatColor.translateAlternateColorCodes('&', kickMsgOr);
                                        p.kickPlayer(kickMsg);
                                    }
                                });

                                break;
                            }
                        }
                    }
                    event.allow();
                    event.setLoginResult(Result.ALLOWED);
                } else {
                    String msgOr = "Server full";
                    String msg = ChatColor.translateAlternateColorCodes('&', msgOr);
                    event.disallow(Result.KICK_FULL, msg);
                }
            }
        }
    }
}