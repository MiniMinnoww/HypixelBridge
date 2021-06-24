package com.miniplugin.org.miniplugin.events;

import com.miniplugin.org.miniplugin.singletons.inGameVar;
import com.miniplugin.org.miniplugin.singletons.playerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Objects;

public class blockEvents implements Listener {
    playerData playerData_ = playerData.getInstance();
    inGameVar inGame = inGameVar.getInstance();
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (inGame.i && (event.getPlayer().getName().equalsIgnoreCase(Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getName()) || event.getPlayer().getName().equalsIgnoreCase(Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getName()))) {
            if (event.getBlock().getType() == Material.IRON_BLOCK || event.getBlock().getType() == Material.BLUE_CARPET || event.getBlock().getType() == Material.LAPIS_BLOCK || event.getBlock().getType() == Material.RED_CARPET || event.getBlock().getType() == Material.REDSTONE_BLOCK) {
                event.getPlayer().sendMessage(ChatColor.RED + "You can't break this block!");
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (inGame.i && (event.getPlayer().getName().equalsIgnoreCase(Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getName()) || event.getPlayer().getName().equalsIgnoreCase(Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getName()))) {
            if (event.getBlock().getLocation().getX() < -17 || event.getBlock().getLocation().getX() > 17 || event.getBlock().getLocation().getY() > 77) {
                event.getPlayer().sendMessage(ChatColor.RED + "You can't place blocks here!");
                event.setCancelled(true);
            }
        }
    }
}
