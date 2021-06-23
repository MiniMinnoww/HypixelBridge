package com.miniplugin.org.miniplugin.singletons;


public class player1Points
{
    private static player1Points single_instance = null;

    public int p;

    private player1Points()
    {
        p = 0;
    }

    public static player1Points getInstance()
    {
        if (single_instance == null)
            single_instance = new player1Points();

        return single_instance;
    }
}
