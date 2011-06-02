import com.bukkit.toasterktn.Dungeon.Generator.Generator;


public class Testen {

    /**
     * @param args
     */
    public static void main(String[] args) {
	Generator mygen = new Generator();
	mygen.setSize(100,100);
	mygen.init(1234);
	mygen.makeRooms();
	mygen.makeWays();
	try {
	    mygen.setStart();
	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println("No startpoint found");
	}
	//mygen.floodfill();
	mygen.debug();
	//mygen.debugdunmask();
    }
}
