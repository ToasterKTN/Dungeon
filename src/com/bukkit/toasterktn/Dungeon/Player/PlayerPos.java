package com.bukkit.toasterktn.Dungeon.Player;

import com.bukkit.toasterktn.Dungeon.Generator.Point;

public class PlayerPos {
private String playername;
private Point oldPos;
private String worldname;
	public PlayerPos(String playername,String worldname, int x, int y, int z) {
	    this.playername=playername;
	    this.oldPos = new Point(x, y, z);
	    this.worldname = worldname;
	    // TODO Auto-generated constructor stub
	}
	public boolean isPlayer(String name) {
	    if (this.playername.equalsIgnoreCase(name)) return true;
	    return false;
	}
	public int getX()
	{
	    return this.oldPos.getX();
	}
	public int getY()
	{
	    return this.oldPos.getY();
	}
	public int getZ()
	{
	    return this.oldPos.getZ();
	}
	public String getWorldName()
	{
	    return this.worldname;
	}
	
}
