package com.miniplugin.org.miniplugin.commands;

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

public class duelCommand implements CommandExecutor {
    playerData playerData_ = playerData.getInstance();
    public player2Data player2 = player2Data.getInstance();
    public inGameVar inGame = inGameVar.getInstance();
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
        }
        return true;
    }
}