package com.bukkit.toasterktn.Dungeon.Thread;

import org.bukkit.entity.Player;
import com.bukkit.toasterktn.Dungeon.Dungeon;
import com.bukkit.toasterktn.Dungeon.Config.DungeonConfig;
import com.bukkit.toasterktn.Dungeon.Generator.SpawnPoint;

public class SpawnThread implements Runnable {
    private Dungeon plugin;

    public SpawnThread(Dungeon instance) {
	this.plugin = instance;
    }

    public void run() {
	try {
	    for (Player p : plugin.getServer().getOnlinePlayers()) {
		if(p.getWorld().getName().equalsIgnoreCase(DungeonConfig.world)) {
		    double x = p.getLocation().getX();
		    double y = p.getLocation().getZ();
			    for (SpawnPoint s : plugin.gen.getSpawnPoints()) {
				s.TrySpawn(plugin.getServer(), x, y);
			    }
			}
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	}
    }
}
