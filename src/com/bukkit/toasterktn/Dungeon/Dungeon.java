package com.bukkit.toasterktn.Dungeon;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.bukkit.toasterktn.Dungeon.Chunk.ChunckList;
import com.bukkit.toasterktn.Dungeon.Chunk.ChunkListener;
import com.bukkit.toasterktn.Dungeon.Config.DungeonConfig;

public class Dungeon extends JavaPlugin {
    // Starts the class
    private ChunkListener chunkListener;
    public ChunckList oldchunks = new ChunckList();
    public boolean isGenerating = false;
    public File chunkfile;

    public static final Logger log = Logger.getLogger("Minecraft");

    @Override
    // When the plugin is disabled this method is called.
    public void onDisable() {
	oldchunks.WriteChunkList(chunkfile);
	System.out.println("[Dungeon] Disabled");
    }

    @Override
    // When the plugin is enabled this method is called.
    public void onEnable() {
	// Create the pluginmanage pm.
	DungeonConfig.initialize(getDataFolder());
	// Force Worldload
	if(getServer().getWorld(DungeonConfig.world) == null) {
	    getServer().createWorld(DungeonConfig.world, Environment.NORMAL, DungeonConfig.worldseed);
	}
	// Get Chunk data
	PluginManager pm = getServer().getPluginManager();
	PluginDescriptionFile pdfFile = this.getDescription();

	this.isGenerating = true;
	
	chunkfile = new File(getDataFolder(), "chunklist.data");
	oldchunks.ReadChunkList(chunkfile);
	chunkListener = new ChunkListener(this);
	
	this.isGenerating = false;
		
	// Register a Chunk Creation, we may want to add a Cache
	pm.registerEvent(Event.Type.CHUNK_LOAD, this.chunkListener, Event.Priority.Normal, this);
	// Kill Players on Floor
	
	// Initialize Autosave
	//if (DungeonConfig.autosavechunklist) {
	//    getServer().getScheduler().scheduleAsyncRepeatingTask(this, new SphereWorldSaveThread(this),1200 * DungeonConfig.autosaveinterval, 1200 * DungeonConfig.autosaveinterval);
	//}
	log.info("[Dungeon] version " + pdfFile.getVersion()
		+ " is enabled!");

	log.info("[Dungeon] Loaded");
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

	if (command.getName().equalsIgnoreCase("regeneratechunk")) {
	    if (!(sender instanceof Player)) {
		log.info("This command cannot be used in the console.");
		return true;
	    }
	    Player player = (Player) sender;
	    if (!player.isOp()) return false;
	    if (args.length >= 2) {
		try {
		    player.getWorld().regenerateChunk(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		    player.sendMessage("¤7Regenerated chunk at " + args[0]
			    + "," + args[1] + "!");
		    log.info(player.getName() + " regenerated chunk at "
			    + args[0] + "," + args[1] + "!");
		} catch (NumberFormatException n) {
		    player.sendMessage("¤cUnknown chunk coordinates!");
		}
	    } else {
		player.getWorld().regenerateChunk(player.getLocation().getBlock().getChunk().getX(), player.getLocation().getBlock().getChunk().getZ());
		player.sendMessage("¤7Regenerated chunk at "
			+ player.getLocation().getBlock().getChunk().getX()
			+ ","
			+ player.getLocation().getBlock().getChunk().getZ()
			+ "!");
		log.info(player.getName() + " regenerated chunk at "
			+ player.getLocation().getBlock().getChunk().getX()
			+ ","
			+ player.getLocation().getBlock().getChunk().getZ()
			+ "!");
	    }
	    return true;
	}
	return false;
    }

}