package com.miniplugin.org.miniplugin;

import org.bukkit.entity.Player;

public class player1Data
{
    private static player1Data single_instance = null;

    public Player p;

    private player1Data()
    {
        p = null;
    }

    public static player1Data getInstance()
    {
        if (single_instance == null)
            single_instance = new player1Data();

        return single_instance;
    }
}
