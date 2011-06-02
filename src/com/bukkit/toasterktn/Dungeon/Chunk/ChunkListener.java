package com.bukkit.toasterktn.Dungeon.Chunk;


import java.util.Iterator;
import java.util.List;

import net.minecraft.server.IChunkProvider;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.world.WorldListener;
import org.bukkit.event.world.ChunkLoadEvent;

import com.bukkit.toasterktn.Dungeon.Dungeon;
import com.bukkit.toasterktn.Dungeon.Config.DungeonConfig;

public class ChunkListener extends WorldListener{
	public static Dungeon plugin;
	private IChunkProvider scp;

	public ChunkListener(Dungeon instance) {
		plugin = instance;
		World world = plugin.getServer().getWorld(DungeonConfig.world); 
		scp = new DungeonChunkProvider(((CraftWorld)world).getHandle(), 0L, plugin);
		((CraftWorld)world).getHandle().chunkProviderServer.chunkProvider = scp;
		
		if (plugin.oldchunks.thisoldchunks.size() < 10) {
		    System.out.println("First run..");
		    System.out.print("working.. please wait.. this may take several minutes");
		    try {
		     	Thread.sleep(15000);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		    Chunk chunks[] = world.getLoadedChunks();
		    for(int ci = 0; ci < chunks.length; ci++)
		    {
		     Chunk c = chunks[ci];
		     world.regenerateChunk(c.getX(),c.getZ());
		     if(ci % 50 == 0)
		          System.gc();
		    }
		    try {
		     	Thread.sleep(10000);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		    chunks = null;
		    chunks = world.getLoadedChunks();
		    System.out.print("working.. Second pass");
		    for(int ci = 0; ci < chunks.length; ci++)
		    {
		     Chunk c = chunks[ci];
		     world.regenerateChunk(c.getX(),c.getZ());
		     if(ci % 50 == 0)
		          System.gc();
		    }
		    try {
		     	Thread.sleep(10000);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		    chunks = null;
		    chunks = world.getLoadedChunks();
		    System.out.print("working.. Third pass");
		    for(int ci = 0; ci < chunks.length; ci++)
		    {
		     Chunk c = chunks[ci];
		     world.regenerateChunk(c.getX(),c.getZ());
		     ((World)plugin.getServer().getWorlds().get(0)).unloadChunk(c.getX(), c.getZ());
		     chunks[ci] = null;
		     if(ci % 50 == 0)
		          System.gc();
		    }
		    List<Entity> entities = world.getEntities();
		    Entity e;
		    for(Iterator<Entity> iterator = entities.iterator(); iterator.hasNext(); e.remove())
			e = (Entity)iterator.next();
		}
	};
	
	
	public void onChunkLoad(ChunkLoadEvent event) {
		if (event.getType().equals(Event.Type.CHUNK_LOAD)) {
		    // Test if this world is a SphereWorld..
		    if (DungeonConfig.world.equalsIgnoreCase(event.getWorld().getName())) {
			    if (!plugin.oldchunks.isInChunkList(event.getChunk())) {
				event.getWorld().regenerateChunk(event.getChunk().getX(), event.getChunk().getZ());
			   }
		    }
		}
	}
}