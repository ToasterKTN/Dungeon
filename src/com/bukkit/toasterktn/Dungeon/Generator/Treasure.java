package com.bukkit.toasterktn.Dungeon.Generator;

import java.util.Date;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.bukkit.toasterktn.Dungeon.Config.DungeonConfig;

public class Treasure {
    private Point loc;
    private long lastspawn;
    private int respawn;
    private Random r;

    public Treasure(int x, int y, int z, int respawn) {
	this.loc = new Point(x, y, z);
	this.lastspawn = 0;
	this.respawn = respawn;
	this.r = new Random();
    }

    public void SpawnBox(World w, int x, int y, int z) {
	if (loc.getX() == x && loc.getY() == y && loc.getZ() == z) {
	    if (lastspawn + respawn < new Date().getTime()) {
		lastspawn = new Date().getTime();
		Chest c = (Chest) w.getBlockAt(x, y, z).getState();
		Inventory i = c.getInventory();
		for (int count = 0; count < r.nextInt(DungeonConfig.lootablecount); count++) {

		    try {
			String[] item = DungeonConfig.lootables.get(r.nextInt(DungeonConfig.lootables.size())).split(",");
			if (item[1] != null) {
			    i.addItem(new ItemStack(Material.getMaterial(Integer.parseInt(item[0])), Integer.parseInt(item[1])));
			} else {
			    i.addItem(new ItemStack(Material.getMaterial(Integer.parseInt(item[0])), DungeonConfig.lootstacksize));
			}
		    } catch (Exception e) {
			System.out.print("Error in the lootables Section.. Check the Config");
		    }
		}
		try {
		    for (int j = 0; j < DungeonConfig.rarechance; j++) {
			String[] rare = DungeonConfig.lootablesrare.get(r.nextInt(DungeonConfig.lootablesrare.size())).split(",");
			if (rare[1] != null) {
			    if (r.nextInt(100) == 1)
				i.addItem(new ItemStack(Material.getMaterial(Integer.parseInt(rare[0])), Integer.parseInt(rare[1])));
			} else {
			    if (r.nextInt(100) == 1)
				i.addItem(new ItemStack(Material.getMaterial(Integer.parseInt(rare[0])), 1));
			}
		    }
		} catch (Exception e) {
		    System.out.print("Error in the lootablesrare Section.. Check the Config");
		}

	    }
	}
    }
}
