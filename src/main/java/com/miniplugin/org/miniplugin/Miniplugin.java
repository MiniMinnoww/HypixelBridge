package com.miniplugin.org.miniplugin;

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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import com.miniplugin.org.miniplugin.mapRebuilder.*;

import java.util.Objects;

public final class Miniplugin extends JavaPlugin implements CommandExecutor, Listener {
    final String prefix = "[MiniPlugin] ";
    Player player1 = null;
    Player player2 = null;

    final String bluePlayerCoords = " -20 73 0 -90 0";
    final String redPlayerCoords = " 20 73 0 90 0" ;
    final String relearnSpawnCoords = " -212 64 -253 90 0";

    final String blueSpawnCoords = " -20 73 0 -90";
    final String redSpawnCoords = " 20 73 0 90" ;
    
    int player1Points = 0;
    int player2Points = 1;

    boolean inGame = false;

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("duel")).setExecutor(this);
        Objects.requireNonNull(getCommand("accept")).setExecutor(this);
        Objects.requireNonNull(getCommand("leave")).setExecutor(this);

        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        System.out.println(ChatColor.GOLD + prefix + ChatColor.GREEN + "Initialised");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player sender_ = (Player) sender;
        if (!inGame) {
            if (command.getName().equalsIgnoreCase("duel")) {
                if (args.length == 1) {
                    player1 = sender_;

                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    player2 = target;
                    if (target != null) {
                        target.sendMessage(ChatColor.BLUE + sender.getName() + ChatColor.WHITE + " has requested to duel you! Type /accept to duel!");
                        player1.sendMessage(ChatColor.WHITE + "Duel request sent to " + ChatColor.RED + player2.getName());
                    }
                }
            }
            if (command.getName().equalsIgnoreCase("accept")) {
                if (args.length == 0) {
                    if (sender.getName().equalsIgnoreCase(player2.getName())) {
                        rebuildMap.rebuild();
                        inGame = true;
                        player1.sendMessage(ChatColor.GREEN + "Duel Starting...");
                        player2.sendMessage(ChatColor.GREEN + "Duel Starting...");
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run tp " + player1.getName() + redPlayerCoords);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run tp " + player2.getName() + bluePlayerCoords);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run spawnpoint " + player1.getName() + redSpawnCoords);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run spawnpoint " + player2.getName() + blueSpawnCoords);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamemode survival " + player1.getName());
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamemode survival " + player2.getName());
                        setInventory(player1);
                        setInventory(player2);


                        }
                    }
                }
            }


        if (inGame) {
            if (command.getName().equalsIgnoreCase("leave")) {
                if (args.length == 0) {
                    if (sender.getName().equalsIgnoreCase(player2.getName()) || sender.getName().equalsIgnoreCase(player1.getName()))
                        inGame = false;
                        player1Points = 0;
                        player2Points = 0;
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run tp " + player1.getName() + relearnSpawnCoords);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run tp " + player2.getName() + relearnSpawnCoords);
                        player1 = null;
                        player2 = null;
                }

            }
        }


        return true;

    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        if (inGame) {
            Player player = event.getPlayer();

            if (player.getName().equals(player1.getName())) {
                if (player1.getLocation().getY() < 40) {
                    // Player died in void
                    player1.setHealth(0);
                } else if (player1.getLocation().getBlock().getRelative(BlockFace.SELF).getType() == Material.BLUE_CARPET) {
                    // Player is in the goal
                    player1.sendMessage(ChatColor.GREEN + "You scored!");
                    player2.sendMessage(ChatColor.RED + player1.getName() + " scored!");
                    player1Points += 1;
                    player1.setHealth(0);
                    player2.setHealth(0);
                }
            } else if (player.getName().equals(player2.getName())) {
                if (player2.getLocation().getY() < 40) {
                    // Player died in void
                    player2.setHealth(0);
                } else if (player2.getLocation().getBlock().getRelative(BlockFace.SELF).getType() == Material.RED_CARPET) {
                    // Player is in the goal
                    player2.sendMessage(ChatColor.GREEN + "You scored!");
                    player1.sendMessage(ChatColor.RED + player2.getName() + " scored!");
                    player2Points += 1;
                    player1.setHealth(0);
                    player2.setHealth(0);
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
        if (inGame && (event.getPlayer().getName().equalsIgnoreCase(player1.getName()) || event.getPlayer().getName().equalsIgnoreCase(player2.getName()))) {
            if (event.getBlock().getType() == Material.IRON_BLOCK || event.getBlock().getType() == Material.BLUE_CARPET || event.getBlock().getType() == Material.LAPIS_BLOCK || event.getBlock().getType() == Material.RED_CARPET || event.getBlock().getType() == Material.REDSTONE_BLOCK) {
                event.getPlayer().sendMessage(ChatColor.RED + "You can't break this block!");
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (inGame && (event.getPlayer().getName().equalsIgnoreCase(player1.getName()) || event.getPlayer().getName().equalsIgnoreCase(player2.getName()))) {
            if (event.getBlock().getLocation().getX() < -17 || event.getBlock().getLocation().getX() > 17) {
                event.getPlayer().sendMessage(ChatColor.RED + "You can't place blocks here!");
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (inGame) {
            setInventory(Objects.requireNonNull(event.getEntity().getPlayer()));
        }
    }

    public void quitGame(Player playerEvent) {
        if (inGame) {
            if (playerEvent.getName().equals(player1.getName())) {
                inGame = false;
                player1Points = 0;
                player2Points = 0;
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run tp " + player2.getName() + relearnSpawnCoords);
                player1 = null;
                player2 = null;
            } else if (playerEvent.getName().equals(player2.getName())) {
                inGame = false;
                player1Points = 0;
                player2Points = 0;
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run tp " + player1.getName() + relearnSpawnCoords);
                player1 = null;
                player2 = null;
            }
        }
    }

    public void setInventory(Player player) {
        if (player.getName().equalsIgnoreCase(player1.getName())) {
            PlayerInventory player1Inventory= player1.getInventory();
            player1Inventory.clear();

            player1.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD, 1));
            player1.getInventory().setItem(1, new ItemStack(Material.BOW, 1));
            player1.getInventory().setItem(2, new ItemStack(Material.DIAMOND_PICKAXE, 1));
            player1.getInventory().setItem(3, new ItemStack(Material.RED_TERRACOTTA, 64));
            player1.getInventory().setItem(4, new ItemStack(Material.GOLDEN_APPLE, 8));
            player1.getInventory().setItem(7, new ItemStack(Material.RED_TERRACOTTA, 64));
            player1.getInventory().setItem(8, new ItemStack(Material.ARROW, 4));
        } else if (player.getName().equalsIgnoreCase(player2.getName())) {
            // If player 2 died
            PlayerInventory player2Inventory= player2.getInventory();
            player2Inventory.clear();
            player2.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD, 1));
            player2.getInventory().setItem(1, new ItemStack(Material.BOW, 1));
            player2.getInventory().setItem(2, new ItemStack(Material.GOLDEN_APPLE, 8));
            player2.getInventory().setItem(3, new ItemStack(Material.RED_WOOL, 64));
            player2.getInventory().setItem(7, new ItemStack(Material.RED_WOOL, 64));
            player2.getInventory().setItem(8, new ItemStack(Material.ARROW, 4));
        }
    }

}
