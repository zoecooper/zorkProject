
import java.util.Scanner;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Set;

/**
 * A <tt>Character</tt> represents a character in one of the rooms the player enters and can interact with.
 * @author ZC
 */
public class Character {

    static class NoCharacterException extends Exception {}

    private String primaryName;
    private int health;
    private Hashtable<String,String> messages;
    


    Character(Scanner s) throws NoCharacterException,
        Dungeon.IllegalDungeonFormatException {

        messages = new Hashtable<String,String>();

        // Read character name.
        String names[] = s.nextLine().split(",");
        if (names[0].equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoCharacterException();
        }
        primaryName = names[0];

        // Read and parse verbs lines, as long as there are more.
        String verbLine = s.nextLine();
        while (!verbLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            if (verbLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
                throw new Dungeon.IllegalDungeonFormatException("No '" +
                    Dungeon.SECOND_LEVEL_DELIM + "' after character.");
            }
            String[] verbParts = verbLine.split(":");
            messages.put(verbParts[0],verbParts[1]);
            
            verbLine = s.nextLine();
        }
    }

    /**
     * Gets the health of a character in a room.
     * @return the character's health
     */
    int getHealth() {
        return health;
    }

   /**
    * Gets the name of the character.
    * @return a string that holds the name of the character
    */
    String getPrimaryName() { return primaryName; }

   /**
    * Gets the message of the command.
    * @param verb string that is the command the user wants to use on the character
    * @return a string of the message
    */
    public String getMessageForVerb(String verb) {
        return messages.get(verb);
    }

   /**
    * Gives the name of the character in a string.
    * @return a string of the character's name
    */
    public String toString() {
        return primaryName;
    }
}
