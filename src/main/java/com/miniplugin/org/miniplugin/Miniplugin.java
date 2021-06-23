package com.miniplugin.org.miniplugin;

import com.miniplugin.org.miniplugin.commands.acceptCommand;
import com.miniplugin.org.miniplugin.commands.duelCommand;
import com.miniplugin.org.miniplugin.inventory.inventoryController;
import com.miniplugin.org.miniplugin.singletons.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Miniplugin extends JavaPlugin implements CommandExecutor, Listener {
    final String prefix = "[MiniPlugin] ";
    public player1Data player1 = player1Data.getInstance();
    public player2Data player2 = player2Data.getInstance();
    public player1Points player1Score = player1Points.getInstance();
    public player2Points player2Score = player2Points.getInstance();
    public inGameVar inGame = inGameVar.getInstance();
    


    final String relearnSpawnCoords = " -212 64 -253 90 0";


    

    @Override
    public void onEnable() {
        
        Objects.requireNonNull(getCommand("duel")).setExecutor(new duelCommand());
        Objects.requireNonNull(getCommand("accept")).setExecutor(new acceptCommand());
        Objects.requireNonNull(getCommand("leave")).setExecutor(this);

        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        System.out.println(ChatColor.GOLD + prefix + ChatColor.GREEN + "Initialised");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        if (inGame.i) {
            if (command.getName().equalsIgnoreCase("leave")) {
                if (args.length == 0) {
                    if (sender.getName().equalsIgnoreCase(player2.p.getName()) || sender.getName().equalsIgnoreCase(player1.p.getName()))
                        inGame.i = false;
                        player1Score.p = 0;
                        player2Score.p = 0;
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run tp " + player1.p.getName() + relearnSpawnCoords);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run tp " + player2.p.getName() + relearnSpawnCoords);
                        player1.p = null;
                        player2.p = null;
                }

            }
        }


        return true;

    }

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
    @EventHandler
    public void onKick(PlayerKickEvent event) {

        quitGame(event.getPlayer());
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        quitGame(event.getPlayer());
    }
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
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (inGame.i) {
            inventoryController.setInventory(Objects.requireNonNull(event.getEntity().getPlayer()));
        }
    }

    public void quitGame(Player playerEvent) {
        if (inGame.i) {
            if (playerEvent.getName().equals(player1.p.getName())) {
                inGame.i = false;
                player1Score.p = 0;
                player2Score.p = 0;
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run tp " + player2.p.getName() + relearnSpawnCoords);
                player1.p = null;
                player2.p = null;
            } else if (playerEvent.getName().equals(player2.p.getName())) {
                inGame.i = false;
                player1Score.p = 0;
                player2Score.p = 0;
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run tp " + player1.p.getName() + relearnSpawnCoords);
                player1.p = null;
                player2.p = null;
            }
        }
    }



}
