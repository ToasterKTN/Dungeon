package com.bukkit.toasterktn.Dungeon.Block;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.bukkit.toasterktn.Dungeon.Config.DungeonConfig;

public class DungeonBlockListener extends BlockListener{
    @Override
    public void onBlockBreak(BlockBreakEvent event) {
       if (event.isCancelled()) return;
       if (event.getBlock().getWorld().getName().equalsIgnoreCase(DungeonConfig.world)) {
	   if (!event.getBlock().getType().equals(Material.TORCH)) event.setCancelled(true);
       }
    }
    @Override
    public void onBlockPlace(BlockPlaceEvent event) {
	 if (event.isCancelled()) return;
	 if (event.getBlock().getWorld().getName().equalsIgnoreCase(DungeonConfig.world)) {
	     if (!event.getBlock().getType().equals(Material.TORCH)) event.setCancelled(true);
	 }
    }
}
