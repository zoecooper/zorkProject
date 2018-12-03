import java.util.Scanner;
/**
 * @author AN
 */ 
public class Exit {

    class NoExitException extends Exception {}

    private String dir;
    private Room src, dest;
    /**
     * Takes a String and two Rooms objects to construct an Exit object.
     * After initializing the source Room object's addExit method is called 
     * to add the exit into the Room's exit. 
     *@param dir direction of the exit with the source room
     *@param src room object containing the exit
     *@param dest destination room object
     */ 
    Exit(String dir, Room src, Room dest) {
        init();
        this.dir = dir;
        this.src = src;
        this.dest = dest;
        src.addExit(this);
    }

    /** Given a Scanner object positioned at the beginning of an "exit" file
        entry, read and return an Exit object representing it. 
        @param d The dungeon that contains this exit (so that Room objects 
        may be obtained.)
        @throws NoExitException The reader object is not positioned at the
        start of an exit entry. A side effect of this is the reader's cursor
        is now positioned one line past where it was.
        @throws IllegalDungeonFormatException A structural problem with the
        dungeon file itself, detected when trying to read this room.
     */
    Exit(Scanner s, Dungeon d) throws NoExitException,
        Dungeon.IllegalDungeonFormatException {

        init();
        String srcTitle = s.nextLine();
        if (srcTitle.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoExitException();
        }
        src = d.getRoom(srcTitle);
        dir = s.nextLine();
        dest = d.getRoom(s.nextLine());
        
        // I'm an Exit object. Great. Add me as an exit to my source Room too,
        // though.
      //  System.out.println("src = " + srcTitle);
        src.addExit(this);

        // throw away delimiter
        if (!s.nextLine().equals(Dungeon.SECOND_LEVEL_DELIM)) {
            throw new Dungeon.IllegalDungeonFormatException("No '" +
                Dungeon.SECOND_LEVEL_DELIM + "' after exit.");
        }
    }

    // Common object initialization tasks.
    private void init() {
    }
    /**
     * This method is used to return a message describing the exit 
     * object's dir variable and the name of the variable of the destination 
     * Room object.
     * @return message describing the exits direction and destination
     */  	
    String describe() {
        return "You can go " + dir + " to " + dest.getTitle() + ".";
    }
    /**
     * Returns the direction variable
     * @return exit dir
     */ 	
    String getDir() { return dir; }
    /**
     * Returns the Room which the exit is in. 
     * @return exit source room
     */ 
    Room getSrc() { return src; }
    /**
     * Returns the destination Room of the exit.
     * @return destination of exit
     */ 
    Room getDest() { return dest; }
}
