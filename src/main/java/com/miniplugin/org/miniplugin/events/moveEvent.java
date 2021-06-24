package com.miniplugin.org.miniplugin.events;

import com.miniplugin.org.miniplugin.singletons.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class moveEvent implements Listener {
    playerData playerData_ = playerData.getInstance();
    player1Points player1Score = player1Points.getInstance();
    player2Points player2Score = player2Points.getInstance();
    inGameVar inGame = inGameVar.getInstance();
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        if (inGame.i) {
            Player player = event.getPlayer();

            if (player.getName().equals(Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getName())) {
                if (Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getLocation().getY() < 40) {
                    // Player died in void
                    Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).setHealth(0);
                } else if (Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getLocation().getBlock().getRelative(BlockFace.SELF).getType() == Material.BLUE_CARPET) {
                    // Player is in the goal
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "playsound minecraft:entity.player.levelup master " + Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getName());
                    Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).sendMessage(ChatColor.GREEN + "You scored!");
                    Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).sendMessage(ChatColor.RED + Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getName() + " scored!");
                    player1Score.p += 1;
                    Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).setHealth(0);
                    Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).setHealth(0);
                }
            } else if (player.getName().equals(Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getName())) {
                if (Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getLocation().getY() < 40) {
                    // Player died in void
                    Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).setHealth(0);
                } else if (Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getLocation().getBlock().getRelative(BlockFace.SELF).getType() == Material.RED_CARPET) {
                    // Player is in the goal
                    Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).sendMessage(ChatColor.GREEN + "You scored!");
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "playsound minecraft:entity.player.levelup master " + Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getName());
                    Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).sendMessage(ChatColor.RED + Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getName() + " scored!");
                    player2Score.p += 1;
                    Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).setHealth(0);
                    Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).setHealth(0);
                }
            }
        }

    }
}
