package com.bukkit.toasterktn.Dungeon.Generator;

public class Room {

    private int roomnumber;
    private Point center;
    private Point start;
    private Point size;
    
    public Room(int startx, int starty, int sizex, int sizey, int rn) {
	this.center = new Point(startx + (sizex / 2), starty + (sizey / 2));
	this.start = new Point(startx,starty);
	this.roomnumber = rn;
	this.size = new Point(sizex, sizey);
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    public Point getCenter() {
        return center;
    }

    public Point getStart() {
        return start;
    }

    public Point getSize() {
        return size;
    }
}
