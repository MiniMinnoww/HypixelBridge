package com.miniplugin.org.miniplugin.inventory;

import com.miniplugin.org.miniplugin.inGameVar;
import com.miniplugin.org.miniplugin.player1Data;
import com.miniplugin.org.miniplugin.player2Data;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class inventoryController {

    public static void setInventory(Player player) {
        player1Data player1 = player1Data.getInstance();
        player2Data player2 = player2Data.getInstance();
        inGameVar inGame = inGameVar.getInstance();
        ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
        sword.addEnchantment(Enchantment.KNOCKBACK, 1);
        if (player.getName().equalsIgnoreCase(player1.p.getName())) {

            PlayerInventory Inventory= player1.p.getInventory();
            Inventory.clear();


            player1.p.getInventory().setItem(0, sword);
            player1.p.getInventory().setItem(1, new ItemStack(Material.BOW, 1));
            player1.p.getInventory().setItem(2, new ItemStack(Material.DIAMOND_PICKAXE, 1));
            player1.p.getInventory().setItem(3, new ItemStack(Material.RED_TERRACOTTA, 64));
            player1.p.getInventory().setItem(4, new ItemStack(Material.GOLDEN_APPLE, 8));
            player1.p.getInventory().setItem(7, new ItemStack(Material.RED_TERRACOTTA, 64));
            player1.p.getInventory().setItem(8, new ItemStack(Material.ARROW, 4));
        } else if (player.getName().equalsIgnoreCase(player2.p.getName())) {
            // If player 2 died
            PlayerInventory Inventory= player2.p.getInventory();
            Inventory.clear();
            player2.p.getInventory().setItem(0, sword);
            player2.p.getInventory().setItem(1, new ItemStack(Material.BOW, 1));
            player2.p.getInventory().setItem(2, new ItemStack(Material.GOLDEN_APPLE, 8));
            player2.p.getInventory().setItem(3, new ItemStack(Material.RED_WOOL, 64));
            player2.p.getInventory().setItem(7, new ItemStack(Material.RED_WOOL, 64));
            player2.p.getInventory().setItem(8, new ItemStack(Material.ARROW, 4));
        }
    }
}
