package com.bukkit.toasterktn.Dungeon.Generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.CreatureType;

import com.bukkit.toasterktn.Dungeon.Config.DungeonConfig;

public class Generator {
    public static byte wall = 0x1;
    public static byte free = 0x0;
    public static byte door = 0x2;
    public static byte blocked = 0x8;
    public static byte way = 0x3;
    public static byte chest = 0x36;
    public static byte unmasked = 0x1;
    public static byte masked = 0x2;
    private int x, y;
    public byte[][] dungeon = null;
    public byte[][] dunmask = null;
    private Random r = null;
    private List<String> dirs = null;
    private List<Room> rooms = null; 
    private List<SpawnPoint> spawns = null; 
    private List<Treasure> treasures = null;
    public int startx, starty;

    public void setSize(int x, int y) {
	this.x = x;
	this.y = y;
	this.dungeon = new byte[x][y];
	this.dunmask = new byte[x][y];
	this.rooms = new ArrayList<Room>();
	this.spawns = new ArrayList<SpawnPoint>();
	this.treasures = new ArrayList<Treasure>();
    }

    public void init(long seed) {
	for (int x = 0; x < this.x; x++) {
	    for (int y = 0; y < this.y; y++) {
		this.dungeon[x][y] = wall;
		this.dunmask[x][y] = unmasked;
		if (x == 0 || x == this.x - 1 || y == 0 || y == this.y - 1) {
		    this.dungeon[x][y] = blocked;
		    this.dunmask[x][y] = masked;
		}
		 
		
	    }
	}
	r = new Random(seed);
	dirs = new ArrayList<String>();
	dirs.add("N");
	dirs.add("E");
	dirs.add("S");
	dirs.add("W");
    }

    public void makeRooms(int maxsize) {
	for (int x = 1; x < this.x - 1; x = x + 2) {
	    for (int y = 1; y < this.y - 1; y = y + 2) {
		if (r.nextInt(100) <= DungeonConfig.roomchance) {
		    int h = ((r.nextInt(maxsize/2) + 1) * 2) + 5;
		    int v = ((r.nextInt(maxsize/2) + 1) * 2) + 5;
		    if (hasRoomSpace(x, y, h, v))
			placeRoom(x, y, h, v);
		}
	    }
	}
    }
    public void makeSpawns(int respawn) {
	for (Room ro:rooms) {
	    if (r.nextInt(5)==1) spawns.add(new SpawnPoint(ro.getCenter().getX(), ro.getCenter().getY(), respawn, CreatureType.ZOMBIE, 5));
	    if (r.nextInt(6)==1) spawns.add(new SpawnPoint(ro.getCenter().getX(), ro.getCenter().getY(), respawn, CreatureType.PIG_ZOMBIE, 5));
	    if (r.nextInt(5)==1) spawns.add(new SpawnPoint(ro.getCenter().getX(), ro.getCenter().getY(), respawn, CreatureType.SKELETON, 5));
	    if (r.nextInt(8)==1) spawns.add(new SpawnPoint(ro.getCenter().getX(), ro.getCenter().getY(), respawn, CreatureType.SPIDER, 5));
	}
    }

    public List<SpawnPoint> getSpawnPoints() {
	return this.spawns;
    }
    public List<Treasure> getTreasures() {
	return this.treasures;
    }
    
