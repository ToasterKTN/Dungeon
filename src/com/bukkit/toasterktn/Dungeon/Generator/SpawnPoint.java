package com.bukkit.toasterktn.Dungeon.Generator;

import java.util.Date;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.CreatureType;

import com.bukkit.toasterktn.Dungeon.Config.DungeonConfig;

public class SpawnPoint {
    private Point spawnpoint;
    private long lastspawn;
    private int respawn;
    private CreatureType type;
    private int count;
    private Random r = new Random();
 
    public SpawnPoint(int x, int y, int respawn, CreatureType type, int count) {
	this.spawnpoint = new Point(x, y);
	this.lastspawn = 0;
	this.respawn = respawn;
	this.type = type;
	this.count = count;
    }
    public void TrySpawn(Server s, double x, double y)
    {
	//System.out.println("TrySpawn");
	if (lastspawn + respawn < new Date().getTime()) {
	    if (spawnpoint.getX() + 15 > x && spawnpoint.getX() - 15 < x) {
		if (spawnpoint.getY() + 15 >  y && spawnpoint.getY() - 15 < y) {
		    if (spawnpoint.getX() + 20 < s.getWorld(DungeonConfig.world).getSpawnLocation().getX() || spawnpoint.getX() - 20 > s.getWorld(DungeonConfig.world).getSpawnLocation().getX() || spawnpoint.getY() + 20 < s.getWorld(DungeonConfig.world).getSpawnLocation().getZ() || spawnpoint.getY() - 20 > s.getWorld(DungeonConfig.world).getSpawnLocation().getZ()) {
			
			boolean didspawn=false;
			for (int k=0;k<count;k++)
			    didspawn=s.getWorld(DungeonConfig.world).spawnCreature(new Location(s.getWorld(DungeonConfig.world), this.spawnpoint.getX() -2 + r.nextInt(4), 2, this.spawnpoint.getY() -2 + r.nextInt(4)), this.type) != null;
			if (didspawn) lastspawn = new Date().getTime();
		    }
		}
	    }
	}
    }
}
