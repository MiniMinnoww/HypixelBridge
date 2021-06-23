package com.miniplugin.org.miniplugin.singletons;


public class inGameVar
{
    private static inGameVar single_instance = null;

    public boolean i;

    private inGameVar()
    {
        i = false;
    }

    public static inGameVar getInstance()
    {
        if (single_instance == null)
            single_instance = new inGameVar();

        return single_instance;
    }
}
