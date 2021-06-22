package com.miniplugin.org.miniplugin.mapRebuilder;

import org.bukkit.Bukkit;

public class createSpawnAreas {
    public static void createBlueSpawn() {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill -21 69 2 -19 68 2 iron_block");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill -18 69 1 -18 68 -1 iron_block");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill -19 69 -2 -21 68 -2 iron_block");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill -22 69 -1 -22 68 1 iron_block");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill -19 68 1 -21 68 -1 iron_block");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill -21 69 -1 -19 69 1 blue_carpet");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill -21 72 1 -19 72 -1 iron_block");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill -20 72 0 -20 72 0 lapis_block");

    }

    public static void createRedSpawn() {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill 21 69 2 19 68 2 iron_block");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill 18 69 1 18 68 -1 iron_block");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill 19 69 -2 21 68 -2 iron_block");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill 22 69 -1 22 68 1 iron_block");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill 19 68 1 21 68 -1 iron_block");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill 21 69 -1 19 69 1 red_carpet");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill 21 72 1 19 72 -1 iron_block");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill 20 72 0 20 72 0 redstone_block");
    }
}
