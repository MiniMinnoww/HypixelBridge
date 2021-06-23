package com.miniplugin.org.miniplugin.commands;

import com.miniplugin.org.miniplugin.singletons.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class leaveCommand implements CommandExecutor {
    player1Data player1 = player1Data.getInstance();
    player2Data player2 = player2Data.getInstance();
    inGameVar inGame = inGameVar.getInstance();
    player1Points player1Score = player1Points.getInstance();
    player2Points player2Score = player2Points.getInstance();
    final String relearnSpawnCoords = " -212 64 -253 90 0";
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
}
