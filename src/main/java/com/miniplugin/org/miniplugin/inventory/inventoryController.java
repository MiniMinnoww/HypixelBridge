package com.miniplugin.org.miniplugin.inventory;

import com.miniplugin.org.miniplugin.singletons.player2Data;
import com.miniplugin.org.miniplugin.singletons.playerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Objects;

public class inventoryController {

    public static void setInventory(Player player) {
        playerData playerData_ = playerData.getInstance();
        player2Data player2 = player2Data.getInstance();
        ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
        sword.addEnchantment(Enchantment.KNOCKBACK, 1);
        ItemStack pick = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        pick.addEnchantment(Enchantment.DIG_SPEED, 3);
        if (player.getName().equalsIgnoreCase(Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getName())) {

            PlayerInventory Inventory= Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getInventory();
            Inventory.clear();


            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getInventory().setItem(0, sword);
            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getInventory().setItem(1, new ItemStack(Material.BOW, 1));
            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getInventory().setItem(2, pick);
            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getInventory().setItem(3, new ItemStack(Material.RED_TERRACOTTA, 64));
            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getInventory().setItem(4, new ItemStack(Material.GOLDEN_APPLE, 8));
            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getInventory().setItem(7, new ItemStack(Material.RED_TERRACOTTA, 64));
            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player1"))).getInventory().setItem(8, new ItemStack(Material.ARROW, 4));
        } else if (player.getName().equalsIgnoreCase(player2.p.getName())) {
            // If player 2 died
            PlayerInventory Inventory= player2.p.getInventory();
            Inventory.clear();
            player2.p.getInventory().setItem(0, sword);
            player2.p.getInventory().setItem(1, new ItemStack(Material.BOW, 1));
            player2.p.getInventory().setItem(2, pick);
            player2.p.getInventory().setItem(3, new ItemStack(Material.BLUE_TERRACOTTA, 64));
            player2.p.getInventory().setItem(4, new ItemStack(Material.GOLDEN_APPLE, 8));
            player2.p.getInventory().setItem(7, new ItemStack(Material.BLUE_TERRACOTTA, 64));
            player2.p.getInventory().setItem(8, new ItemStack(Material.ARROW, 4));
        }
    }
}
