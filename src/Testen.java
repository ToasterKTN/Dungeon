import java.io.File;

import com.bukkit.toasterktn.Dungeon.Config.DungeonConfig;
import com.bukkit.toasterktn.Dungeon.Generator.Generator;


public class Testen {

    /**
     * @param args
     */
    public static void main(String[] args) {
	Generator mygen = new Generator();
	DungeonConfig.initialize(new File("/tmp/dummy"));
	mygen.setSize(100,100);
	mygen.init(12345);
	mygen.makeRooms(10);
	mygen.makeWays();
	mygen.removedeadend(3);
	try {
	    mygen.setStart();
	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println("No startpoint found");
	}
	mygen.chests(25);
	//mygen.floodfill();
	mygen.debug();
	//mygen.debugdunmask();
    }
}
