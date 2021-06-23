package com.miniplugin.org.miniplugin.events;

import com.miniplugin.org.miniplugin.singletons.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class moveEvent implements Listener {
    player1Data player1 = player1Data.getInstance();
    player2Data player2 = player2Data.getInstance();
    player1Points player1Score = player1Points.getInstance();
    player2Points player2Score = player2Points.getInstance();
    inGameVar inGame = inGameVar.getInstance();
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        if (inGame.i) {
            Player player = event.getPlayer();

            if (player.getName().equals(player1.p.getName())) {
                if (player1.p.getLocation().getY() < 40) {
                    // Player died in void
                    player1.p.setHealth(0);
                } else if (player1.p.getLocation().getBlock().getRelative(BlockFace.SELF).getType() == Material.BLUE_CARPET) {
                    // Player is in the goal
                    player1.p.sendMessage(ChatColor.GREEN + "You scored!");
                    player2.p.sendMessage(ChatColor.RED + player1.p.getName() + " scored!");
                    player1Score.p += 1;
                    player1.p.setHealth(0);
                    player2.p.setHealth(0);
                }
            } else if (player.getName().equals(player2.p.getName())) {
                if (player2.p.getLocation().getY() < 40) {
                    // Player died in void
                    player2.p.setHealth(0);
                } else if (player2.p.getLocation().getBlock().getRelative(BlockFace.SELF).getType() == Material.RED_CARPET) {
                    // Player is in the goal
                    player2.p.sendMessage(ChatColor.GREEN + "You scored!");
                    player1.p.sendMessage(ChatColor.RED + player2.p.getName() + " scored!");
                    player2Score.p += 1;
                    player1.p.setHealth(0);
                    player2.p.setHealth(0);
                }
            }
        }

    }
}
