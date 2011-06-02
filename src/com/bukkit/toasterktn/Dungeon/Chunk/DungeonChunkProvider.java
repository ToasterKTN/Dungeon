package com.bukkit.toasterktn.Dungeon.Chunk;

import java.util.Random;

import com.bukkit.toasterktn.Dungeon.Dungeon;
import com.bukkit.toasterktn.Dungeon.Config.DungeonConfig;
import com.bukkit.toasterktn.Dungeon.Generator.Generator;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.Chunk;
import net.minecraft.server.IChunkProvider;
import net.minecraft.server.IProgressUpdate;
import net.minecraft.server.MapGenBase;
import net.minecraft.server.MapGenCaves;
import net.minecraft.server.NoiseGeneratorOctaves;
import net.minecraft.server.World;

public class DungeonChunkProvider implements IChunkProvider {
    private Dungeon plugin;
    private Random r;
    public NoiseGeneratorOctaves a;
    public NoiseGeneratorOctaves b;
    public NoiseGeneratorOctaves c;
    private World p;
    private MapGenBase u = new MapGenCaves();
    private BiomeBase[] v;
    private Generator gen = null;
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
	gen = new Generator();
	gen.setSize(1000, 1000);
	gen.init(i);
	gen.makeRooms();
	gen.makeWays();
	try {
	    gen.setStart();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
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
		try {
		   byte bt = gen.dungeon[i * 16 + l][j * 16 + k];
		   l1 = (l * 16 + k) * 128 + 1;
		   int l2 = (l * 16 + k) * 128 + 2;
		   int l3 = (l * 16 + k) * 128 + 3;
		   int l4 = (l * 16 + k) * 128 + 4;
		   abyte[l4] = (byte) 0x14;
		   if (bt == Generator.wall) {
		       abyte[l1] = (byte) 0x1;
		       abyte[l2] = (byte) 0x1;
		       abyte[l3] = (byte) 0x1;
		   }
		   if (bt == Generator.blocked) {
		       abyte[l1] = (byte) 0x7;
		       abyte[l2] = (byte) 0x7;
		       abyte[l3] = (byte) 0x7;
		   }
		   if (bt == Generator.door) {
		       if (r.nextInt(5)==1) {
			   abyte[l1] = (byte) 0x40;
			   abyte[l2] = (byte) 0x40;
			   p.setData(i*16+l, 4,j*16+k,0x08);
		       }
		       abyte[l3] = (byte) 0x1;
		   }
		   if (bt == Generator.way) {
		       abyte[l3] = (byte) 0x14;
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