    private boolean hasRoomSpace(int x, int y, int h, int v) {
	try {
	    for (int x1 = x -2 ; x1 < x + h +4 ; x1++) {
		for (int y1 = y -2; y1 < y + v +4; y1++) {
		    if (this.dungeon[x1][y1] != wall)
			return false;
		}
	    }
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    private void placeRoom(int x, int y, int h, int v) {
	rooms.add(new Room(x, y, h, v, 0));
	for (int x1 = x; x1 < x + h; x1++) {
	    for (int y1 = y; y1 < y + v; y1++) {
		this.dungeon[x1][y1] = free;
	    }
	}
	for (int x1 = 0; x1 < 2 + r.nextInt((h + v) / 5 + 1); x1++) {
	    // Make Doors
	    int tryplace = 50;
	    while (tryplace > 0) {
		tryplace--;
		int i = r.nextInt(4);
		if (i == 0) {
		    // Try north
		    int k1 = r.nextInt((h - 4) / 2 ) * 2 + x +2;
		    if (isfreecell(k1, y - 1)) {
			setDoor(k1, y - 1);
			tryplace = 0;
		    }
		}
		if (i == 1) {
		    // Try east
		    int k1 = r.nextInt((v - 4) / 2) * 2 + y +2;
		    if (isfreecell(x + h, k1)) {
			setDoor(x + h, k1);
			tryplace = 0;
		    }
		}
		if (i == 2) {
		    // Try south
		    int k1 = r.nextInt((h -4) / 2) * 2 + x +2;
		    if (isfreecell(k1, y + v)) {
			setDoor(k1, y + v);
			tryplace = 0;
		    }
		}
		if (i == 3) {
		    // Try west
		    int k1 = r.nextInt((v -4) / 2) * 2 + y +2;
		    if (isfreecell(x - 1, k1)) {
			setDoor(x - 1, k1);
			tryplace = 0;
		    }
		}
	    }
	}
    }

    public void makeWays() {
	for (int x = 1; x < this.x - 1; x = x + 2) {
	    for (int y = 1; y < this.y - 1; y = y + 2) {
		Collections.shuffle(dirs);
		if (isfreecell(x, y))
		    makeWaysPart(x, y);
	    }
	}
    }

    public void makeWaysPart(int x, int y) {
	//Collections.shuffle(dirs);
	for (String dir : dirs) {
	    // Stillfree try make a Corridor
	    if (dir.equalsIgnoreCase("E")) {
		if (isfreecell(x + 2, y) || isway(x + 2, y)) {
		    // its free make a waypart
		    setWay(x, y);
		    setWay(x + 1, y);
		    if (isway(x + 2, y)) return;
		    setWay(x + 2, y);
		    makeWaysPart(x + 2, y);
		    return;
		}
	    }
	    if (dir.equalsIgnoreCase("S")) {
		if (isfreecell(x, y + 2) || isway(x, y + 2)) {
		    // its free make a waypart
		    setWay(x, y);
		    setWay(x, y + 1);
		    if (isway(x , y + 2)) return;
		    setWay(x, y + 2);
		    makeWaysPart(x, y + 2);
		    return;
		}
	    }
	    if (dir.equalsIgnoreCase("W")) {
		if (isfreecell(x - 2, y) || isway(x - 2, y)) {
		    // its free make a waypart
		    setWay(x, y);
		    setWay(x - 1, y);
		    if (isway(x - 2, y)) return;
		    setWay(x - 2, y);
		    makeWaysPart(x - 2, y);
		    return;
		}
	    }
	    if (dir.equalsIgnoreCase("N")) {
		if (isfreecell(x, y - 2) || isway(x, y - 2)) {
		    // its free make a waypart
		    setWay(x, y);
		    setWay(x, y - 1);
		    if (isway(x , y - 2)) return;
		    setWay(x, y - 2);
		    makeWaysPart(x, y - 2);
		    return;
		}
	    }
	}
    }

    public void removedeadend(int passes) {
	for (int p=0; p < passes; p++ ) {
	for (int x = 1; x < this.x - 1;x++) {
	    for (int y = 1; y < this.y - 1; y++) {
		if (isway(x, y)) {
		    int count=0;
		    if (isused(x-1, y)) count++;
		    if (isused(x+1, y)) count++;
		    if (isused(x, y+1)) count++;
		    if (isused(x, y-1)) count++;
		    if (count >= 3) {
			setWall(x, y);
		    }
		}
	    }
	}
	}
    }
    
    private void setDoor(int x, int y) {
	dungeon[x][y] = door;
    }

    private void setWay(int x, int y) {
	dungeon[x][y] = way;
    }
    private void setWall(int x, int y) {
	dungeon[x][y] = wall;
    }
    private void setChest(int x, int y) {
	dungeon[x][y] = chest;
	treasures.add(new Treasure(x, 2, y, DungeonConfig.respawntime));
    }
    
    private boolean istype(int x, int y, byte type) {
	try {
	    if (dungeon[x][y] == type)
		return true;
	    return false;
	} catch (Exception e) {
	    return false;
	}
    }
    
    private boolean isfreecell(int x, int y) {
	return istype(x, y, wall);
    }
    private boolean isroom(int x, int y) {
	return istype(x, y, free);
    }
    private boolean isway(int x, int y) {
	return istype(x, y, way);
    }
    @SuppressWarnings("unused")
    private boolean iswall(int x, int y) {
	return istype(x, y, wall);
    }
    private boolean isdoor(int x, int y) {
	return istype(x, y, door);
    }
    private boolean isused(int x, int y) {
	    if (istype(x, y, wall) || istype(x, y, blocked) || istype(x, y, chest))
		return true;
	    return false;
    }
    
    public void setStart() throws Exception {
	int c = 0;
	while(c < 100) {
	    startx = r.nextInt(this.x);
	    starty = r.nextInt(this.y);
	    if (dungeon[startx][starty] == free) return;
	    c++;
	}
	throw new Exception("No startpoint found");
    }
    
    public void floodfill() {
	List<Point> pl = new ArrayList<Point>();
	pl.add(new Point(startx, starty));
	while(pl.size() > 0) {
	    // set mask for this point
	    //debugdunmask();
	    int x = pl.get(0).getX();
	    int y = pl.get(0).getY();
	    
	    if (dunmask[x-1][y]==unmasked && (dungeon[x-1][y]==free || dungeon[x-1][y]==door || dungeon[x-1][y]==way)) {
		dunmask[x-1][y]=masked;
		pl.add(new Point(x-1, y));
	    }
	    if (dunmask[x+1][y]==unmasked && (dungeon[x+1][y]==free || dungeon[x+1][y]==door || dungeon[x+1][y]==way)){
		dunmask[x+1][y]=masked;
		pl.add(new Point(x+1, y));
	    }
	    if (dunmask[x][y-1]==unmasked && (dungeon[x][y-1]==free || dungeon[x][y-1]==door || dungeon[x][y-1]==way)){
		dunmask[x][y-1]=masked;
		pl.add(new Point(x, y-1));
	    }
	    if (dunmask[x][y+1]==unmasked && (dungeon[x][y+1]==free || dungeon[x][y+1]==door || dungeon[x][y+1]==way)) {
		dunmask[x][y+1]=masked;
		pl.add(new Point(x, y+1));
	    }
	    pl.remove(0);
	    //System.out.println(pl.size());
	} 
    }
    
    public void chests(int chance) 
    {
	for (int x = 1; x < this.x - 1;x++) {
	    for (int y = 1; y < this.y - 1; y++) {
		if (isroom(x, y)) {
		    int count=0;
		    if (isroom(x-1, y)) count++;
		    if (isroom(x+1, y)) count++;
		    if (isroom(x, y+1)) count++;
		    if (isroom(x, y-1)) count++;
		    if (isdoor(x-1, y)) count=0;
		    if (isdoor(x+1, y)) count=0;
		    if (isdoor(x, y+1)) count=0;
		    if (isdoor(x, y-1)) count=0;
		    if (count == 3) {
			if (r.nextInt(1000) < chance) setChest(x, y);
		    }
		}
	    }
	}
    }
    
    public void debugdunmask() {
	for (int x = 0; x < this.x; x++) {
	    for (int y = 0; y < this.y; y++) {
		System.out.print(this.dunmask[x][y] + " ");
	    }
	    System.out.println();
	}
    }
    
    public void debug() {
	for (int x = 0; x < this.x; x++) {
	    for (int y = 0; y < this.y; y++) {
		if (this.dungeon[x][y]  == wall) System.out.print("##");
		if (this.dungeon[x][y]  == free) System.out.print("  ");
		if (this.dungeon[x][y]  == blocked) System.out.print("**");
		if (this.dungeon[x][y]  == way) System.out.print("..");
		if (this.dungeon[x][y]  == door) System.out.print("__");
		if (this.dungeon[x][y]  == chest) System.out.print("[]");
	    }
	    System.out.println();
	}
    }
}
