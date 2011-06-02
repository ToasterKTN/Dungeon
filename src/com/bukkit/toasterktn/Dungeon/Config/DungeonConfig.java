package com.bukkit.toasterktn.Dungeon.Config;

import java.io.File;

public class DungeonConfig {
	private static final String settingsFile = "Dungeon.yml";
	public static String world;
	public static int worldseed;
	public static boolean autosavechunklist;
	public static int autosaveinterval;
	public static int maxroomsize;
	public static int worldsize;
	public static int removedeads;
	public static int roomchance;
	public static int chestchance;
	public static int torchchance;
	
	public static void initialize(File dataFolder) {
		
	        if(!dataFolder.exists()) {
	            dataFolder.mkdirs();
	        }
	        
	        File configFile  = new File(dataFolder, settingsFile);
	        BetterConfig config = new BetterConfig(configFile);
	        config.load();
	     	
	     	world  = config.getString("world", "sphere");
		worldseed = config.getInt("worldseed", 12345);
	     	autosavechunklist = config.getBoolean("autosavechunklist", true);
	     	autosaveinterval = config.getInt("autosaveinterval", 30);
	     	maxroomsize = config.getInt("maxroomsize", 20);
	     	worldsize = config.getInt("worldsize", 4000);
	     	removedeads = config.getInt("removedeads", 5);
	     	roomchance = config.getInt("roomchance", 80);
	     	chestchance = config.getInt("chestchance", 5);
	     	torchchance = config.getInt("torchchance", 50);
	     	config.save();
	    }
}
