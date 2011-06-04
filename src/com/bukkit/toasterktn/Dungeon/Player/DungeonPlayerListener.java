package com.bukkit.toasterktn.Dungeon.Player;


import org.bukkit.Location;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerRespawnEvent;

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
			if (event.getPlayer().getLocation().getBlockY() < 20)
			    for (Treasure t:plugin.genlarge.getTreasures()) 
				t.SpawnBox(event.getPlayer().getWorld(),x, y, z);
			if (event.getPlayer().getLocation().getBlockY() > 20 && event.getPlayer().getLocation().getBlockY() < 60 )
			    for (Treasure t:plugin.gennormal.getTreasures()) 
				t.SpawnBox(event.getPlayer().getWorld(),x, y, z);
			if (event.getPlayer().getLocation().getBlockY() > 60 )
			    for (Treasure t:plugin.gensmall.getTreasures()) 
				t.SpawnBox(event.getPlayer().getWorld(),x, y, z);
			
		    }
		}
	    } catch (Exception e) {
		// TODO: handle exception
	    }
	}
    }
    @Override
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        // TODO Auto-generated method stub
     if (event.getPlayer().getWorld().getName().equalsIgnoreCase(DungeonConfig.world)) event.setRespawnLocation(new Location(event.getPlayer().getServer().getWorld(DungeonConfig.world), -30, 4, -30));
    }
}
