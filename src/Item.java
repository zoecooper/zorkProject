
import java.util.Scanner;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Set;

/**
 * An <tt>Item</tt> represents an item in one of the rooms the player enters and can interact with.
 * @author ZC
 */
public class Item {

    static class NoItemException extends Exception {}

    private String primaryName;
    private int weight;
    private Hashtable<String,String> messages;
    private Set<String> aliases;


    Item(Scanner s) throws NoItemException,
        Dungeon.IllegalDungeonFormatException {

        messages = new Hashtable<String,String>();
        aliases = new HashSet<String>();

        // Read item name.
        String names[] = s.nextLine().split(",");
        if (names[0].equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoItemException();
        }
        primaryName = names[0];
        for (int i=1; i<names.length; i++) {
            aliases.add(names[i]);
        }

        // Read item weight.
        weight = Integer.valueOf(s.nextLine());

        // Read and parse verbs lines, as long as there are more.
        String verbLine = s.nextLine();
        while (!verbLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            if (verbLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
                throw new Dungeon.IllegalDungeonFormatException("No '" +
                    Dungeon.SECOND_LEVEL_DELIM + "' after item.");
            }
            String[] verbParts = verbLine.split(":");
            messages.put(verbParts[0],verbParts[1]);
            
            verbLine = s.nextLine();
        }
    }
    
    /**
     * Gets the weight of an item in a room.
     * @return the item's weight
     * @author ZC
     */
    int getWeight() {
        return weight;
    }

   /**
    * Compares the primary name with alias name.
    * @param name string of the item
    * @return a true if the primary name/alias name equals the name or a false if neither of those work.
    * @author ZC
    */ 
    boolean goesBy(String name) {
        if (this.primaryName.equals(name)) {
            return true;
        }
        for (String alias : this.aliases) {
            if (alias.equals(name)) {
                return true;
            }
        }
        return false;
    }

   /**
    * Gets the name of the item.
    * @return a string that holds the name of the item
    * @author ZC
    */
    String getPrimaryName() { return primaryName; }
    
   /**
    * Gets the message of the command.
    * @param verb string that is the command the user wants to use on the item
    * @return a string of the message
    * @author ZC
    */
    public String getMessageForVerb(String verb) {
        return messages.get(verb);
    }

   /**
    * Gives the name of the item in a string.
    * @return a string of the item's name
    * @author ZC
    */
    public String toString() {
        return primaryName;
    }
}
