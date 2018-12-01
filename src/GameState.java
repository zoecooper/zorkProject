
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * <tt>GameState<tt> delivers a way for the user to save its current progress in the
 *  game and restore the player to the last saved progress.
 *  @author NC 
 */

public class GameState {

    public static class IllegalSaveFormatException extends Exception {
        public IllegalSaveFormatException(String e) {
            super(e);
        }
    }
    static String DEFAULT_SAVE_FILE = "zork_sav"; 	
    static String SAVE_FILE_EXTENSION = ".sav";
    static String SAVE_FILE_VERSION = "Zork++ save data";

    static String ADVENTURER_MARKER = "Adventurer:";
    static String CURRENT_ROOM_LEADER = "Current room: ";
    static String INVENTORY_LEADER = "Inventory: ";

    private int health;
    private int score;
    private static GameState theInstance;
    private Dungeon dungeon;
    private ArrayList<Item> inventory;
    private Room adventurersCurrentRoom;

    /**
     * Singleton creating an instance of itself and returing that instance to be called on anywhere
     * throughout the different classes.
     */ 
    static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }
    /**
     * Returns the player's current health.
     */ 
   int getHealth() {
	    return health;
    }
    /**
     * Subtracts the points necessary to increase the player's current health.
     */ 
    void subtractHealth(int n){
	    this.health -= n;
    }
     /**
      * Return's the player's current score.
      */
    int getScore(){
	    return score;
    }
    /**
     * Adds the points necessary to increase the player's current score.
     */
    void addToScore(int j){
           this.score += j;
    }
	    

    /** 
     * Constructor instantiating the inventory ArrayList for <tt>Item<tt>'s.
     */ 
    private GameState() {
        inventory = new ArrayList<Item>();
    }
    /** 
     * Adds up the weight of each item in the player's inventory and returns the
     * total weight of the inventory.
     */ 
    int getAdventurersCurrentWeight() {
        int total = 0;
        for (Item item : inventory) {
            total += item.getWeight();
        }
        return total;
    }
    /**
     *Reads lines in the save file allowing the player to revive the  previously 
     saved state in the game so that the player may return to where he/she last saved
     as long as the save file is compatible. Restore calls many other restore methods to read 
     corresponding parts of the file.
     * @param filename name of save file the user is playing the game with.
     * @throws FileNotFoundException
     * @throws IllegalDungeonFormatException
     * @throws IllegalSaveFormatException
     */ 
    void restore(String filename) throws FileNotFoundException,
        IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException {

        Scanner s = new Scanner(new FileReader(filename));

        if (!s.nextLine().equals(SAVE_FILE_VERSION)) {
            throw new IllegalSaveFormatException("Save file not compatible.");
        }

        String dungeonFileLine = s.nextLine();

        if (!dungeonFileLine.startsWith(Dungeon.FILENAME_LEADER)) {
            throw new IllegalSaveFormatException("No '" +
                Dungeon.FILENAME_LEADER + 
                "' after version indicator.");
        }

        dungeon = new Dungeon(dungeonFileLine.substring(
            Dungeon.FILENAME_LEADER.length()), false);
        dungeon.restoreState(s);

        s.nextLine();  // Throw away "Adventurer:".
        String currentRoomLine = s.nextLine();
        adventurersCurrentRoom = dungeon.getRoom(
            currentRoomLine.substring(CURRENT_ROOM_LEADER.length()));
        if (s.hasNext()) {
            String inventoryList = s.nextLine().substring(
                INVENTORY_LEADER.length());
            String[] inventoryItems = inventoryList.split(",");
            for (String itemName : inventoryItems) {
                try {
                    addToInventory(dungeon.getItem(itemName));
                } catch (Item.NoItemException e) {
                    throw new IllegalSaveFormatException("No such item '" +
                        itemName + "'");
                }
            }
        }
    }
    void store() throws IOException {
	    store(DEFAULT_SAVE_FILE);
    }

    /** Saves the player's current state in the game to a save file so that the player 
     * may return back to the game in the same state saved.
     * @param saveName the filename the the current state of game is saved to
     * @throws IOException
     */ 
    void store(String saveName) throws IOException {
        String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
        w.println(SAVE_FILE_VERSION);
        dungeon.storeState(w);
        w.println(ADVENTURER_MARKER);
        w.println(CURRENT_ROOM_LEADER + adventurersCurrentRoom.getTitle());
        if (inventory.size() > 0) {
            w.print(INVENTORY_LEADER);
            for (int i=0; i<inventory.size()-1; i++) {
                w.print(inventory.get(i).getPrimaryName() + ",");
            }
            w.println(inventory.get(inventory.size()-1).getPrimaryName());
        }
        w.close();
    }

    /**
     * Sets the players current room to the entry of the dungeon being currently played.
     * @param dungeon
     */ 
    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        adventurersCurrentRoom = dungeon.getEntry();
    }
    /**
     * Returns all names of the items listed in the player's inventory.
     */ 
    ArrayList<String> getInventoryNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Item item : inventory) {
            names.add(item.getPrimaryName());
        }
        return names;
    }
 
    /**
     * Adds an item to the player's inventory.
     * @param item -the item that will be added to the inventory
     */ 
    void addToInventory(Item item) /* throws TooHeavyException */ {
        inventory.add(item);
    }

    /**
     * Removes an item from the player's inventory.
     * @param item the item that will be added to the inventory
     */
    void removeFromInventory(Item item) {
        inventory.remove(item);
    }

    /** 
     * Returns the primary name of a given item in the current room the player is in if the name or alias of the item aligns wiith the primary name.
     * @param name of the item
     * @throws NoItemException
     */  
    Item getItemInVicinityNamed(String name) throws Item.NoItemException {

        // First, check inventory.
        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        // Next, check room contents.
        for (Item item : adventurersCurrentRoom.getContents()) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        throw new Item.NoItemException();
    }

    /** 
     * Returns the primary name of a given item in the player's inventory if the given name aligns with the primary name.
     * @param name of the item
     * @throws NoItemException
     */
    Item getItemFromInventoryNamed(String name) throws Item.NoItemException {

        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }
        throw new Item.NoItemException();
    }


    /**
     * Returns the player's current room.
     */ 
    Room getAdventurersCurrentRoom() {
        return adventurersCurrentRoom;
    }

    /**
     * Sets the current room of the player.
     * @param room
     */
    void setAdventurersCurrentRoom(Room room) {
        adventurersCurrentRoom = room;
    }

    /**
     * Returns the current dungeon.
     */
    Dungeon getDungeon() {
        return dungeon;
    }


}
