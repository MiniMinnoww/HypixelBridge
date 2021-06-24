package com.miniplugin.org.miniplugin.commands;

import com.miniplugin.org.miniplugin.singletons.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class leaveCommand implements CommandExecutor {
    playerData playerData_ = playerData.getInstance();
    player1Points player1Score = player1Points.getInstance();
    player2Points player2Score = player2Points.getInstance();
    inGameVar inGame = inGameVar.getInstance();
    final String relearnSpawnCoords = " -212 64 -253 90 0";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (inGame.i) {
            if (command.getName().equalsIgnoreCase("leave")) {
                if (args.length == 0) {
                    if (sender.getName().equalsIgnoreCase(Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getName()) || sender.getName().equalsIgnoreCase(Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getName()))
                        inGame.i = false;
                    player1Score.p = 0;
                    player2Score.p = 0;
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run tp " + Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getName() + relearnSpawnCoords);
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run tp " + Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getName() + relearnSpawnCoords);
                    playerData_.p.replace("player1", null);
                    playerData_.p.replace("player2", null);
                }

            }
        }


        return true;

    }
}
