package com.miniplugin.org.miniplugin.commands;

import com.miniplugin.org.miniplugin.inGameVar;
import com.miniplugin.org.miniplugin.player1Data;
import com.miniplugin.org.miniplugin.player2Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class duelCommand implements CommandExecutor {
    public player1Data player1 = player1Data.getInstance();
    public player2Data player2 = player2Data.getInstance();
    public inGameVar inGame = inGameVar.getInstance();
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
        }
        return true;
    }
}