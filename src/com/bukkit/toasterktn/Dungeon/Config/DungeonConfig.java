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
	public static int genlarge;
	public static int gennormal;
	public static int gensmall;
	public static int removedeads;
	public static int roomchance;
	public static int chestchance;
	public static int torchchance;
	public static int respawntime;
	public static int rarechance;
	public static String exitworld;
	public static int exitx;
	public static int exity;
	public static int exitz;
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
	     	exitworld  = config.getString("exitworld", "world");
	     	exitx = config.getInt("exitx",0);
	     	exity = config.getInt("exity",70);
	     	exitz = config.getInt("exitz",0);
		worldseed = config.getInt("worldseed", 12345);
	     	autosavechunklist = config.getBoolean("autosavechunklist", true);
	     	autosaveinterval = config.getInt("autosaveinterval", 30);
	     	maxroomsize = config.getInt("maxroomsize", 20);
	     	genlarge = config.getInt("genlarge", 1000);
	     	gennormal = config.getInt("gennormal", 500);
	     	gensmall = config.getInt("gensmall", 200);
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
