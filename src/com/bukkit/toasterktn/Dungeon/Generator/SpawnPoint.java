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
 
    public SpawnPoint(int x, int y, int z, int respawn, CreatureType type, int count) {
	this.spawnpoint = new Point(x, y, z);
	this.lastspawn = 0;
	this.respawn = respawn;
	this.type = type;
	this.count = count;
    }
    public void TrySpawn(Server s, double x, double y, double z)
    {
	//System.out.println("TrySpawn");
	if (lastspawn + respawn < new Date().getTime()) {
	    if (spawnpoint.getX() + 15 > x && spawnpoint.getX() - 15 < x) {
		if (spawnpoint.getY() + 10 >  y && spawnpoint.getY() - 10 < y) {
		    if (spawnpoint.getZ() + 15 >  z && spawnpoint.getZ() - 15 < z) {		
			boolean didspawn=false;
			for (int k=0;k<count;k++)
			    didspawn=s.getWorld(DungeonConfig.world).spawnCreature(new Location(s.getWorld(DungeonConfig.world), this.spawnpoint.getX() -2 + r.nextInt(4), this.spawnpoint.getY() , this.spawnpoint.getZ() -2 + r.nextInt(4)), this.type) != null;
			if (didspawn) lastspawn = new Date().getTime();
		    }
		}
	    }
	}
    }
}
