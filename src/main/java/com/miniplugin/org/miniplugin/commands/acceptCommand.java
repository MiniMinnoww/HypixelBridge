package com.miniplugin.org.miniplugin.commands;

import com.miniplugin.org.miniplugin.inventory.inventoryController;
import com.miniplugin.org.miniplugin.mapRebuilder.rebuildMap;
import com.miniplugin.org.miniplugin.singletons.inGameVar;
import com.miniplugin.org.miniplugin.singletons.player2Data;
import com.miniplugin.org.miniplugin.singletons.playerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class acceptCommand implements CommandExecutor {
    playerData playerData_ = playerData.getInstance();
    public player2Data player2 = player2Data.getInstance();
    public inGameVar inGame = inGameVar.getInstance();
    final String blueSpawnCoords = " -20 73 0 -90";
    final String redSpawnCoords = " 20 73 0 90" ;
    final String bluePlayerCoords = " -20 73 0 -90 0";
    final String redPlayerCoords = " 20 73 0 90 0" ;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player sender_ = (Player) sender;
            if (!inGame.i) {
                if (command.getName().equalsIgnoreCase("duel")) {
                    if (args.length == 1) {
                        playerData_.p.replace("player1", sender_.getUniqueId());
                        

                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        player2.p = target;
                        if (target != null) {
                            target.sendMessage(ChatColor.BLUE + sender.getName() + ChatColor.WHITE + " has requested to duel you! Type /accept to duel!");
                            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).sendMessage(ChatColor.WHITE + "Duel request sent to " + ChatColor.RED + player2.p.getName());
                        }
                    }
                }
                if (command.getName().equalsIgnoreCase("accept")) {
                    if (args.length == 0) {
                        if (sender.getName().equalsIgnoreCase(player2.p.getName())) {
                            rebuildMap.rebuild();
                            inGame.i = true;
                            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).sendMessage(ChatColor.GREEN + "Duel Starting...");
                            player2.p.sendMessage(ChatColor.GREEN + "Duel Starting...");
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run tp " + Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getName() + redPlayerCoords);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run tp " + player2.p.getName() + bluePlayerCoords);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run spawnpoint " + Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getName() + redSpawnCoords);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run spawnpoint " + player2.p.getName() + blueSpawnCoords);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamemode survival " + Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getName());
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamemode survival " + player2.p.getName());
                            inventoryController.setInventory(Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))));
                            inventoryController.setInventory(player2.p);

                        }
                    }
                }
            }
            return true;
    }
}