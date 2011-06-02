package com.bukkit.toasterktn.Dungeon.Thread;

//import org.bukkit.block.Block;
import java.util.Random;

import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.bukkit.toasterktn.Dungeon.Dungeon;
import com.bukkit.toasterktn.Dungeon.Config.DungeonConfig;

public class DungeonCreateLootThread implements Runnable{
    private Dungeon plugin;
    private int x,y,z;
    Random r;
    public DungeonCreateLootThread(Dungeon instance, int x, int y, int z) {
	this.x = x;
	this.y = y;
	this.z = z;
	this.plugin = instance;
	r= new Random();
    } 

    public void run()
    {
	//Block b =  (Block) plugin.getServer().getWorld(DungeonConfig.world).getBlockAt(x, y, z);
	try {
	Chest c = (Chest) plugin.getServer().getWorld(DungeonConfig.world).getBlockAt(x, y, z).getState();
	Inventory i = c.getInventory();
	i.clear();
	int k = r.nextInt(4);
	if (k == 0) {
	    i.addItem(new ItemStack(320,r.nextInt(5))); 
	    i.addItem(new ItemStack(260,r.nextInt(5))); 
	    i.addItem(new ItemStack(322,r.nextInt(2))); 
	    i.addItem(new ItemStack(257,r.nextInt(3))); 
	    i.addItem(new ItemStack(50,r.nextInt(64)));  
	}
	if (k == 1) {
	    i.addItem(new ItemStack(267 ,1)); 
	    i.addItem(new ItemStack(299,1)); 
	    i.addItem(new ItemStack(305,1)); 
	    i.addItem(new ItemStack(314,1)); 
	    i.addItem(new ItemStack(262,r.nextInt(30)));  
	    i.addItem(new ItemStack(50,r.nextInt(64)));  
	}
	if (k == 2) {
	    i.addItem(new ItemStack(261 ,1)); 
	    i.addItem(new ItemStack(272,1)); 
	    i.addItem(new ItemStack(312,1)); 
	    i.addItem(new ItemStack(262,r.nextInt(30)));  
	    i.addItem(new ItemStack(50,r.nextInt(64)));  
	}
	if (k == 3) {
	    i.addItem(new ItemStack(311 ,1)); 
	    i.addItem(new ItemStack(313,1)); 
	    i.addItem(new ItemStack(317,1)); 
	    i.addItem(new ItemStack(262,r.nextInt(30)));  
	    i.addItem(new ItemStack(50,r.nextInt(64)));  
	}
	} catch (Exception e) {
	    // TODO: handle exception
	}
    }
}
