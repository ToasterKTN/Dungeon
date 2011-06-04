package com.bukkit.toasterktn.Dungeon.Chunk;

import java.util.Random;

import com.bukkit.toasterktn.Dungeon.Dungeon;
import com.bukkit.toasterktn.Dungeon.Config.DungeonConfig;
import com.bukkit.toasterktn.Dungeon.Generator.Generator;
//import com.bukkit.toasterktn.Dungeon.Thread.DungeonCreateLootThread;
import net.minecraft.server.BiomeBase;
import net.minecraft.server.Chunk;
import net.minecraft.server.IChunkProvider;
import net.minecraft.server.IProgressUpdate;
import net.minecraft.server.MapGenBase;
import net.minecraft.server.MapGenCaves;
import net.minecraft.server.World;

public class DungeonChunkProvider implements IChunkProvider {
    private Dungeon plugin;
    private Random r;
    private World p;
    private MapGenBase u = new MapGenCaves();
    private BiomeBase[] v;
    double[] d;
    double[] e;
    double[] f;
    double[] g;
    double[] h;
    int[][] i = new int[32][32];
 

    public DungeonChunkProvider(World world, long i, Dungeon instance) {
	this.p = world;
	this.r = new Random(i);
	this.plugin = instance;
	plugin.genlarge = new Generator();
	plugin.genlarge.setSize(DungeonConfig.genlarge, DungeonConfig.genlarge);
	plugin.genlarge.init(i);
	plugin.genlarge.makeRooms(DungeonConfig.maxroomsize);
	plugin.genlarge.makeWays();
	plugin.genlarge.removedeadend(DungeonConfig.removedeads);
	try {
	    plugin.genlarge.setStart();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	plugin.genlarge.chests(DungeonConfig.chestchance,0);	
	plugin.genlarge.makeSpawns(DungeonConfig.respawntime, 0);
	try {
	    plugin.genlarge.setStop();
	} catch (Exception e1) {
	    e1.printStackTrace();
	}
	
	plugin.gennormal = new Generator();
	plugin.gennormal.setSize(DungeonConfig.gennormal, DungeonConfig.gennormal);
	plugin.gennormal.init(i);
	plugin.gennormal.makeRooms(DungeonConfig.maxroomsize);
	plugin.gennormal.makeWays();
	plugin.gennormal.removedeadend(DungeonConfig.removedeads);
	try {
	    plugin.gennormal.setStart();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	plugin.gennormal.chests(DungeonConfig.chestchance, 40);	
	plugin.gennormal.makeSpawns(DungeonConfig.respawntime, 40);
	try {
	    plugin.gennormal.setStop();
	} catch (Exception e1) {
	    e1.printStackTrace();
	}
	
	
	plugin.gensmall = new Generator();
	plugin.gensmall.setSize(DungeonConfig.gensmall, DungeonConfig.gensmall);
	plugin.gensmall.init(i);
	plugin.gensmall.makeRooms(DungeonConfig.maxroomsize);
	plugin.gensmall.makeWays();
	plugin.gensmall.removedeadend(DungeonConfig.removedeads);
	try {
	    plugin.gensmall.setStart();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	plugin.gensmall.chests(DungeonConfig.chestchance, 80);	
	plugin.gensmall.makeSpawns(DungeonConfig.respawntime, 80);	
	try {
	    plugin.gensmall.setStop();
	} catch (Exception e1) {
	    e1.printStackTrace();
	}
	plugin.getServer().getWorld(DungeonConfig.world).setSpawnLocation(-30,4,-30);
    }

  
    public Chunk getChunkAt(int i, int j) {
	return this.getOrCreateChunk(i, j);
    }

    public Chunk getOrCreateChunk(int i, int j) {
	this.r.setSeed((long) i * 341873128712L + (long) j * 132897987541L);
	byte[] abyte = new byte['\u8000'];
	Chunk chunk = new Chunk(this.p, abyte, i, j);

	this.v = this.p.getWorldChunkManager().a(this.v, i * 16, j * 16, 16, 16);
	this.v = this.p.getWorldChunkManager().a(this.v, i * 16, j * 16, 16, 16);
	this.u.a(this, this.p, i, j, abyte);
	  	
	for (int k = 0; k < 16; ++k) {
	    for (int l = 0; l < 16; ++l) {
		int l1 = (l * 16 + k) * 128;
		abyte[l1] = (byte) 7;
		// **********   LARGE *******
		
		try {
		   byte bt = plugin.genlarge.dungeon[i * 16 + l][j * 16 + k];
		   abyte[(l * 16 + k) * 128 + 1] = 0x2D;
		   l1 = (l * 16 + k) * 128 + 2;
		   int l2 = (l * 16 + k) * 128 + 3;
		   int l3 = (l * 16 + k) * 128 + 4;
		   int l4 = (l * 16 + k) * 128 + 5;
		   int l5 = (l * 16 + k) * 128 + 6;
		   abyte[l5] = (byte) 0x16;
		   if (bt == Generator.wall) {
		       abyte[l1] = (byte) 0x30;
		       abyte[l2] = (byte) 0x30;
		       abyte[l3] = (byte) 0x30;
		       abyte[l4] = (byte) 0x30;
		   }
		   if (bt == Generator.blocked) {
		       abyte[l1] = (byte) 0x7;
		       abyte[l2] = (byte) 0x7;
		       abyte[l3] = (byte) 0x7;
		       abyte[l4] = (byte) 0x7;
		   }
		   if (bt == Generator.door) {
		       if (r.nextInt(5)==1) {
			   abyte[l1] = (byte) 0x40;
			   abyte[l2] = (byte) 0x40;
			   //p.setData(i*16+l, 4,j*16+k,0x08);
		       }
		       abyte[l3] = (byte) 0x30;
		       abyte[l4] = (byte) 0x30;
		   }
		   if (bt == Generator.way) {
		       abyte[l3] = (byte) 0x30;
		       abyte[l4] = (byte) 0x30;
		       if (r.nextInt(DungeonConfig.torchchance) == 1) abyte[l1] = 50;
		   }
		   if (bt == Generator.free) {
		       if (r.nextInt(DungeonConfig.torchchance) == 1) abyte[l1] = 50;
		   }
		   if (bt == Generator.chest) {
		       abyte[l1] = Generator.chest;
		       //plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new DungeonCreateLootThread(plugin,i * 16 + l,2,j * 16 + k),r.nextInt(1200));
		   }
		} catch (Exception e) {
		   // Nothing todo
		}
		
		
		// *********** NORMAL ********
		try {
			   byte bt = plugin.gennormal.dungeon[i * 16 + l][j * 16 + k];
			   l1 = (l * 16 + k) * 128 + 40;
			   abyte[l1] = (byte) 7;
			   abyte[(l * 16 + k) * 128 + 1+ 40] = 0x4;
			   l1 = (l * 16 + k) * 128 + 2+ 40;
			   int l2 = (l * 16 + k) * 128 + 3+ 40;
			   int l3 = (l * 16 + k) * 128 + 4+ 40;
			   int l4 = (l * 16 + k) * 128 + 5+ 40;
			   int l5 = (l * 16 + k) * 128 + 6+ 40;
			   abyte[l5] = (byte) 0x05;
			   if (bt == Generator.wall) {
			       abyte[l1] = (byte) 0x4;
			       abyte[l2] = (byte) 0x4;
			       abyte[l3] = (byte) 0x59;
			       abyte[l4] = (byte) 0x1;
			   }
			   if (bt == Generator.blocked) {
			       abyte[l1] = (byte) 0x7;
			       abyte[l2] = (byte) 0x7;
			       abyte[l3] = (byte) 0x7;
			       abyte[l4] = (byte) 0x7;
			   }
			   if (bt == Generator.door) {
			       if (r.nextInt(5)==1) {
				   abyte[l1] = (byte) 0x40;
				   abyte[l2] = (byte) 0x40;
				   //p.setData(i*16+l, 4,j*16+k,0x08);
			       }
			       abyte[l3] = (byte) 0x59;
			       abyte[l4] = (byte) 0x04;
			   }
			   if (bt == Generator.way) {
			       abyte[l3] = (byte) 0x04;
			       abyte[l4] = (byte) 0x04;
			       if (r.nextInt(DungeonConfig.torchchance) == 1) abyte[l1] = 50;
			   }
			   if (bt == Generator.free) {
			       if (r.nextInt(DungeonConfig.torchchance) == 1) abyte[l1] = 50;
			   }
			   if (bt == Generator.chest) {
			       abyte[l1] = Generator.chest;
			       //plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new DungeonCreateLootThread(plugin,i * 16 + l,2,j * 16 + k),r.nextInt(1200));
			   }
			} catch (Exception e) {
			   // Nothing todo
			}
			
			
			
			//************** SMALL *********
			
			try {
				   byte bt = plugin.gensmall.dungeon[i * 16 + l][j * 16 + k];
				   l1 = (l * 16 + k) * 128 + 80;
				   abyte[l1] = (byte) 7;
				   abyte[(l * 16 + k) * 128 + 1+ 80] = 0x2D;
				   l1 = (l * 16 + k) * 128 + 2 + 80;
				   int l2 = (l * 16 + k) * 128 + 3+ 80;
				   int l3 = (l * 16 + k) * 128 + 4+ 80;
				   int l4 = (l * 16 + k) * 128 + 5+ 80;
				   int l5 = (l * 16 + k) * 128 + 6+ 80;
				   abyte[l5] = (byte) 0x59;
				   if (bt == Generator.wall) {
				       abyte[l1] = (byte) 0x1;
				       abyte[l2] = (byte) 0x1;
				       abyte[l3] = (byte) 0x2B;
				       abyte[l4] = (byte) 0x1;
				   }
				   if (bt == Generator.blocked) {
				       abyte[l1] = (byte) 0x7;
				       abyte[l2] = (byte) 0x7;
				       abyte[l3] = (byte) 0x7;
				       abyte[l4] = (byte) 0x7;
				   }
				   if (bt == Generator.door) {
				       if (r.nextInt(5)==1) {
					   abyte[l1] = (byte) 0x40;
					   abyte[l2] = (byte) 0x40;
					   //p.setData(i*16+l, 4,j*16+k,0x08);
				       }
				       abyte[l3] = (byte) 0x2B;
				       abyte[l4] = (byte) 0x59;
				   }
				   if (bt == Generator.way) {
				       //abyte[l3] = (byte) 0x14;
				       abyte[l4] = (byte) 0x59;
				       if (r.nextInt(DungeonConfig.torchchance) == 1) abyte[l1] = 50;
				   }
				   if (bt == Generator.free) {
				       if (r.nextInt(DungeonConfig.torchchance) == 1) abyte[l1] = 50;
				   }
				   if (bt == Generator.chest) {
				       abyte[l1] = Generator.chest;
				       //plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new DungeonCreateLootThread(plugin,i * 16 + l,2,j * 16 + k),r.nextInt(1200));
				   }
				} catch (Exception e) {
				   // Nothing todo
				}	
		
	    } 
	}
	plugin.oldchunks.AddChunkToList(DungeonConfig.world,i,j);
	chunk.b();
	return chunk;
    }
	
    public boolean isChunkLoaded(int i, int j) {
	return true;
    }

    public void getChunkAt(IChunkProvider cj1, int i1, int j1) {
	getChunkAt(i1,j1);
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
	return true;
    }

    public boolean unloadChunks() {
	return false;
    }

    public boolean b() {
	return true;
    }
}
