package com.miniplugin.org.miniplugin.events;

import com.miniplugin.org.miniplugin.inventory.inventoryController;
import com.miniplugin.org.miniplugin.singletons.inGameVar;
import com.miniplugin.org.miniplugin.singletons.playerData;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class deathEvent implements Listener {
    playerData playerData_ = playerData.getInstance();

    inGameVar inGame = inGameVar.getInstance();

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (inGame.i) {
            if (Objects.requireNonNull(event.getEntity().getPlayer()).getName().equalsIgnoreCase(Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getName()) || Objects.requireNonNull(event.getEntity().getPlayer()).getName().equalsIgnoreCase(Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getName())) {
                inventoryController.setInventory(Objects.requireNonNull(event.getEntity().getPlayer()));
            }
        }
    }
}
