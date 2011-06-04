package com.bukkit.toasterktn.Dungeon.Entity;

import org.bukkit.entity.CreatureType;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityListener;

import com.bukkit.toasterktn.Dungeon.Config.DungeonConfig;

public class DungeonEntityListener extends EntityListener{

    public void onCreatureSpawn(CreatureSpawnEvent event) {
	if (event.isCancelled()) return;
	if (event.getLocation().getWorld().getName().equalsIgnoreCase(DungeonConfig.world))
	    if (event.getCreatureType() == CreatureType.SLIME) event.setCancelled(true);
	    if (event.getLocation().getBlockX() < 0)  event.setCancelled(true);
	    if (event.getLocation().getBlockZ() < 0)  event.setCancelled(true);
    }
}
