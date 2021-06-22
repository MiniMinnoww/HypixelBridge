package com.miniplugin.org.miniplugin;

import org.bukkit.entity.Player;

public class player2Data
{
    private static player2Data single_instance = null;

    public Player p;

    private player2Data()
    {
        p = null;
    }

    public static player2Data getInstance()
    {
        if (single_instance == null)
            single_instance = new player2Data();

        return single_instance;
    }
}
