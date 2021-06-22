package com.miniplugin.org.miniplugin.mapRebuilder;

import org.bukkit.Bukkit;

public class clearArena {
    public static void clear() {
        for (int y = 30; y <= 256; y++) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:bridgetemplate run fill -23 " + y + " 22 22 " + y + " -23 air");
        }
    }
}
