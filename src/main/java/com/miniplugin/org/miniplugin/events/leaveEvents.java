package com.miniplugin.org.miniplugin.events;

import com.miniplugin.org.miniplugin.singletons.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class leaveEvents implements Listener {
    player1Data player1 = player1Data.getInstance();
    player2Data player2 = player2Data.getInstance();

    player1Points player1Score = player1Points.getInstance();
    player2Points player2Score = player2Points.getInstance();

    inGameVar inGame = inGameVar.getInstance();

    final String relearnSpawnCoords = " -212 64 -253 90 0";

    @EventHandler
    public void onKick(PlayerKickEvent event) {

        quitGame(event.getPlayer());
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        quitGame(event.getPlayer());
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
