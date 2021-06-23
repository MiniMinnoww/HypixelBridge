package com.miniplugin.org.miniplugin.commands;

import com.miniplugin.org.miniplugin.singletons.inGameVar;
import com.miniplugin.org.miniplugin.mapRebuilder.rebuildMap;
import com.miniplugin.org.miniplugin.singletons.player1Data;
import com.miniplugin.org.miniplugin.singletons.player2Data;
import com.miniplugin.org.miniplugin.inventory.inventoryController;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class acceptCommand implements CommandExecutor {
    public player1Data player1 = player1Data.getInstance();
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
                        player1.p = sender_;

                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        player2.p = target;
                        if (target != null) {
                            target.sendMessage(ChatColor.BLUE + sender.getName() + ChatColor.WHITE + " has requested to duel you! Type /accept to duel!");
                            player1.p.sendMessage(ChatColor.WHITE + "Duel request sent to " + ChatColor.RED + player2.p.getName());
                        }
                    }
                }
                if (command.getName().equalsIgnoreCase("accept")) {
                    if (args.length == 0) {
                        if (sender.getName().equalsIgnoreCase(player2.p.getName())) {
                            rebuildMap.rebuild();
                            inGame.i = true;
                            player1.p.sendMessage(ChatColor.GREEN + "Duel Starting...");
                            player2.p.sendMessage(ChatColor.GREEN + "Duel Starting...");
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run tp " + player1.p.getName() + redPlayerCoords);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run tp " + player2.p.getName() + bluePlayerCoords);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run spawnpoint " + player1.p.getName() + redSpawnCoords);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run spawnpoint " + player2.p.getName() + blueSpawnCoords);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamemode survival " + player1.p.getName());
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamemode survival " + player2.p.getName());
                            inventoryController.setInventory(player1.p);
                            inventoryController.setInventory(player2.p);

                        }
                    }
                }
            }
            return true;
    }
}