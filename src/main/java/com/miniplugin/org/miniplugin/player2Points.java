package com.miniplugin.org.miniplugin;


public class player2Points
{
    private static player2Points single_instance = null;

    public int p;

    private player2Points()
    {
        p = 0;
    }

    public static player2Points getInstance()
    {
        if (single_instance == null)
            single_instance = new player2Points();

        return single_instance;
    }
}
