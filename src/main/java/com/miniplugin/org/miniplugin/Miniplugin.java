package com.miniplugin.org.miniplugin;

import com.miniplugin.org.miniplugin.commands.acceptCommand;
import com.miniplugin.org.miniplugin.commands.duelCommand;
import com.miniplugin.org.miniplugin.commands.leaveCommand;
import com.miniplugin.org.miniplugin.events.blockEvents;
import com.miniplugin.org.miniplugin.events.deathEvent;
import com.miniplugin.org.miniplugin.events.leaveEvents;
import com.miniplugin.org.miniplugin.events.moveEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Miniplugin extends JavaPlugin{
    final String prefix = "[MiniPlugin] ";

    @Override
    public void onEnable() {
        
        Objects.requireNonNull(getCommand("duel")).setExecutor(new duelCommand());
        Objects.requireNonNull(getCommand("accept")).setExecutor(new acceptCommand());
        Objects.requireNonNull(getCommand("leave")).setExecutor(new leaveCommand());

        Bukkit.getServer().getPluginManager().registerEvents(new deathEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new blockEvents(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new moveEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new leaveEvents(), this);


        System.out.println(ChatColor.GOLD + prefix + ChatColor.GREEN + "Initialised");

    }






}
