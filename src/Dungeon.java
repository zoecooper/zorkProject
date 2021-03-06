
import java.util.Hashtable;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * A <tt>Dungeon</tt> represents the area the player is in and holds rooms, exits, characters, and items.
 * @author ZC
 */
public class Dungeon {

    public static class IllegalDungeonFormatException extends Exception {
        public IllegalDungeonFormatException(String e) {
            super(e);
        }
    }

    // Variables relating to both dungeon file and game state storage.
    public static String TOP_LEVEL_DELIM = "===";
    public static String SECOND_LEVEL_DELIM = "---";

    // Variables relating to dungeon file (.zork) storage.
    public static String ROOMS_MARKER = "Rooms:";
    public static String EXITS_MARKER = "Exits:";
    public static String ITEMS_MARKER = "Items:";
    public static String CHARACTERS_MARKER = "Characters:";
    
    // Variables relating to game state (.sav) storage.
    static String FILENAME_LEADER = "Dungeon file: ";
    static String ROOM_STATES_MARKER = "Room states:";

    private String name;
    private Room entry;
    private Hashtable<String,Room> rooms;
    private Hashtable<String,Item> items;
    //characters hashtable
    private Hashtable<String,Character> characters;
    private String filename;

    Dungeon(String name, Room entry) {
        init();
        this.filename = null;    // null indicates not hydrated from file.
        this.name = name;
        this.entry = entry;
        rooms = new Hashtable<String,Room>();
    }

    /**
     * Read from the .zork filename passed, and instantiate a Dungeon object
     * based on it.
     */
    public Dungeon(String filename) throws FileNotFoundException, 
        IllegalDungeonFormatException {

        this(filename, true);
    }

    /**
     * Read from the .zork filename passed, and instantiate a Dungeon object
     * based on it, including (possibly) the items in their original locations.
     */
    public Dungeon(String filename, boolean initState) 
        throws FileNotFoundException, IllegalDungeonFormatException {

        init();
        this.filename = filename;

        Scanner s = new Scanner(new FileReader(filename));
        name = s.nextLine();

        s.nextLine();   // Throw away version indicator.

        // Throw away delimiter.
        if (!s.nextLine().equals(TOP_LEVEL_DELIM)) {
            throw new IllegalDungeonFormatException("No '" +
                TOP_LEVEL_DELIM + "' after version indicator.");
        }

        // Throw away Items starter.
        if (!s.nextLine().equals(ITEMS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                ITEMS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate items.
            while (true) {
                add(new Item(s));
            }
        } catch (Item.NoItemException e) {  /* end of items */ }

        // Throw away Rooms starter.
        if (!s.nextLine().equals(ROOMS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                ROOMS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate and add first room (the entry).
            entry = new Room(s, this, initState);
            add(entry);

            // Instantiate and add other rooms.
            while (true) {
                add(new Room(s, this, initState));
            }
        } catch (Room.NoRoomException e) {  /* end of rooms */ }

        // Throw away Exits starter.
        if (!s.nextLine().equals(EXITS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                EXITS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate exits.
            while (true) {
                Exit exit = new Exit(s, this);
            }
        } catch (Exit.NoExitException e) {  /* end of exits */ }

        s.close();
    }
    
    // Common object initialization tasks, regardless of which constructor
    // is used.
    private void init() {
        rooms = new Hashtable<String,Room>();
        items = new Hashtable<String,Item>();
    }

    /**
     * Store the current (changeable) state of this dungeon to the writer
     * passed.
     */
    void storeState(PrintWriter w) throws IOException {
        w.println(FILENAME_LEADER + getFilename());
        w.println(ROOM_STATES_MARKER);
        for (Room room : rooms.values()) {
            room.storeState(w);
        }
        w.println(TOP_LEVEL_DELIM);
    }

    /**
     * Restore the (changeable) state of this dungeon to that reflected in the
     * reader passed.
     */
    void restoreState(Scanner s) throws GameState.IllegalSaveFormatException {

        // Note: the filename has already been read at this point.
        
        if (!s.nextLine().equals(ROOM_STATES_MARKER)) {
            throw new GameState.IllegalSaveFormatException("No '" +
                ROOM_STATES_MARKER + "' after dungeon filename in save file.");
        }

        String roomName = s.nextLine();
        while (!roomName.equals(TOP_LEVEL_DELIM)) {
            getRoom(roomName.substring(0,roomName.length()-1)).
                restoreState(s, this);
            roomName = s.nextLine();
        }
    }

    /**
     * Gets the entry room of a dungeon.
     * @return the room object
     */
    public Room getEntry() { return entry; }

    /**
     * Gets the name of a dungeon.
     * @return the dungeon name string
     */
    public String getName() { return name; }

    /**
     * Gets the name of the file the scanner object will read from with the dungeon and its information.
     * @return the string of the filename
     */
    public String getFilename() { return filename; }

    /**
     * Adds a room to a dungeon.
     * @param room room object
     */
    public void add(Room room) { rooms.put(room.getTitle(),room); }

    /**
     * Adds an item to a dungeon.
     * @param item item object
     * */
    public void add(Item item) { items.put(item.getPrimaryName(),item); }


    /** 
     * Gets a room in a dungeon.
     * @param roomTitle string that is the title of the room
     * @return the room object
     */
    public Room getRoom(String roomTitle) {
        return rooms.get(roomTitle);
    }


    /**
     * Get the Item object whose primary name is passed. This has nothing to
     * do with where the Adventurer might be, or what's in his/her inventory,
     * etc.
     * @param primaryItemName string that is the primary name of an item not its alias
     * @return an item regardless of its alias or primary name
     * @throws a NoItemException if the item is not found
     */
    public Item getItem(String primaryItemName) throws Item.NoItemException {
        
        if (items.get(primaryItemName) == null) {
            return null; 
        }
        return items.get(primaryItemName);
    }

     /**
     * Calls the disappearItem method, because once an item is transformed, the transformation cannot be reversed. Once that is done, it will return a new item to take the place of the previous item.
     * @param primaryItemName the name of the item being transformed
     * @throws Item.NoItemException exception thrown if the item is not in the dungeon
     * @return newItem that is the new and transformed version of the item
     *
     */
    public Item transformItem(String primaryItemName) throws Item.NoItemException{
        Item newItem = null;

        return newItem;
    }

     /**
     * Removes items from the dungeon permanently. Primarily ensures the item is in the vicinity or inventory so it doesn't affect items in another rooms. Then further ensures it removes them from either the inventory or the room/dungeon.
     *
     * @param primaryItemName the name of the item to be removed from the dungeon
     * @throws Item.NoItemException if the item is not in the dungeon
     */
    public void disappearItem(Item i){
        items.remove(i);
    }

}
