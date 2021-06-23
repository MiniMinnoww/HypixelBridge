package com.miniplugin.org.miniplugin.events;
import com.miniplugin.org.miniplugin.singletons.inGameVar;
import com.miniplugin.org.miniplugin.singletons.player1Data;
import com.miniplugin.org.miniplugin.singletons.player2Data;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class blockEvents implements Listener {
    player1Data player1 = player1Data.getInstance();
    player2Data player2 = player2Data.getInstance();
    inGameVar inGame = inGameVar.getInstance();
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (inGame.i && (event.getPlayer().getName().equalsIgnoreCase(player1.p.getName()) || event.getPlayer().getName().equalsIgnoreCase(player2.p.getName()))) {
            if (event.getBlock().getType() == Material.IRON_BLOCK || event.getBlock().getType() == Material.BLUE_CARPET || event.getBlock().getType() == Material.LAPIS_BLOCK || event.getBlock().getType() == Material.RED_CARPET || event.getBlock().getType() == Material.REDSTONE_BLOCK) {
                event.getPlayer().sendMessage(ChatColor.RED + "You can't break this block!");
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (inGame.i && (event.getPlayer().getName().equalsIgnoreCase(player1.p.getName()) || event.getPlayer().getName().equalsIgnoreCase(player2.p.getName()))) {
            if (event.getBlock().getLocation().getX() < -17 || event.getBlock().getLocation().getX() > 17 || event.getBlock().getLocation().getY() > 77) {
                event.getPlayer().sendMessage(ChatColor.RED + "You can't place blocks here!");
                event.setCancelled(true);
            }
        }
    }
}
