
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
    private Hashtable<String,String> event = new Hashtable<String,String>();;


    Item(Scanner s) throws NoItemException,
        Dungeon.IllegalDungeonFormatException {

        messages = new Hashtable<String,String>();
        aliases = new HashSet<String>();
	//event = new Hashtable<String,String>();
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
           
	    if(verbParts[0].contains("[")){
	//	    System.out.println(primaryName);
	//	    System.out.println(verbParts[0]);
		    int n = verbParts[0].indexOf("[");
	//	    System.out.println(verbParts[0].substring(0,n));
	//	    System.out.println(verbParts[0].substring(n));
		    event.put(verbParts[0].substring(0,n),verbParts[0].substring(n));
		    messages.put(verbParts[0].substring(0,n),verbParts[1]);
		    //System.out.println("verb= " + verbParts[0].substring(0,n)+ "message= " + verbParts[1]);
	    }
     
            verbLine = s.nextLine();
        }
    }
    
    /**
     * Gets the weight of an item in a room.
     * @return the item's weight
     */
	public Item(String name){
		this.primaryName = name;  
	}

    int getWeight() {
        return weight;
    }

   /**
    * Checks whether the argument matches either the Item's primary name or one of its aliases.
    * @param name string of the item
    * @return true if the primary name/alias name equals the name or false if neither of those work
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
    */
    String getPrimaryName() { return primaryName; }
    
   /**
    * Gets the message of the command and checks for other effects now that the file contains chars like [].
    * @param verb string that is the command the user wants to use on the item
    * @return a string of the message
    * @throw exception if no item exists
    */
    public String getMessageForVerb(String verb) {
	    return messages.get(verb);
    }

    String getEvent(String verb){
	// System.out.println(verb);
	    return event.get(verb);
    }
	    
    

   /**
    * Gives the name of the item in a string.
    * @return a string of the item's name
    */
    public String toString() {
        return primaryName;
    }
   
}
