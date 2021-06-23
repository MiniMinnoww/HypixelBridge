package com.miniplugin.org.miniplugin.events;

import com.miniplugin.org.miniplugin.inventory.inventoryController;
import com.miniplugin.org.miniplugin.singletons.inGameVar;
import com.miniplugin.org.miniplugin.singletons.player1Data;
import com.miniplugin.org.miniplugin.singletons.player2Data;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class deathEvent implements Listener {
    player1Data player1 = player1Data.getInstance();
    player2Data player2 = player2Data.getInstance();

    inGameVar inGame = inGameVar.getInstance();

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (inGame.i) {
            if (Objects.requireNonNull(event.getEntity().getPlayer()).getName().equalsIgnoreCase(player1.p.getName()) || Objects.requireNonNull(event.getEntity().getPlayer()).getName().equalsIgnoreCase(player2.p.getName())) {
                inventoryController.setInventory(Objects.requireNonNull(event.getEntity().getPlayer()));
            }
        }
    }
}
