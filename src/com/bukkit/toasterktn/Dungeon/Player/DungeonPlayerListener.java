package com.bukkit.toasterktn.Dungeon.Player;


import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

import com.bukkit.toasterktn.Dungeon.Dungeon;
import com.bukkit.toasterktn.Dungeon.Config.DungeonConfig;
import com.bukkit.toasterktn.Dungeon.Generator.Treasure;

public class DungeonPlayerListener extends PlayerListener{
    private Dungeon plugin;
    public DungeonPlayerListener(Dungeon instance) {
	this.plugin = instance;
    }
    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
	if (event.isCancelled()) return;
	if (event.getPlayer() != null) {
	    try {
		if( event.getPlayer().getWorld().getName().equalsIgnoreCase(DungeonConfig.world)) {
		    if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			int x = event.getClickedBlock().getX();
			int y = event.getClickedBlock().getY();
			int z = event.getClickedBlock().getZ();
			for (Treasure t:plugin.gen.getTreasures()) {
			    t.SpawnBox(event.getPlayer().getWorld(),x, y, z);
			}
		    }
		}
	    } catch (Exception e) {
		// TODO: handle exception
	    }
	}
    }
}
