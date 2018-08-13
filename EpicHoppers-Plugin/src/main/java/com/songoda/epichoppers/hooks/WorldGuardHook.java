package com.songoda.epichoppers.hooks;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.songoda.epichoppers.EpicHoppersPlugin;
import com.songoda.epichoppers.utils.Debugger;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by songoda on 3/17/2017.
 */
public class WorldGuardHook extends Hook {

    private EpicHoppersPlugin plugin = EpicHoppersPlugin.getInstance();

    public WorldGuardHook() {
        super("WorldGuard");
        if (isEnabled())
            plugin.hooks.WorldGuardHook = this;
    }

    @Override
    public boolean canBuild(Player p, Location location) {
        try {
            return p.hasPermission(plugin.getDescription().getName() + ".bypass") || WorldGuardPlugin.inst().canBuild(p, location);
        } catch (Exception e) {
            Debugger.runReport(e);
        }
        return false;
    }
}