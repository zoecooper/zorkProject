import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GameState {

    public static class IllegalSaveFormatException extends Exception {
        public IllegalSaveFormatException(String e) {
            super(e);
        }
    }

    static String DEFAULT_SAVE_FILE = "zork_save";
    static String SAVE_FILE_EXTENSION = ".sav";
    static String SAVE_FILE_VERSION = "Zork III save data";

    static String CURRENT_ROOM_LEADER = "Current room: ";

    private static GameState theInstance;
    private Dungeon dungeon;
    private ArrayList<Item> inventory;
    private Room adventurersCurrentRoom;

    static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }

    private GameState() {
	    inventory = new ArrayList<Item>();
    }

    void restore(String filename) throws FileNotFoundException,
        IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException {

        Scanner s = new Scanner(new FileReader(filename));

        if (!s.nextLine().contains(SAVE_FILE_VERSION)) {
            throw new IllegalSaveFormatException("Save file not compatible.");
        }

        String dungeonFileLine = s.nextLine();

        if (!dungeonFileLine.startsWith(Dungeon.FILENAME_LEADER)) {
            throw new IllegalSaveFormatException("No '" +
                Dungeon.FILENAME_LEADER + 
                "' after version indicator.");
        }

        dungeon = new Dungeon(dungeonFileLine.substring(
            Dungeon.FILENAME_LEADER.length()), true);
        dungeon.restoreState(s);

        String currentRoomLine = s.nextLine();
        adventurersCurrentRoom = dungeon.getRoom(
            currentRoomLine.substring(CURRENT_ROOM_LEADER.length()));
    }

    void store() throws IOException {
        store(DEFAULT_SAVE_FILE);
    }

    void store(String saveName) throws IOException {
        String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
        w.println(SAVE_FILE_VERSION);
        dungeon.storeState(w);
        w.println(CURRENT_ROOM_LEADER + 
            getAdventurersCurrentRoom().getTitle());
        w.close();
    }

    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        adventurersCurrentRoom = dungeon.getEntry();
    }

    Room getAdventurersCurrentRoom() {
        return adventurersCurrentRoom;
    }

    void setAdventurersCurrentRoom(Room room) {
        adventurersCurrentRoom = room;
    }

    Dungeon getDungeon() {
        return dungeon;
    }

    ArrayList<Item> getInventory(){
	    return this.inventory;
    }
    Item getItemInVicinityNamed(String name) throws Item.NoItemException {
	    for (Item item : inventory) {
		    if (item.goesBy(name)) {
			    return item;
		    }
	    }
	    for (Item item : adventurersCurrentRoom.getContents()) {
		    if (item.goesBy(name)) {
			    return item;
		    }
	    }
	    throw new Item.NoItemException();
    }
    Item getItemFromInventoryNamed(String name){
	    for (int i = 0; i<inventory.size(); i++){
		    if(inventory.get(i).equals(name)){
			    return inventory.get(i);
		    }
		  
	    }
	    return null;
    }
    void addToInventory(Item item){
	    inventory.add(item);
    }
    void removeFromInventory(Item item){
	    inventory.remove(item);
    }
    ArrayList<String> getInventoryNames(){
	    ArrayList<String> invnamed = new ArrayList<String>();
	    for(int i = 0; i < inventory.size(); i++){
		    invnamed.add(inventory.get(i).getPrimaryName());
	    }
	    return invnamed;
    }



}
