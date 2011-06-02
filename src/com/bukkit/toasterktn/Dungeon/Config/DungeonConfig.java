package com.bukkit.toasterktn.Dungeon.Config;

import java.io.File;

public class DungeonConfig {
	private static final String settingsFile = "SphereWorld.yml";
	public static String world;
	public static int worldseed;
	public static boolean autosavechunklist;
	public static int autosaveinterval;
	
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
	     	config.save();
	    }
}
