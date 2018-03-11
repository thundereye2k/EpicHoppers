package com.songoda.epichoppers.Hooks;

import com.songoda.epichoppers.EpicHoppers;
import com.songoda.epichoppers.Utils.Debugger;
import me.markeh.factionsframework.entities.FPlayer;
import me.markeh.factionsframework.entities.FPlayers;
import me.markeh.factionsframework.entities.Faction;
import me.markeh.factionsframework.entities.Factions;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by songoda on 3/17/2017.
 */
public class FactionsHook implements Hooks {

    private EpicHoppers plugin = EpicHoppers.pl();
    private String pluginName = "Factions";

    FactionsHook() {
        if (plugin.getServer().getPluginManager().getPlugin(pluginName) != null && plugin.getServer().getPluginManager().getPlugin("FactionsFramework") != null) {
            plugin.hooks.hooksFile.getConfig().addDefault("hooks." + pluginName, true);
            if (!plugin.hooks.hooksFile.getConfig().contains("hooks." + pluginName) || plugin.hooks.hooksFile.getConfig().getBoolean("hooks." + pluginName)) {
                plugin.hooks.FactionsHook = this;
            }
        }
    }

    @Override
    public boolean canBuild(Player p, Location location) {
        try {
            if (p.hasPermission(plugin.getDescription().getName() + ".bypass")) {
                return true;
            } else {
                FPlayer fp = FPlayers.getBySender(p);

                Faction faction = Factions.getFactionAt(location);

                if (fp.getFaction().equals(faction) || faction.isNone()) {
                    return true;
                }
            }
        } catch (Exception e) {
            Debugger.runReport(e);
        }
        return false;
    }

    @Override
    public boolean isInClaim(String id, Location location) {
        Faction faction = Factions.getFactionAt(location);

        if (faction.getId().equals(id)) {
            return true;
        }
        return false;
    }

    @Override
    public String getClaimId(String name) {
        try {
            Faction faction = Factions.getByName(name, "");

            return faction.getId();
        } catch (Exception e) {
        }
        return null;
    }

}
