package com.bukkit.toasterktn.Dungeon.Block;

import org.bukkit.World;

import com.bukkit.toasterktn.Dungeon.Generator.Generator;

public class DungeonCreateLounge {
    public static void CreateLounge(World w , Generator g1, Generator g2, Generator g3) {
	for (int x = -20 ; x >= -40 ; x--)
	    for (int y = -20 ; y >= -40 ; y--)
		for (int k=1 ; k < 11; k++)
		    w.getBlockAt(x , k, y).setTypeId(0x0);
	for (int x = -20 ; x >= -40 ; x--)
	    for (int y = -20 ; y >= -40 ; y--)
		w.getBlockAt(x , 1, y).setTypeId(0x3);
	for (int x = -20 ; x >= -40 ; x--)
	    for (int y = -20 ; y >= -40 ; y--)
		w.getBlockAt(x , 2, y).setTypeId(0x30);
	for (int x = -20 ; x >= -40 ; x--) {
		w.getBlockAt(x , 3, -20).setTypeId(0x31);
		w.getBlockAt(x , 4, -20).setTypeId( 0x31);
		w.getBlockAt(x , 5, -20).setTypeId( 0x31);
		w.getBlockAt(x , 6, -20).setTypeId( 0x31);
		w.getBlockAt(x , 7, -20).setTypeId( 0x31);
		w.getBlockAt(x , 8, -20).setTypeId( 0x31);
		w.getBlockAt(x , 9, -20).setTypeId( 0x31);
		w.getBlockAt(x , 3, -40).setTypeId( 0x31);
		w.getBlockAt(x , 4, -40).setTypeId( 0x31);
		w.getBlockAt(x , 5, -40).setTypeId( 0x31);
		w.getBlockAt(x , 6, -40).setTypeId( 0x31);
		w.getBlockAt(x , 7, -40).setTypeId( 0x31);
		w.getBlockAt(x , 8, -40).setTypeId( 0x31);
		w.getBlockAt(x , 9, -40).setTypeId( 0x31);
	}
	for (int x = -20 ; x >= -40 ; x--) {
		w.getBlockAt(-20 , 3, x).setTypeId( 0x31);
		w.getBlockAt(-20 , 4, x).setTypeId( 0x31);
		w.getBlockAt(-20 , 5, x).setTypeId( 0x31);
		w.getBlockAt(-20 , 6, x).setTypeId( 0x31);
		w.getBlockAt(-20 , 7, x).setTypeId( 0x31);
		w.getBlockAt(-20 , 8, x).setTypeId( 0x31);
		w.getBlockAt(-20 , 9, x).setTypeId( 0x31);
		w.getBlockAt(-40 , 3, x).setTypeId( 0x31);
		w.getBlockAt(-40 , 4, x).setTypeId( 0x31);
		w.getBlockAt(-40 , 5, x).setTypeId( 0x31);
		w.getBlockAt(-40 , 6, x).setTypeId( 0x31);
		w.getBlockAt(-40 , 7, x).setTypeId( 0x31);
		w.getBlockAt(-40 , 8, x).setTypeId( 0x31);
		w.getBlockAt(-40 , 9, x).setTypeId( 0x31);
	}
	
	for (int x = -22 ; x >= -26 ; x--)
	    for (int y = -22 ; y >= -26 ; y--)
		w.getBlockAt(x , 3, y).setTypeId( 0x29);
	for (int x = -34 ; x >= -38 ; x--)
	    for (int y = -22 ; y >= -26 ; y--)
		w.getBlockAt(x , 3, y).setTypeId( 0x2A);
	for (int x = -34 ; x >= -38 ; x--)
	    for (int y = -34 ; y >= -38 ; y--)
		w.getBlockAt(x , 3, y).setTypeId( 0x39);
	for (int x = -22 ; x >= -26 ; x--)
	    for (int y = -34 ; y >= -38 ; y--)
		w.getBlockAt(x , 3, y).setTypeId( 0x01);
	for (int x = -20 ; x >= -40 ; x--)
	    for (int y = -20 ; y >= -40 ; y--)
		w.getBlockAt(x , 10, y).setTypeId(0x59);

	w.getBlockAt(-22 , 4, -22).setTypeId(0x32);
	w.getBlockAt(-26 , 4, -22).setTypeId(0x32);
	w.getBlockAt(-22 , 4, -26).setTypeId(0x32);
	w.getBlockAt(-26 , 4, -26).setTypeId(0x32);
	
	w.getBlockAt(-34 , 4, -34).setTypeId(0x32);
	w.getBlockAt(-34 , 4, -38).setTypeId(0x32);
	w.getBlockAt(-38 , 4, -34).setTypeId(0x32);
	w.getBlockAt(-38 , 4, -38).setTypeId(0x32);
	
	w.getBlockAt(-34 , 4, -22).setTypeId(0x32);
	w.getBlockAt(-34 , 4, -26).setTypeId(0x32);
	w.getBlockAt(-38 , 4, -22).setTypeId(0x32);
	w.getBlockAt(-38 , 4, -26).setTypeId(0x32);
	
	w.getBlockAt(-22 , 4, -34).setTypeId(0x32);
	w.getBlockAt(-22 , 4, -38).setTypeId(0x32);
	w.getBlockAt(-26 , 4, -34).setTypeId(0x32);
	w.getBlockAt(-26 , 4, -38).setTypeId(0x32);
	
	for (int x = -1 ; x <= 1 ; x++) {
	    for (int z = -1 ; z <= 1 ; z++) {
		w.getBlockAt( g1.stopx + x, 1, g1.stopy + z).setTypeId( 0x31);	
		w.getBlockAt( g2.stopx + x, 41, g2.stopy + z).setTypeId( 0x31);	
		w.getBlockAt( g3.stopx + x, 81, g3.stopy + z).setTypeId( 0x31);	
	    }	
    	}
    
    	w.getBlockAt(g1.stopx, 1, g1.stopy ).setTypeId( 0x39);	
	w.getBlockAt(g2.stopx, 41, g2.stopy).setTypeId( 0x39);	
	w.getBlockAt(g3.stopx, 81, g3.stopy ).setTypeId( 0x39);	
    }
}
