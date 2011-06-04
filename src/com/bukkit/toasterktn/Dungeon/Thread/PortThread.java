package com.bukkit.toasterktn.Dungeon.Thread;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.bukkit.toasterktn.Dungeon.Dungeon;
import com.bukkit.toasterktn.Dungeon.Config.DungeonConfig;


public class PortThread implements Runnable{
    private Dungeon plugin;

    public PortThread(Dungeon instance) {
	this.plugin = instance;
    }

    public void run() {
	try {
	    for (Player p: plugin.getServer().getWorld(DungeonConfig.world).getPlayers()) {
		if (p.isOnline()) {
		  if (p.getLocation().getBlockX() < -33 && p.getLocation().getBlockX() > -38) {
		      if (p.getLocation().getBlockZ() < -33 && p.getLocation().getBlockZ() > -38) {
			  p.teleport(new Location(plugin.getServer().getWorld(DungeonConfig.world), plugin.genlarge.startx, 3, plugin.genlarge.starty));
		      }
		  }
		
		  if (p.getLocation().getBlockX() < -23 && p.getLocation().getBlockX() > -26) {
		      if (p.getLocation().getBlockZ() < -23 && p.getLocation().getBlockZ() > -26) {
			  p.teleport(new Location(plugin.getServer().getWorld(DungeonConfig.world), plugin.gennormal.startx, 43, plugin.gennormal.starty));
		      }
		  }
		  if (p.getLocation().getBlockX() < -33 && p.getLocation().getBlockX() > -38) {
		      if (p.getLocation().getBlockZ() < -23 && p.getLocation().getBlockZ() > -26) {
			  p.teleport(new Location(plugin.getServer().getWorld(DungeonConfig.world), plugin.gensmall.startx, 83, plugin.gensmall.starty));
		      }
		  }
		  if (p.getLocation().getBlockX() < -23 && p.getLocation().getBlockX() > -26) {
		      if (p.getLocation().getBlockZ() < -33 && p.getLocation().getBlockZ() > -38) {
			  p.teleport(new Location(plugin.getServer().getWorld(DungeonConfig.exitworld), DungeonConfig.exitx, DungeonConfig.exity, DungeonConfig.exitz));
			  plugin.oldplayerpos.remove(p);
		      }
		  }
		  
		  
		  if (p.getLocation().getBlockX() < plugin.genlarge.stopx+1 && p.getLocation().getBlockX() > plugin.genlarge.stopx-1) {
		      if (p.getLocation().getBlockZ() < plugin.genlarge.stopy+1 && p.getLocation().getBlockZ() > plugin.genlarge.stopy-1) {
			  if (p.getLocation().getBlockY() < 20) {
			      p.teleport(new Location(plugin.getServer().getWorld(DungeonConfig.world),-30, 4, -30));
			  }
		      }
		  }  
		  if (p.getLocation().getBlockX() < plugin.gennormal.stopx+1 && p.getLocation().getBlockX() > plugin.gennormal.stopx-1) {
		      if (p.getLocation().getBlockZ() < plugin.gennormal.stopy+1 && p.getLocation().getBlockZ() > plugin.gennormal.stopy-1) {
			  if (p.getLocation().getBlockY() > 20 && p.getLocation().getBlockY() < 60) {
			      p.teleport(new Location(plugin.getServer().getWorld(DungeonConfig.world),-30, 4, -30));
			  }
		      }
		  }
		  if (p.getLocation().getBlockX() < plugin.gensmall.stopx+1 && p.getLocation().getBlockX() > plugin.gensmall.stopx-1) {
		      if (p.getLocation().getBlockZ() < plugin.gensmall.stopy+1 && p.getLocation().getBlockZ() > plugin.gensmall.stopy-1) {
			  if (p.getLocation().getBlockY() > 80) {
			      p.teleport(new Location(plugin.getServer().getWorld(DungeonConfig.world),-30, 4, -30));
			  }
		      }
		  }
		  
		  
		}
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	}
    }
}
