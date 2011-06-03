package com.bukkit.toasterktn.Dungeon.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	public static int respawntime;
	public static int rarechance;
	public static List<String> lootables = new ArrayList<String>();
	public static List<String> lootablesrare = new ArrayList<String>();
	private static List<String> defaultlootablesrare = new ArrayList<String>();
	private static List<String> defaultlootables = new ArrayList<String>();
	public static int lootablecount;
	public static int lootstacksize;
	
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
	     	respawntime = config.getInt("respawntime", 50000);
	     	lootablecount = config.getInt("lootablecount", 5);
	  	lootstacksize = config.getInt("lootstacksize",10);
	        Integer[] deflotrares = {310,311,312,313,345,276};
	        for (int i = 0; i < deflotrares.length; i++) {
	        	defaultlootablesrare.add(deflotrares[i].toString()+",1");
			}
	        
	        Integer[] deflot = {50,50,50,50,50,50,267,261,306,307,308,309,262,262,262};
	        for (int i = 0; i < deflot.length; i++) {
	        	defaultlootables.add(deflot[i].toString()+"," + lootstacksize);
			}  
	     	lootables  = config.getStringList("lootables", defaultlootables);
	     	lootablesrare  = config.getStringList("lootablesrare", defaultlootablesrare);
	     	rarechance = config.getInt("rarechance", 70);
	     	
	     	
	     	config.save();
	    }
}
