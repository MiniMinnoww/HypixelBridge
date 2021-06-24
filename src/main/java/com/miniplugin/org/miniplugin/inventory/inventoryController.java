package com.miniplugin.org.miniplugin.inventory;

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
        } else if (player.getName().equalsIgnoreCase(Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getName())) {
            // If player 2 died
            PlayerInventory Inventory= Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getInventory();
            Inventory.clear();
            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getInventory().setItem(0, sword);
            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getInventory().setItem(1, new ItemStack(Material.BOW, 1));
            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getInventory().setItem(2, pick);
            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getInventory().setItem(3, new ItemStack(Material.BLUE_TERRACOTTA, 64));
            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getInventory().setItem(4, new ItemStack(Material.GOLDEN_APPLE, 8));
            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getInventory().setItem(7, new ItemStack(Material.BLUE_TERRACOTTA, 64));
            Objects.requireNonNull(Bukkit.getPlayer(playerData_.p.get("player2"))).getInventory().setItem(8, new ItemStack(Material.ARROW, 4));
        }
    }
}
