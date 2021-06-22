package com.miniplugin.org.miniplugin.mapRebuilder;

import org.bukkit.Bukkit;

public class createBridges {
    public static void createBlueBridge() {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill -15 69 0 -1 63 0 blue_terracotta");
    }

    public static void createRedBridge() {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill 15 63 0 1 69 0 red_terracotta");
    }

    public static void createWhiteBridge() {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill 0 69 0 0 63 0 white_terracotta");
    }
}
