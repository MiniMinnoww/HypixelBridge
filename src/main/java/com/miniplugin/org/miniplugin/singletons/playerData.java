package com.miniplugin.org.miniplugin.singletons;

import java.util.HashMap;
import java.util.UUID;

public class playerData
{
    private static playerData single_instance = null;

    public HashMap<String, UUID> p;

    private playerData()
    {
        p = new HashMap<>();
        p.put("player1", null);
        p.put("player2", null);
    }

    public static playerData getInstance()
    {
        if (single_instance == null)
            single_instance = new playerData();

        return single_instance;
    }
}
